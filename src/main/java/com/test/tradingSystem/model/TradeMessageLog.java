package com.test.tradingSystem.model;

import com.test.tradingSystem.Enum.CountryCode;
import com.test.tradingSystem.Enum.Currency;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "trade_message_log")
public class TradeMessageLog
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "currency_from")
	@Enumerated(EnumType.ORDINAL)
	private Currency currencyFrom;

	@Column(name = "currency_to")
	@Enumerated(EnumType.ORDINAL)
	private Currency currencyTo;

	@Column(name = "amount_sell")
	private BigDecimal amountSell;

	@Column(name = "amount_buy")
	private BigDecimal amountBuy;

	@Column(name = "rate")
	private BigDecimal rate;

	@Column(name = "time_placed")
	private LocalDateTime timePlaced;

	@Column(name = "originating_country")
	@Enumerated(EnumType.ORDINAL)
	private CountryCode originatingCountry;

	@CreationTimestamp
	@Column(name = "created_date")
	private Date createdTS;

	@UpdateTimestamp
	@Column(name = "last_modified_date")
	private Date modifiedTS;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

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

	public LocalDateTime getTimePlaced()
	{
		return timePlaced;
	}

	public void setTimePlaced(LocalDateTime timePlaced)
	{
		this.timePlaced = timePlaced;
	}

	public Date getCreatedTS()
	{
		return createdTS;
	}

	public void setCreatedTS(Date createdTS)
	{
		this.createdTS = createdTS;
	}

	public Date getModifiedTS()
	{
		return modifiedTS;
	}

	public void setModifiedTS(Date modifiedTS)
	{
		this.modifiedTS = modifiedTS;
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
}
