/**
 * 
 */
package com.global.stock.bizlogic;

import java.math.BigDecimal;
import java.util.Calendar;

import com.global.stock.dto.StockDTO;
import com.global.stock.dto.TradeDTO;
import com.global.stock.dto.TradeType;
import com.global.stock.exception.StockException;
import com.global.stock.exception.TradeException;
import com.global.stock.service.StockMarketService;
import com.global.stock.service.impl.StockMarketServiceImpl;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author Shiva Krishna Komuravelly
 *
 */
public class StockMarketServiceTest extends TestCase {

	private StockMarketService stockMarketService;

	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public StockMarketServiceTest(String testName) {
		super(testName);
		stockMarketService = new StockMarketServiceImpl();
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(StockMarketServiceTest.class);
	}

	public void testDividendYiedCalculation() {
		assertTrue(true);

		try {
			BigDecimal dividendYieldValue1 = stockMarketService.calculateDividendYield("TEA", new BigDecimal(20));
			// System.out.println(dividendYieldValue1);
			assertEquals(dividendYieldValue1, new BigDecimal("0.00"));

			BigDecimal dividendYieldValue2 = stockMarketService.calculateDividendYield("POP", new BigDecimal(30));
			// System.out.println(dividendYieldValue2);
			assertEquals(dividendYieldValue2, new BigDecimal("0.27"));

			BigDecimal dividendYieldValue3 = stockMarketService.calculateDividendYield("ALE", new BigDecimal(25));
			// System.out.println(dividendYieldValue3);
			assertEquals(dividendYieldValue3, new BigDecimal("0.92"));

			BigDecimal dividendYieldValue4 = stockMarketService.calculateDividendYield("GIN", new BigDecimal(35));
			// System.out.println(dividendYieldValue4);
			assertEquals(dividendYieldValue4, new BigDecimal("0.06"));

			BigDecimal dividendYieldValue5 = stockMarketService.calculateDividendYield("JOE", new BigDecimal(40));
			// System.out.println(dividendYieldValue5);
			assertEquals(dividendYieldValue5, new BigDecimal("0.33"));

		} catch (StockException e) {
			e.printStackTrace();
			assertFalse(true);
		}

	}

	public void testPEValueCalculation() {
		assertTrue(true);

		try {
			try {
				BigDecimal pEValue1 = stockMarketService.calculatePERatio("TEA", new BigDecimal(20));
				// System.out.println(pEValue1);
			} catch (StockException e) {
				assertTrue(true);
			}

			BigDecimal pEValue2 = stockMarketService.calculatePERatio("POP", new BigDecimal(30));
			// System.out.println(pEValue2);
			assertEquals(pEValue2, new BigDecimal("111.11"));

			BigDecimal pEValue3 = stockMarketService.calculatePERatio("ALE", new BigDecimal(25));
			// System.out.println(pEValue3);
			assertEquals(pEValue3, new BigDecimal("27.17"));

			BigDecimal pEValue4 = stockMarketService.calculatePERatio("GIN", new BigDecimal(35));
			// System.out.println(pEValue4);
			assertEquals(pEValue4, new BigDecimal("583.33"));

			BigDecimal pEValue5 = stockMarketService.calculatePERatio("JOE", new BigDecimal(40));
			// System.out.println(pEValue5);
			assertEquals(pEValue5, new BigDecimal("121.21"));

		} catch (StockException e) {
			e.printStackTrace();
			assertFalse(true);
		}

	}

	public void testWeighedStockPrice() throws InterruptedException {
		assertTrue(true);

		try {
			TradeDTO tradeDTO1 = new TradeDTO();
			StockDTO stockDTO1 = new StockDTO();
			stockDTO1.setStockSymbol("POP");
			stockDTO1.setStockPrice(new BigDecimal("20"));
			tradeDTO1.setStockDTO(stockDTO1);
			tradeDTO1.setQuantity(new BigDecimal(5));
			tradeDTO1.setPrice(new BigDecimal(5));
			tradeDTO1.setTradeType(TradeType.BUY);
			tradeDTO1.setTimeStamp(Calendar.getInstance().getTime());
			stockMarketService.recordTrade(tradeDTO1);
			
			Thread.sleep(100);
			tradeDTO1.setTimeStamp(Calendar.getInstance().getTime());
			stockMarketService.recordTrade(tradeDTO1);
			
			Thread.sleep(100);
			tradeDTO1.setTimeStamp(Calendar.getInstance().getTime());
			stockMarketService.recordTrade(tradeDTO1);
			
			
			Thread.sleep(100);
			tradeDTO1.setTimeStamp(Calendar.getInstance().getTime());
			stockMarketService.recordTrade(tradeDTO1);
			
			
			Thread.sleep(100);
			tradeDTO1.setTimeStamp(Calendar.getInstance().getTime());
			stockMarketService.recordTrade(tradeDTO1);
			
			
			Thread.sleep(100);
			tradeDTO1.setTimeStamp(Calendar.getInstance().getTime());
			stockMarketService.recordTrade(tradeDTO1);
			
			BigDecimal stockPriceForPastNMins = stockMarketService.calculateStockPriceForPastNMinutes("POP", 1);
			//System.out.println(stockPriceForPastNMins);
			assertEquals(stockPriceForPastNMins, new BigDecimal("5.00"));
			
		} catch (TradeException e) {
			e.printStackTrace();
			assertFalse(true);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			assertFalse(true);
		} catch (Exception e) {
			e.printStackTrace();
			assertFalse(true);
		}

	}
	
	
	
	public void testCalculateGBCEAllShareIndex() throws InterruptedException {
		assertTrue(true);

		try {
			
			TradeDTO tradeDTO1 = new TradeDTO();
			StockDTO stockDTO1 = new StockDTO();
			stockDTO1.setStockSymbol("POP");
			stockDTO1.setStockPrice(new BigDecimal("20"));
			tradeDTO1.setStockDTO(stockDTO1);
			tradeDTO1.setQuantity(new BigDecimal(5));
			tradeDTO1.setPrice(new BigDecimal(5));
			tradeDTO1.setTradeType(TradeType.BUY);
			tradeDTO1.setTimeStamp(Calendar.getInstance().getTime());
			stockMarketService.recordTrade(tradeDTO1);
			
			Thread.sleep(100);
			tradeDTO1.setTimeStamp(Calendar.getInstance().getTime());
			stockMarketService.recordTrade(tradeDTO1);
			
			Thread.sleep(100);
			tradeDTO1.setTimeStamp(Calendar.getInstance().getTime());
			stockMarketService.recordTrade(tradeDTO1);
			
			
			Thread.sleep(100);
			tradeDTO1.setTimeStamp(Calendar.getInstance().getTime());
			stockMarketService.recordTrade(tradeDTO1);
			
			
			Thread.sleep(100);
			tradeDTO1.setTimeStamp(Calendar.getInstance().getTime());
			stockMarketService.recordTrade(tradeDTO1);
			
			
			Thread.sleep(100);
			tradeDTO1.setTimeStamp(Calendar.getInstance().getTime());
			stockMarketService.recordTrade(tradeDTO1);
			
			BigDecimal gbceAllShareIndexValue = stockMarketService.calculateGBCEAllShareIndex();
			//System.out.println(gbceAllShareIndexValue);
			assertEquals(gbceAllShareIndexValue,new BigDecimal("5.000000"));
			
		}  catch (Exception e) {
			e.printStackTrace();
			assertFalse(true);
		}

	}

}
