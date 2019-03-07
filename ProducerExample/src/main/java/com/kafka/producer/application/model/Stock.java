package com.kafka.producer.application.model;



public class Stock {

	private String stockName;
	private long stockValue;
	private long yearLow;
	private long yearHigh;
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public long getStockValue() {
		return stockValue;
	}
	public void setStockValue(long stockValue) {
		this.stockValue = stockValue;
	}
	public long getYearLow() {
		return yearLow;
	}
	public void setYearLow(long yearLow) {
		this.yearLow = yearLow;
	}
	public long getYearHigh() {
		return yearHigh;
	}
	public void setYearHigh(long yearHigh) {
		this.yearHigh = yearHigh;
	}
	
	
}
