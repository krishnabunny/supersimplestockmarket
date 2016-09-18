package com.global.stock.dto;

import java.math.BigDecimal;
import java.util.Date;
/**
 * @author Shiva Krishna Komuravelly
 *
 */
public class TradeDTO implements Comparable<TradeDTO> {

	private Date timeStamp = null;

	private StockDTO stockDTO = null;

	private TradeType tradeType = TradeType.BUY;

	private BigDecimal quantity = BigDecimal.ZERO;

	private BigDecimal price = BigDecimal.ZERO;

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public StockDTO getStockDTO() {
		return stockDTO;
	}

	public void setStockDTO(StockDTO stockDTO) {
		this.stockDTO = stockDTO;
	}

	public TradeType getTradeType() {
		return tradeType;
	}

	public void setTradeType(TradeType tradeType) {
		this.tradeType = tradeType;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int compareTo(TradeDTO tradeDTO) {
		if (this.getTimeStamp() == null || tradeDTO.getTimeStamp() == null)
			return 0;
		return this.getTimeStamp().compareTo(tradeDTO.getTimeStamp());
	}
}
