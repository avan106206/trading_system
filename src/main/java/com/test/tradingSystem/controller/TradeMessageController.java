package com.test.tradingSystem.controller;

import com.test.tradingSystem.dto.request.TradeMessageRequestData;
import com.test.tradingSystem.dto.response.TradeMessageResponseData;
import com.test.tradingSystem.service.TradeMessageProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeParseException;
import java.util.List;


@RestController
@RequestMapping(value = {"/tradeMessage"})
public class TradeMessageController
{

	private static final Logger LOG = LoggerFactory.getLogger(TradeMessageController.class);

	@Autowired
	TradeMessageProcessService tradeMessageProcessService;

	@PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
	public ResponseEntity addTradeMessage(@RequestBody TradeMessageRequestData tradeMessageRequestData) {
		try
		{
			tradeMessageProcessService.saveTradeMessage(tradeMessageRequestData);
			ResponseEntity restResponse = new ResponseEntity("added successfully", HttpStatus.OK);
			return restResponse;
		}
		catch(DateTimeParseException dex)
		{
			LOG.error("error occur on TradeMessageController.addTradeMessage()", dex);
			ResponseEntity restResponse = new ResponseEntity("invalid datetime", HttpStatus.BAD_REQUEST);
			return restResponse;
		}
		catch(Exception ex)
		{
			LOG.error("error occur on TradeMessageController.addTradeMessage()", ex);
			ResponseEntity restResponse = new ResponseEntity("internal error", HttpStatus.INTERNAL_SERVER_ERROR);
			return restResponse;
		}
	}

	@GetMapping("/get")
	public ResponseEntity getTradeMessage() {
		try
		{
			List<TradeMessageResponseData> tradeMessageResponseDataList = tradeMessageProcessService.getTradeMessage();
			ResponseEntity restResponse = new ResponseEntity(tradeMessageResponseDataList, HttpStatus.OK);
			return restResponse;
		}
		catch(Exception ex)
		{
			LOG.error("error occur on TradeMessageController.addTradeMessage()", ex);
			ResponseEntity restResponse = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
			return restResponse;
		}
	}

}
