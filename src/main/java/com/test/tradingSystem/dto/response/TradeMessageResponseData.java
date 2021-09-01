package com.test.tradingSystem.dto.response;


import com.test.tradingSystem.Enum.CountryCode;
import com.test.tradingSystem.Enum.Currency;

import java.math.BigDecimal;


public class TradeMessageResponseData
{
	private String userId;

	private Currency currencyFrom;

	private Currency currencyTo;

	private BigDecimal amountSell;

	private BigDecimal amountBuy;

	private BigDecimal rate;

	private String timePlaced;

	private CountryCode originatingCountry;

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public BigDecimal getAmountSell()
	{
		return amountSell;
	}

	public void setAmountSell(BigDecimal amountSell)
	{
		this.amountSell = amountSell;
	}

	public BigDecimal getAmountBuy()
	{
		return amountBuy;
	}

	public void setAmountBuy(BigDecimal amountBuy)
	{
		this.amountBuy = amountBuy;
	}

	public BigDecimal getRate()
	{
		return rate;
	}

	public void setRate(BigDecimal rate)
	{
		this.rate = rate;
	}

	public String getTimePlaced()
	{
		return timePlaced;
	}

	public void setTimePlaced(String timePlaced)
	{
		this.timePlaced = timePlaced;
	}

	public Currency getCurrencyFrom()
	{
		return currencyFrom;
	}

	public void setCurrencyFrom(Currency currencyFrom)
	{
		this.currencyFrom = currencyFrom;
	}

	public Currency getCurrencyTo()
	{
		return currencyTo;
	}

	public void setCurrencyTo(Currency currencyTo)
	{
		this.currencyTo = currencyTo;
	}

	public CountryCode getOriginatingCountry()
	{
		return originatingCountry;
	}

	public void setOriginatingCountry(CountryCode originatingCountry)
	{
		this.originatingCountry = originatingCountry;
	}

	public TradeMessageResponseData(String userId, Currency currencyFrom, Currency currencyTo, BigDecimal amountSell, BigDecimal amountBuy, BigDecimal rate,
			String timePlaced, CountryCode originatingCountry)
	{
		this.userId = userId;
		this.currencyFrom = currencyFrom;
		this.currencyTo = currencyTo;
		this.amountSell = amountSell;
		this.amountBuy = amountBuy;
		this.rate = rate;
		this.timePlaced = timePlaced;
		this.originatingCountry = originatingCountry;
	}

	public TradeMessageResponseData()
	{
	}
}
