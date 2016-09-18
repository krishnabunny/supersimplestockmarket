package com.global.stock.dao;

import java.util.ArrayList;
import java.util.List;

import com.global.stock.dto.StockDTO;
import com.global.stock.dto.TradeDTO;
/**
 * @author Shiva Krishna Komuravelly
 *
 */
public interface StockDAO {

	public StockDTO getStockBySymbol(String stockSymbol);

	public void recordTrade(TradeDTO tradeDTO);

	public List<StockDTO> fetchAllStocks();

	public ArrayList<TradeDTO> getTradesForPastNMinutes(int n);

}
