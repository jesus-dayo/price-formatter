package com.priceformatter.price.formatter;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import java.math.BigDecimal;

import com.priceformatter.price.dto.InstrumentConfigDTO;
import com.priceformatter.price.dto.PriceDisplayDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.priceformatter.price.dto.FormattedPriceDTO;

public class PriceTrimFormatterTest {

	@Mock
	private IPriceFormatter zeroFormatter;
	
	@InjectMocks
	private PriceTrimFormatter priceFormatter;
	
	@Before
	public void init() {
	    MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void formatPriceTruncated_givenDecimalInstrument() {
		when(zeroFormatter.formatPrice(any(PriceDisplayDTO.class)))
			.thenReturn(new FormattedPriceDTO("4","7.9","200"));
		PriceDisplayDTO price = new PriceDisplayDTO(
				new BigDecimal("47.92"),
				new InstrumentConfigDTO(0,2,3,4));
		FormattedPriceDTO formattedPriceDTO = priceFormatter.formatPrice(price);
		assertTrue("4".equals(formattedPriceDTO.getBigFigure()));
		assertTrue("7.9".equals(formattedPriceDTO.getDealingPrice()));
		assertTrue("2".equals(formattedPriceDTO.getFractionalPips()));
	}
	
	@Test
	public void formatPriceTruncated_givenPercentageInstrument() {
		when(zeroFormatter.formatPrice(any(PriceDisplayDTO.class))).thenReturn(new FormattedPriceDTO("16.7"
				,"5","40"));
		PriceDisplayDTO price = new PriceDisplayDTO(
				new BigDecimal("0.16754"),
				new InstrumentConfigDTO(1,1,2,4));
		FormattedPriceDTO formattedPriceDTO = priceFormatter.formatPrice(price);
		assertTrue("16.7".equals(formattedPriceDTO.getBigFigure()));
		assertTrue("5".equals(formattedPriceDTO.getDealingPrice()));
		assertTrue("4".equals(formattedPriceDTO.getFractionalPips()));
	}
	
	@Test
	public void formatPriceTruncated_givenPercentageTrailZeroInstrument() {
		when(zeroFormatter.formatPrice(any(PriceDisplayDTO.class))).thenReturn(new FormattedPriceDTO("15.2"
				,"3","00"));
		PriceDisplayDTO price = new PriceDisplayDTO(new BigDecimal(0.1523),
				new InstrumentConfigDTO(1,1,2,4));
		FormattedPriceDTO formattedPriceDTO = priceFormatter.formatPrice(price);
		assertTrue("15.2".equals(formattedPriceDTO.getBigFigure()));
		assertTrue("3".equals(formattedPriceDTO.getDealingPrice()));
		assertTrue("".equals(formattedPriceDTO.getFractionalPips()));
	}
	
	@Test
	public void formatPriceTruncated_givenBasisPointInstrument() {
		when(zeroFormatter.formatPrice(any(PriceDisplayDTO.class))).thenReturn(new FormattedPriceDTO(""
				,"1.8","0"));
		PriceDisplayDTO price = new PriceDisplayDTO(
				new BigDecimal("0.0018"),
				new InstrumentConfigDTO(3,3,1,2));
		FormattedPriceDTO formattedPriceDTO = priceFormatter.formatPrice(price);
		assertTrue("".equals(formattedPriceDTO.getBigFigure()));
		assertTrue("1.8".equals(formattedPriceDTO.getDealingPrice()));
		assertTrue("".equals(formattedPriceDTO.getFractionalPips()));
	}
	
	@Test
	public void formatPriceTruncated_givenPerMileInstrument() {
		when(zeroFormatter.formatPrice(any(PriceDisplayDTO.class))).thenReturn(new FormattedPriceDTO(""
				,"18.0","00"));
		PriceDisplayDTO price = new PriceDisplayDTO(
				new BigDecimal("0.0018"),
				new InstrumentConfigDTO(2,3,1,2));
		FormattedPriceDTO formattedPriceDTO = priceFormatter.formatPrice(price);
		assertTrue("".equals(formattedPriceDTO.getBigFigure()));
		assertTrue("18.0".equals(formattedPriceDTO.getDealingPrice()));
		assertTrue("".equals(formattedPriceDTO.getFractionalPips()));
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
