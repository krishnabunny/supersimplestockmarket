package com.global.stock.bizlogic.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.global.stock.bizlogic.StockMarketManager;
import com.global.stock.dao.StockDAO;
import com.global.stock.dao.impl.StockDAOImpl;
import com.global.stock.dto.StockDTO;
import com.global.stock.dto.StockTypes;
import com.global.stock.dto.TradeDTO;
import com.global.stock.exception.StockException;
import com.global.stock.exception.TradeException;
/**
 * @author Shiva Krishna Komuravelly
 *
 */
public class StockMarketManagerImpl implements StockMarketManager {

	private Logger log = Logger.getLogger(StockMarketManagerImpl.class);

	private StockDAO stockDAO = null;

	public StockMarketManagerImpl() {
		super();
		stockDAO = new StockDAOImpl();
	}

	public BigDecimal getDividendYield(StockDTO stockDTO) throws StockException {
		BigDecimal dividendYield = null;
		if (BigDecimal.ZERO.compareTo(stockDTO.getStockPrice()) >= 0) {
			log.error("Stock Price cant be zero or less");
			throw new StockException("Stock Price cant be zero or less");
		} else {
			if (StockTypes.COMMON == stockDTO.getStockType()) {
				dividendYield = stockDTO.getLastDividend().divide(stockDTO.getStockPrice(), 2, RoundingMode.HALF_UP);
			} else {
				dividendYield = (stockDTO.getFixedDividend().multiply(stockDTO.getParValue()))
						.divide(stockDTO.getStockPrice(), 2, RoundingMode.HALF_UP);
			}
		}
		return dividendYield;
	}

	public BigDecimal getPeRatio(StockDTO stockDTO) throws StockException {
		BigDecimal peRatio = null;

		if (BigDecimal.ZERO.compareTo(stockDTO.getStockPrice()) >= 0) {
			log.error("Stock Price cant be zero or less");
			throw new StockException("Stock Price cant be zero or less");
		} else if (BigDecimal.ZERO.compareTo(getDividendYield(stockDTO)) != 0) {
			peRatio = stockDTO.getStockPrice().divide(getDividendYield(stockDTO), 2, RoundingMode.HALF_UP);
		} else {
			log.error("Dividend Yield Value is zero so we cant divide the stock price with dividend Yeild Value");
			throw new StockException("Dividend Yield Value is zero so we cant divide the stock price with dividend Yeild Value");
		}

		return peRatio;
	}

	public void recordTrade(TradeDTO tradeDTO) throws TradeException {

		if (tradeDTO == null) {
			log.error("Trade cant be null");
			throw new TradeException("Trade cant be recorded with null value");
		}

		if (tradeDTO.getStockDTO() == null) {
			log.error("Trade should always be happening on a Stock");
			throw new TradeException("Trade cant be recorded because its not associated with any Stock");
		}

		if (BigDecimal.ZERO.compareTo(tradeDTO.getQuantity()) >= 0) {
			log.error("Trade cant be recorded because quantity should be there for doing any trade");
			throw new TradeException("Trade cant be recorded because quantity should be there for doing any trade");
		}

		// shares price should be greater than zero
		if (BigDecimal.ZERO.compareTo(tradeDTO.getPrice()) >= 0) {
			log.error("Trade cant be recorded because Trade Price cant be zero");
			throw new TradeException("Trade cant be recorded because Trade Price cant be zero");
		}

		if (BigDecimal.ZERO.compareTo(tradeDTO.getQuantity()) >= 0) {
			log.error("Trade cant be recorded as Trade Quantity is 0 or less.");
			throw new TradeException("Trade cant be recorded as Trade Quantity cant be zero");
		} else {
			stockDAO.recordTrade(tradeDTO);
		}
	}

	public StockDTO getStockDTOBySymbol(String stockSymbol) {
		return stockDAO.getStockBySymbol(stockSymbol);
	}

	public ArrayList<StockDTO> getAllStocks() {
		return (ArrayList<StockDTO>) stockDAO.fetchAllStocks();
	}

	public StockDAO getStockDAO() {
		return stockDAO;
	}

	public void setStockDAO(StockDAO stockDAO) {
		this.stockDAO = stockDAO;
	}

	public ArrayList<TradeDTO> getTradesForPastNMinutes(int n) {
		return stockDAO.getTradesForPastNMinutes(n);
	}

}
