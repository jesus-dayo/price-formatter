package com.priceformatter.price.formatter;


import com.priceformatter.constants.DisplayFormat;
import com.priceformatter.price.dto.FormattedPriceDTO;
import com.priceformatter.price.dto.PriceDisplayDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("priceZeroFormat")
public class PriceZeroFormatter extends AbstractPriceFormatter {

    @Override
    public FormattedPriceDTO buildFormattedPrice(PriceDisplayDTO price) {
        price.setLongPriceChar(getLongStringPrice(price.getPrice(),
                DisplayFormat.getFactorByKey(price.getConfig().getDisplayFormat()),
                price.getConfig().getScale()).toCharArray());
        return FormattedPriceDTO.builder()
                .fractionalPips(getPartOfPrice(price, price.getConfig().getFpl()))
                .dealingPrice(getPartOfPrice(price, price.getConfig().getDpl()))
                .bigFigure(String.valueOf(price.getLongPriceChar())).build();
    }

}
