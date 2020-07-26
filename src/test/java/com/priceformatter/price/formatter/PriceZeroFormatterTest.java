package com.priceformatter.price.formatter;

import com.priceformatter.price.dto.FormattedPriceDTO;
import com.priceformatter.price.dto.InstrumentConfigDTO;
import com.priceformatter.price.dto.PriceDisplayDTO;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PriceZeroFormatterTest {

	private IPriceFormatter priceFormatter = new PriceZeroFormatter();

	@Test
	public void formatPrice_givenDecimalInstrument() {
		FormattedPriceDTO formattedPriceDTO = priceFormatter.formatPrice(new PriceDisplayDTO(new BigDecimal("47.92"), new InstrumentConfigDTO(0, 2, 3, 4)));
		assertTrue("4".equals(formattedPriceDTO.getBigFigure()));
		assertEquals("7.9", formattedPriceDTO.getDealingPrice());
		assertTrue("200".equals(formattedPriceDTO.getFractionalPips()));
		FormattedPriceDTO formattedPriceDTO2 = priceFormatter.formatPrice(
				new PriceDisplayDTO(new BigDecimal("47.92"), new InstrumentConfigDTO(0, 2, 3, 10)));
		assertTrue("47.92000".equals(formattedPriceDTO2.getBigFigure()));
		assertTrue("00".equals(formattedPriceDTO2.getDealingPrice()));
		assertTrue("000".equals(formattedPriceDTO2.getFractionalPips()));
	}
	
	@Test
	public void formatPrice_givenPercentageInstrument() {
		FormattedPriceDTO formattedPriceDTO = priceFormatter.formatPrice(new PriceDisplayDTO(new BigDecimal("0.16754"), new InstrumentConfigDTO(1, 1, 2, 4)));
		assertTrue("16.7".equals(formattedPriceDTO.getBigFigure()));
		assertTrue("5".equals(formattedPriceDTO.getDealingPrice()));
		assertTrue("40".equals(formattedPriceDTO.getFractionalPips()));
	}

	@Test
	public void formatPrice_givenPercentageTrailZeroInstrument() {
		FormattedPriceDTO formattedPriceDTO = priceFormatter.formatPrice(new PriceDisplayDTO(new BigDecimal("0.1523"), new InstrumentConfigDTO(1, 1, 2, 4)));
		assertTrue("15.2".equals(formattedPriceDTO.getBigFigure()));
		assertTrue("3".equals(formattedPriceDTO.getDealingPrice()));
		assertTrue("00".equals(formattedPriceDTO.getFractionalPips()));
	}
	
	@Test
	public void formatPrice_givenLargeDPL() {
		FormattedPriceDTO formattedPriceDTO = priceFormatter.formatPrice(new PriceDisplayDTO(new BigDecimal("47.92"), 
				new InstrumentConfigDTO(0, 200, 3, 4)));
		assertTrue("".equals(formattedPriceDTO.getBigFigure()));
		assertTrue("47.9".equals(formattedPriceDTO.getDealingPrice()));
		assertTrue("200".equals(formattedPriceDTO.getFractionalPips()));
	}
	
	@Test
	public void formatPrice_givenLargeFPL() {
		FormattedPriceDTO formattedPriceDTO = priceFormatter.formatPrice(new PriceDisplayDTO(new BigDecimal("47.92"), 
				new InstrumentConfigDTO(0, 2, 300, 4)));
		assertTrue("".equals(formattedPriceDTO.getBigFigure()));
		assertTrue("".equals(formattedPriceDTO.getDealingPrice()));
		assertTrue("47.9200".equals(formattedPriceDTO.getFractionalPips()));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void formatPrice_givenNegativeRawPrice() {
		priceFormatter.formatPrice(new PriceDisplayDTO(new BigDecimal("-47.92"), new InstrumentConfigDTO(0, 2, 3, 4)));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void formatPrice_givenNullRawPrice() {
		priceFormatter.formatPrice(new PriceDisplayDTO(null, new InstrumentConfigDTO(0, 2, 3, 4)));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void formatPrice_givenNullInstrument() {
		priceFormatter.formatPrice(new PriceDisplayDTO(new BigDecimal("47.92"), null));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void formatPrice_givenInvalidDisplayFormat() {
		priceFormatter.formatPrice(new PriceDisplayDTO(new BigDecimal("47.92"), new InstrumentConfigDTO(10, 2, 3, 4)));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void formatPrice_givenNegativeDPL() {
		priceFormatter.formatPrice(new PriceDisplayDTO(new BigDecimal("47.92"), new InstrumentConfigDTO(0, -2, 3, 4)));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void formatPrice_givenNegativeFPL() {
		priceFormatter.formatPrice(new PriceDisplayDTO(new BigDecimal("47.92"), new InstrumentConfigDTO(0, 2, -3, 4)));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void formatPrice_givenNegativeScale() {
		priceFormatter.formatPrice(new PriceDisplayDTO(new BigDecimal("47.92"), new InstrumentConfigDTO(0, 2, 3, -4)));
	}
}
