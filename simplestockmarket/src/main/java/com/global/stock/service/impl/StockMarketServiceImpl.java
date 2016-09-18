package com.global.stock.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;

import org.apache.commons.math3.stat.StatUtils;
import org.apache.log4j.Logger;

import com.global.stock.bizlogic.StockMarketManager;
import com.global.stock.bizlogic.impl.StockMarketManagerImpl;
import com.global.stock.dto.StockDTO;
import com.global.stock.dto.TradeDTO;
import com.global.stock.exception.StockException;
import com.global.stock.exception.TradeException;
import com.global.stock.service.StockMarketService;

/**
 * @author Shiva Krishna Komuravelly
 *
 */
public class StockMarketServiceImpl implements StockMarketService {

	private Logger log = Logger.getLogger(StockMarketServiceImpl.class);

	private StockMarketManager stockMarketManager = null;

	// NOTE: If we are using spring then this initialization is not required.
	public StockMarketServiceImpl() {
		super();
		stockMarketManager = new StockMarketManagerImpl();
	}

	public StockMarketManager getStockMarketManager() {
		return stockMarketManager;
	}

	public void setStockMarketManager(StockMarketManager stockMarketManager) {
		this.stockMarketManager = stockMarketManager;
	}

	public BigDecimal calculateDividendYield(String stockSymbol, BigDecimal stockPriceValue) throws StockException {
		BigDecimal dividendYield = null;
		try {
			StockDTO stockDTO = stockMarketManager.getStockDTOBySymbol(stockSymbol);
			stockDTO.setStockPrice(stockPriceValue);
			dividendYield = stockMarketManager.getDividendYield(stockDTO);
			log.debug("Dividend Yield value is : " + dividendYield);
		} catch (StockException e) {
			log.error("Stock Exception occured while calculating Dividend Yield", e);
			throw e;
		}
		return dividendYield;
	}

	public BigDecimal calculatePERatio(String stockSymbol, BigDecimal stockPriceValue) throws StockException {
		BigDecimal peRatio = null;
		try {
			StockDTO stockDTO = stockMarketManager.getStockDTOBySymbol(stockSymbol);
			stockDTO.setStockPrice(stockPriceValue);
			peRatio = stockMarketManager.getPeRatio(stockDTO);
			log.debug("PE Ratio value is : " + peRatio);
		} catch (StockException e) {
			log.error("Stock Exception occured while calculating PE Ratio", e);
			throw e;
		}
		return peRatio;
	}

	public void recordTrade(TradeDTO tradeDTO) throws TradeException, CloneNotSupportedException {
		log.debug("Recording the Trade");
		try {
			BigDecimal stockDTOPriceValue = tradeDTO.getStockDTO().getStockPrice();
			tradeDTO.setStockDTO(stockMarketManager.getStockDTOBySymbol(tradeDTO.getStockDTO().getStockSymbol()));
			tradeDTO.getStockDTO().setStockPrice(stockDTOPriceValue);
			stockMarketManager.recordTrade(tradeDTO);

			log.debug("Recorded Trade Successfully ");
		} catch (TradeException e) {
			log.error("Trade Exception occured while recording a Trade", e);
			throw e;
		}
	}

	public BigDecimal calculateStockPriceForPastNMinutes(String stockSymbol, int n) throws Exception {
		BigDecimal stockPriceValue = null;
		try {
			StockDTO stockDTO = stockMarketManager.getStockDTOBySymbol(stockSymbol);

			ArrayList<TradeDTO> tradeDTOs = stockMarketManager.getTradesForPastNMinutes(n);

			BigDecimal totalQuantityValue = BigDecimal.ZERO;
			BigDecimal totalTradePrice = BigDecimal.ZERO;
			for (TradeDTO tradeDTO : tradeDTOs) {
				totalTradePrice = totalTradePrice.add(tradeDTO.getPrice().multiply(tradeDTO.getQuantity()));
				totalQuantityValue = totalQuantityValue.add(tradeDTO.getQuantity());
			}

			if (BigDecimal.ZERO.compareTo(totalQuantityValue) >= 0) {
				log.error("Total Quantity Value is zero so cant calculate Stock Price Value");
			} else {
				stockPriceValue = totalTradePrice.divide(totalQuantityValue, 2, RoundingMode.HALF_UP);
			}

			log.debug("Stock Price value for Past " + n + " minutes is " + stockPriceValue);
		} catch (Exception e) {
			log.error("Exception occured while calculating the Stock Price for Past N minutes", e);
			throw e;
		}
		return stockPriceValue;
	}

	public BigDecimal calculateGBCEAllShareIndex() throws Exception {
		BigDecimal gbceAllShareIndexValue = BigDecimal.ZERO;

		ArrayList<StockDTO> stocks = stockMarketManager.getAllStocks();
		ArrayList<BigDecimal> totalStockPricesValue = new ArrayList<BigDecimal>();
		for (StockDTO stockDTO : stocks) {
			BigDecimal stockPriceValue = calculateStockPriceForPastNMinutes(stockDTO.getStockSymbol(), 0);
			if (BigDecimal.ZERO.compareTo(stockPriceValue) <= 0) {
				totalStockPricesValue.add(stockPriceValue);
			}
		}

		if (totalStockPricesValue.size() >= 1) {
			double[] stockPricesArr = new double[totalStockPricesValue.size()];

			for (int k = 0; k <= (totalStockPricesValue.size() - 1); k++) {
				stockPricesArr[k] = totalStockPricesValue.get(k).doubleValue();
			}
			gbceAllShareIndexValue = new BigDecimal(StatUtils.geometricMean(stockPricesArr), MathContext.DECIMAL32);
		}
		return gbceAllShareIndexValue;
	}

}
