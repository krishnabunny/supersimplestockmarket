package com.global.stock.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.global.datastructure.SortedArrayList;
import com.global.stock.dao.StockDAO;
import com.global.stock.dto.StockDTO;
import com.global.stock.dto.StockTypes;
import com.global.stock.dto.TradeDTO;
/**
 * @author Shiva Krishna Komuravelly
 *
 */
public class StockDAOImpl implements StockDAO {

	private Logger log = Logger.getLogger(StockDAOImpl.class);

	private HashMap<String, StockDTO> allStocks = null;

	private SortedArrayList<TradeDTO> allTrades = null;

	public StockDAOImpl() {
		allTrades = new SortedArrayList<TradeDTO>();
		allStocks = new HashMap<String, StockDTO>();

		StockDTO stockDTO1 = new StockDTO();
		stockDTO1.setStockSymbol("TEA");
		stockDTO1.setStockType(StockTypes.COMMON);
		stockDTO1.setLastDividend(BigDecimal.ZERO);
		stockDTO1.setFixedDividend(BigDecimal.ZERO);
		stockDTO1.setParValue(new BigDecimal(100));
		allStocks.put("TEA", stockDTO1);

		StockDTO stockDTO2 = new StockDTO();
		stockDTO2.setStockSymbol("POP");
		stockDTO2.setStockType(StockTypes.COMMON);
		stockDTO2.setLastDividend(new BigDecimal(8));
		stockDTO2.setFixedDividend(BigDecimal.ZERO);
		stockDTO2.setParValue(new BigDecimal(100));
		allStocks.put("POP", stockDTO2);

		StockDTO stockDTO3 = new StockDTO();
		stockDTO3.setStockSymbol("ALE");
		stockDTO3.setStockType(StockTypes.COMMON);
		stockDTO3.setLastDividend(new BigDecimal(23));
		stockDTO3.setFixedDividend(BigDecimal.ZERO);
		stockDTO3.setParValue(new BigDecimal(60));
		allStocks.put("ALE", stockDTO3);

		StockDTO stockDTO4 = new StockDTO();
		stockDTO4.setStockSymbol("GIN");
		stockDTO4.setStockType(StockTypes.PREFFERED);
		stockDTO4.setLastDividend(new BigDecimal(8));
		stockDTO4.setFixedDividend(new BigDecimal(0.02d));
		stockDTO4.setParValue(new BigDecimal(100));
		allStocks.put("GIN", stockDTO4);

		StockDTO stockDTO5 = new StockDTO();
		stockDTO5.setStockSymbol("JOE");
		stockDTO5.setStockType(StockTypes.COMMON);
		stockDTO5.setLastDividend(new BigDecimal(13));
		stockDTO5.setFixedDividend(BigDecimal.ZERO);
		stockDTO5.setParValue(new BigDecimal(250));
		allStocks.put("JOE", stockDTO5);

	}

	public StockDTO getStockBySymbol(String stockSymbol) {
		StockDTO stockDTO = null;
		try {
			if (stockSymbol != null) {
				stockDTO = allStocks.get(stockSymbol);
			}
		} catch (Exception e) {
			log.error("Stock symbol not found : " + stockSymbol, e);
		}
		return stockDTO;
	}

	public void recordTrade(TradeDTO tradeDTO) {
		allTrades.insertSorted(tradeDTO);
	}

	public HashMap<String, StockDTO> getAllStocks() {
		return allStocks;
	}

	public void setAllStocks(HashMap<String, StockDTO> allStocks) {
		this.allStocks = allStocks;
	}

	public SortedArrayList<TradeDTO> getAllTrades() {
		return allTrades;
	}

	public void setAllTrades(SortedArrayList<TradeDTO> allTrades) {
		this.allTrades = allTrades;
	}

	public List<StockDTO> fetchAllStocks() {
		ArrayList<StockDTO> allStockDTOs = new ArrayList<StockDTO>(allStocks.values());
		return allStockDTOs;
	}

	public ArrayList<TradeDTO> getTradesForPastNMinutes(int n) {
		Date dateNow = Calendar.getInstance().getTime();
		ArrayList<TradeDTO> tradesList = new ArrayList<TradeDTO>();
		for (int k = 0; k < allTrades.size(); k++) {
			Date tradeTimeStamp = allTrades.get(k).getTimeStamp();
			if (((dateNow.getTime() - tradeTimeStamp.getTime()) / 1000) / 60 <= n) {
				tradesList.add(allTrades.get(k));
			}
		}
		return tradesList;
	}

}
