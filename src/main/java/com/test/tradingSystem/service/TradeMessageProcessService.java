package com.test.tradingSystem.service;

import com.test.tradingSystem.dto.request.TradeMessageRequestData;
import com.test.tradingSystem.dto.response.TradeMessageResponseData;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface TradeMessageProcessService
{
	void saveTradeMessage(TradeMessageRequestData tradeMessageRequestData);

	List<TradeMessageResponseData> getTradeMessage();
}
