package com.priceformatter.price.formatter;

import com.priceformatter.constants.DisplayFormat;
import com.priceformatter.price.dto.FormattedPriceDTO;
import com.priceformatter.price.dto.PriceDisplayDTO;
import com.priceformatter.util.PriceFormatHelper;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public abstract class AbstractPriceFormatter implements IPriceFormatter {

    public abstract FormattedPriceDTO buildFormattedPrice(PriceDisplayDTO price);

    @Override
    public FormattedPriceDTO formatPrice(PriceDisplayDTO price) {
        validate(price);
        return buildFormattedPrice(price);
    }

    public void validate(PriceDisplayDTO price) {
        throwErrorNullPriceOrInstruments(price);
        throwErrorInvalidDisplayFormat(price);
        throwErrorNegativePrice(price);
    }

    protected void throwErrorNegativePrice(PriceDisplayDTO priceDisplay) {
        validateNegativeRawPrice(priceDisplay);
        validateNegativeDplFplScale(priceDisplay);
    }

    private void validateNegativeDplFplScale(PriceDisplayDTO priceDisplay) {
        if (priceDisplay.getConfig().getDpl() < 0 || priceDisplay.getConfig().getFpl() < 0 || priceDisplay.getConfig().getScale() < 0) {
            throw new IllegalArgumentException("Instrument config dpl,fpl,scale cannot be negative");
        }
    }

    private void validateNegativeRawPrice(PriceDisplayDTO priceDisplay) {
        if (priceDisplay.getPrice().signum() == -1) {
            throw new IllegalArgumentException("Raw Price cannot be negative");
        }
    }

    protected void throwErrorInvalidDisplayFormat(PriceDisplayDTO priceDisplay) {
        DisplayFormat.getFactorByKey(priceDisplay.getConfig().getDisplayFormat());
    }

    protected void throwErrorNullPriceOrInstruments(PriceDisplayDTO priceDisplay) {
        if (priceDisplay.getPrice() == null || priceDisplay.getConfig() == null) {
            throw new IllegalArgumentException("Price or Instrument cannot be null");
        }
    }

    protected String getLongStringPrice(BigDecimal price, int factor, int scale) {
        return PriceFormatHelper.buildLongPriceStrTrailZero(price.multiply(new BigDecimal(String.valueOf(factor)))
                .setScale(scale, RoundingMode.HALF_UP), scale);
    }

    protected char[] push(char[] arr, char item) {
        char[] tmp = Arrays.copyOf(arr, arr.length + 1);
        tmp[tmp.length - 1] = item;
        return tmp;
    }

    protected char[] pop(char[] arr) {
        char[] tmp = Arrays.copyOf(arr, arr.length - 1);
        return tmp;
    }

    protected char[] reverseCharArr(char[] arr) {
        for (int left = 0, right = arr.length - 1; left < right; left++, right--) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        }
        return arr;
    }

    protected String removeTrailingZero(String value) {
        char[] originalValue = value.toCharArray();
        while (originalValue.length > 0 && originalValue[originalValue.length - 1] == '0') {
            originalValue = pop(originalValue);
        }
        return String.valueOf(originalValue).trim();
    }

    protected String getPartOfPrice(PriceDisplayDTO priceDisplay,int length) {
        char[] longPriceChar = priceDisplay.getLongPriceChar();
        if (longPriceChar.length == 0) {
            return StringUtils.EMPTY;
        }

        char[] result = new char[0];
        int count = 0;
        while (count != length && longPriceChar.length > 0) {
            result = push(result,getLastCharElement(longPriceChar));
            if (getLastCharElement(longPriceChar) != '.') {
                count++;
            }
            longPriceChar = pop(longPriceChar);
        }
        priceDisplay.setLongPriceChar(longPriceChar);
        return String.valueOf(reverseCharArr(result));
    }

    protected char getLastCharElement(char[] longPriceChar) {
        return longPriceChar[(longPriceChar.length - 1)];
    }
}
