package com.test.tradingSystem.service;

import com.test.tradingSystem.dto.request.TradeMessageRequestData;
import com.test.tradingSystem.dto.response.TradeMessageResponseData;
import com.test.tradingSystem.model.TradeMessageLog;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface TradeMessageProcessService
{
	void saveTradeMessage(TradeMessageLog tradeMessageLog);

	List<TradeMessageResponseData> getTradeMessage();

	TradeMessageLog convertTradeMessageRequestDataToTradeMessageLog(TradeMessageRequestData tradeMessageRequestData);
}
