package com.test.tradingSystem.controller;

import com.test.tradingSystem.Enum.CountryCode;
import com.test.tradingSystem.Enum.Currency;
import com.test.tradingSystem.dto.response.TradeMessageResponseData;
import com.test.tradingSystem.service.TradeMessageProcessService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
public class TradeMessageControllerTest
{
	@Autowired
	private MockMvc mockMvc;

	@Mock
	private TradeMessageProcessService tradeMessageProcessService;

	@Test
	/*** valid input, return 200 ok ***/
	public void whenValidInput_thenReturns200() throws Exception {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

		String requestBodyString = new JSONObject()
				.put("userId", "12345")
				.put("currencyFrom", "USD")
				.put("currencyTo", "HKD")
				.put("amountSell", BigDecimal.valueOf((long)1000))
				.put("amountBuy", BigDecimal.valueOf((long)747.10))
				.put("rate", BigDecimal.valueOf((long)0.7471))
				.put("timePlaced", "24-AUG-18 10:27:44")
				.put("originatingCountry", "HK")
				.toString();

		mockMvc.perform(post("/tradeMessage/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBodyString))
				.andExpect(status().isOk());
	}


	@Test
	/*** response should contain userId, currencyFrom, currencyTo, amountSell, amountBuy, rate, timePlaced, originatingCountry ***/
	public void whenGetTradeMessage_thenReturns_userId_currencyFrom_currencyTo_amountSell_amountBuy_rate_timePlaced_originatingCountry() throws Exception {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

		TradeMessageResponseData tradeMessageResponseData = new TradeMessageResponseData("12345", Currency.USD, Currency.HKD
				, BigDecimal.valueOf((long)1000), BigDecimal.valueOf((long)747.10), BigDecimal.valueOf((long)0.7471), "24-AUG-18 10:27:44"
				, CountryCode.US);
		List<TradeMessageResponseData> responseList = new ArrayList<>();
		responseList.add(tradeMessageResponseData);

		Mockito.when(tradeMessageProcessService.getTradeMessage()).thenReturn(responseList);

		mockMvc.perform(get("/tradeMessage/get"))
				.andExpect(jsonPath("$[0].userId").hasJsonPath())
				.andExpect(jsonPath("$[0].currencyFrom").hasJsonPath())
				.andExpect(jsonPath("$[0].currencyTo").hasJsonPath())
				.andExpect(jsonPath("$[0].amountSell").hasJsonPath())
				.andExpect(jsonPath("$[0].amountBuy").hasJsonPath())
				.andExpect(jsonPath("$[0].rate").hasJsonPath())
				.andExpect(jsonPath("$[0].timePlaced").hasJsonPath())
				.andExpect(jsonPath("$[0].originatingCountry").hasJsonPath());
	}

	@Test
	/*** if input the datetime with invalid format, return 400 bad request ***/
	public void whenInValidDatetimeFormat_thenReturns400() throws Exception {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

		String requestBodyString = new JSONObject()
				.put("userId", "12345")
				.put("currencyFrom", "USD")
				.put("currencyTo", "HKD")
				.put("amountSell", BigDecimal.valueOf((long)1000))
				.put("amountBuy", BigDecimal.valueOf((long)747.10))
				.put("rate", BigDecimal.valueOf((long)0.7471))
				.put("timePlaced", "24-08-2018 10:27:44")
				.put("originatingCountry", "HK")
				.toString();

		mockMvc.perform(post("/tradeMessage/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBodyString))
				.andExpect(status().isBadRequest());
	}

	@Test
	/*** if input the invalid country code, return 400 bad request ***/
	public void whenInValidCountryCode_thenReturns400() throws Exception {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

		String requestBodyString = new JSONObject()
				.put("userId", "12345")
				.put("currencyFrom", "USD")
				.put("currencyTo", "HKD")
				.put("amountSell", BigDecimal.valueOf((long)1000))
				.put("amountBuy", BigDecimal.valueOf((long)747.10))
				.put("rate", BigDecimal.valueOf((long)0.7471))
				.put("timePlaced", "24-AUG-18 10:27:44")
				.put("originatingCountry", "ABC")
				.toString();

		mockMvc.perform(post("/tradeMessage/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBodyString))
				.andExpect(status().isBadRequest());
	}

	@Test
	/*** if input the string as the amount, return 400 bad request ***/
	public void whenInValidAmount_thenReturns400() throws Exception {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

		String requestBodyString = new JSONObject()
				.put("userId", "12345")
				.put("currencyFrom", "USD")
				.put("currencyTo", "HKD")
				.put("amountSell", "fjiesifneivnj")
				.put("amountBuy", "wodqwi")
				.put("rate", "85161")
				.put("timePlaced", "24-AUG-18 10:27:44")
				.put("originatingCountry", "ABC")
				.toString();

		mockMvc.perform(post("/tradeMessage/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBodyString))
				.andExpect(status().isBadRequest());
	}


}
