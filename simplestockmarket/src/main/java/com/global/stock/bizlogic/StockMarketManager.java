package com.global.stock.bizlogic;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.global.stock.dto.StockDTO;
import com.global.stock.dto.TradeDTO;
import com.global.stock.exception.StockException;
import com.global.stock.exception.TradeException;
/**
 * @author Shiva Krishna Komuravelly
 *
 */
public interface StockMarketManager {

	public BigDecimal getDividendYield(StockDTO stockDTO) throws StockException;

	public BigDecimal getPeRatio(StockDTO stockDTO) throws StockException;

	public void recordTrade(TradeDTO tradeDTO) throws TradeException;

	public StockDTO getStockDTOBySymbol(String stockSymbol);
	
	public ArrayList<StockDTO> getAllStocks();

	public ArrayList<TradeDTO> getTradesForPastNMinutes(int n);

}
