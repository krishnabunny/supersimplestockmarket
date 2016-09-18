package com.global.stock.dto;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
/**
 * @author Shiva Krishna Komuravelly
 *
 */
public class StockDTO implements Cloneable {

	private Logger logger = Logger.getLogger(StockDTO.class);

	private String stockSymbol = null;

	private StockTypes stockType = StockTypes.COMMON;

	private BigDecimal lastDividend = BigDecimal.ZERO;

	private BigDecimal fixedDividend = BigDecimal.ZERO;

	private BigDecimal parValue = BigDecimal.ZERO;

	private BigDecimal stockPrice = BigDecimal.ZERO;

	public StockDTO clone() throws CloneNotSupportedException {
		return (StockDTO) super.clone();
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public StockTypes getStockType() {
		return stockType;
	}

	public void setStockType(StockTypes stockType) {
		this.stockType = stockType;
	}

	public BigDecimal getLastDividend() {
		return lastDividend;
	}

	public void setLastDividend(BigDecimal lastDividend) {
		this.lastDividend = lastDividend;
	}

	public BigDecimal getFixedDividend() {
		return fixedDividend;
	}

	public void setFixedDividend(BigDecimal fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

	public BigDecimal getParValue() {
		return parValue;
	}

	public void setParValue(BigDecimal parValue) {
		this.parValue = parValue;
	}

	public BigDecimal getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(BigDecimal stockPrice) {
		this.stockPrice = stockPrice;
	}

	@Override
	public String toString() {
		return "StockDTO [logger=" + logger + ", stockSymbol=" + stockSymbol + ", stockType=" + stockType
				+ ", lastDividend=" + lastDividend + ", fixedDividend=" + fixedDividend + ", parValue=" + parValue
				+ ", stockPrice=" + stockPrice + "]";
	}

}
