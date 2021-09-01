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
	public ResponseEntity<String> addTradeMessage(@RequestBody TradeMessageRequestData tradeMessageRequestData) {
		try
		{
			tradeMessageProcessService.saveTradeMessage(tradeMessageProcessService.convertTradeMessageRequestDataToTradeMessageLog(tradeMessageRequestData));
			return new ResponseEntity<>("added successfully", HttpStatus.OK);
		}
		catch(DateTimeParseException dex)
		{
			LOG.error("error occur on TradeMessageController.addTradeMessage()", dex);
			return new ResponseEntity<>("invalid datetime", HttpStatus.BAD_REQUEST);
		}
		catch(IllegalArgumentException ie)
		{
			if(ie.getMessage().contains("Enum.Currency")){
				LOG.error("error occur on TradeMessageController.addTradeMessage()", ie);
				return new ResponseEntity<>("invalid Currency", HttpStatus.BAD_REQUEST);
			}else{
				LOG.error("error occur on TradeMessageController.addTradeMessage()", ie);
				return new ResponseEntity<>("invalid CountryCode", HttpStatus.BAD_REQUEST);
			}
		}
		catch(Exception ex)
		{
			LOG.error("error occur on TradeMessageController.addTradeMessage()", ex);
			return new ResponseEntity<>("internal error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/get")
	public ResponseEntity<List<TradeMessageResponseData>> getTradeMessage() {
		try
		{
			return new ResponseEntity<>(tradeMessageProcessService.getTradeMessage(), HttpStatus.OK);
		}
		catch(Exception ex)
		{
			LOG.error("error occur on TradeMessageController.addTradeMessage()", ex);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
