package com.global.stock.service;

import java.math.BigDecimal;

import com.global.stock.dto.TradeDTO;
import com.global.stock.exception.StockException;
import com.global.stock.exception.TradeException;
/**
 * @author Shiva Krishna Komuravelly
 *
 */
public interface StockMarketService {

	
	public BigDecimal calculateDividendYield(String stockSymbol, BigDecimal stockPriceValue) throws StockException;
	
	public BigDecimal calculatePERatio(String stockSymbol, BigDecimal stockPriceValue) throws StockException;
	
	public void recordTrade(TradeDTO tradeDTO) throws TradeException, CloneNotSupportedException;
	
	public BigDecimal calculateStockPriceForPastNMinutes(String stockSymbol, int n) throws Exception ;
	
	public BigDecimal calculateGBCEAllShareIndex() throws Exception;
}
