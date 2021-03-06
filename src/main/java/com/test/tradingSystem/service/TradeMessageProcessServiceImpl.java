package com.test.tradingSystem.service;

import com.test.tradingSystem.Enum.CountryCode;
import com.test.tradingSystem.Enum.Currency;
import com.test.tradingSystem.Enum.DateTimeFormat;
import com.test.tradingSystem.dao.TradeMessageLogDAO;
import com.test.tradingSystem.dto.request.TradeMessageRequestData;
import com.test.tradingSystem.dto.response.TradeMessageResponseData;
import com.test.tradingSystem.model.TradeMessageLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;


@Component
public class TradeMessageProcessServiceImpl implements TradeMessageProcessService
{
	@Autowired
	TradeMessageLogDAO tradeMessageLogDAO;

	@Override
	@Transactional
	public void saveTradeMessage(TradeMessageLog tradeMessageLog)
	{

		tradeMessageLogDAO.save(tradeMessageLog);
	}

	@Override
	public List<TradeMessageResponseData> getTradeMessage()
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateTimeFormat.dateTimeString.toString());
		List<TradeMessageResponseData> tradeMessageList = new ArrayList<>();
		tradeMessageLogDAO.findAll().forEach(
				tradeMessage -> {
					TradeMessageResponseData m = new TradeMessageResponseData();
					m.setAmountBuy(tradeMessage.getAmountBuy());
					m.setAmountSell(tradeMessage.getAmountSell());
					m.setCurrencyFrom(tradeMessage.getCurrencyFrom());
					m.setCurrencyTo(tradeMessage.getCurrencyTo());
					m.setRate(tradeMessage.getRate());
					m.setOriginatingCountry(tradeMessage.getOriginatingCountry());
					m.setUserId(tradeMessage.getUserId());
					m.setTimePlaced(tradeMessage.getTimePlaced().format(formatter));
					tradeMessageList.add(m);
				}
		);
		return tradeMessageList;
	}

	@Override
	public TradeMessageLog convertTradeMessageRequestDataToTradeMessageLog(TradeMessageRequestData tradeMessageRequestData)
	{
		DateTimeFormatter formatter = new DateTimeFormatterBuilder().parseCaseInsensitive()
				.append(DateTimeFormatter.ofPattern(DateTimeFormat.dateTimeString.toString())).toFormatter();
		LocalDateTime TimePlaced = LocalDateTime.parse(tradeMessageRequestData.getTimePlaced(), formatter);
		TradeMessageLog tradeMessageLog = new TradeMessageLog();
		tradeMessageLog.setAmountBuy(tradeMessageRequestData.getAmountBuy());
		tradeMessageLog.setAmountSell(tradeMessageRequestData.getAmountSell());
		tradeMessageLog.setCurrencyFrom(Currency.valueOf(tradeMessageRequestData.getCurrencyFrom()));
		tradeMessageLog.setCurrencyTo(Currency.valueOf(tradeMessageRequestData.getCurrencyTo()));
		tradeMessageLog.setRate(tradeMessageRequestData.getRate());
		tradeMessageLog.setOriginatingCountry(CountryCode.valueOf(tradeMessageRequestData.getOriginatingCountry()));
		tradeMessageLog.setUserId(tradeMessageRequestData.getUserId());
		tradeMessageLog.setTimePlaced(TimePlaced);
		return tradeMessageLog;
	}


}
