package com.priceformatter.util;

import java.math.BigDecimal;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

public class PriceFormatHelper {

    private final static String ZERO = "0";

    private PriceFormatHelper() {
    }

    public static String buildLongPriceStrTrailZero(BigDecimal price, int scale) {
        String priceStr = String.valueOf(price);
        return StringUtils.rightPad(priceStr, getPriceLengthToDecimalPoint(priceStr) + scale, ZERO);
    }

    public static Integer getPriceLengthToDecimalPoint(String price) {
        if (Objects.isNull(price)) {
            return 0;
        }
        if (price.contains(".")) {
            return price.substring(0, price.indexOf(".")).length() + 1;
        }
        return 0;
    }

}
