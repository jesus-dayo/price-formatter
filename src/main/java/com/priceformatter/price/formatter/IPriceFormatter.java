package com.priceformatter.price.formatter;

import com.priceformatter.price.dto.FormattedPriceDTO;
import com.priceformatter.price.dto.PriceDisplayDTO;

public interface IPriceFormatter {

	FormattedPriceDTO formatPrice(PriceDisplayDTO price);

}
