package com.test.tradingSystem.Enum;

public enum DateTimeFormat
{
	dateTimeString("yy-MMM-d HH:mm:ss");

	private String dateTimeFormat = "";

	DateTimeFormat(String code) {
		this.dateTimeFormat = code;
	}

	@Override
	public String toString() {
		return dateTimeFormat;
	}
}
