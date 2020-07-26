package com.priceformatter.price.formatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.priceformatter.price.dto.FormattedPriceDTO;
import com.priceformatter.price.dto.PriceDisplayDTO;

@Component
@Qualifier("priceTrimFormat")
public class PriceTrimFormatter extends AbstractPriceFormatter{

	@Autowired
	@Qualifier("priceZeroFormat")
	private IPriceFormatter zeroFormatter;

	@Override
	public FormattedPriceDTO buildFormattedPrice(PriceDisplayDTO price) {
		FormattedPriceDTO formattedPriceDTO = zeroFormatter.formatPrice(price);
		formattedPriceDTO.setFractionalPips(removeTrailingZero(formattedPriceDTO.getFractionalPips()));
		return formattedPriceDTO;
	}

}
