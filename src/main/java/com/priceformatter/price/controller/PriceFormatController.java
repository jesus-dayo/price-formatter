package com.priceformatter.price.controller;

import javax.validation.Valid;

import com.priceformatter.constants.RestUrl;
import com.priceformatter.price.formatter.IPriceFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.priceformatter.price.dto.FormattedPriceDTO;
import com.priceformatter.price.dto.PriceDisplayDTO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = RestUrl.PRICE_REQUEST_URI)
public class PriceFormatController {

    private static final String FORMAT_PRICE_WITH_TRIM_ZERO = "Format price with trim zero";
    private static final String FORMAT_PRICE_WITH_TRAILING_ZERO = "Format price with trailing zero";
    private static final String DISPLAY_FORMAT_REMINDER = "DisplayFormat valid values <br> 0=DECIMAL,<br> 1=PERCENTAGE, <br>2=PERMILE, <br>3=BASISPOINT";

    @Autowired
    @Qualifier("priceZeroFormat")
    private IPriceFormatter priceZeroFormatter;

    @Autowired
    @Qualifier("priceTrimFormat")
    private IPriceFormatter priceTrimZeroFormatter;

    @RequestMapping(method = RequestMethod.POST,
            value = RestUrl.PRICE_REQUEST_FORMAT_ZERO_URI,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(
            value = FORMAT_PRICE_WITH_TRAILING_ZERO,
            notes = FORMAT_PRICE_WITH_TRAILING_ZERO,
            response = FormattedPriceDTO.class
    )
    public ResponseEntity<FormattedPriceDTO> priceZeroFormat(
            @ApiParam(value = DISPLAY_FORMAT_REMINDER, required = true)
            @Valid @RequestBody PriceDisplayDTO priceDisplay) {
        return new ResponseEntity<>(priceZeroFormatter.formatPrice(priceDisplay), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,
            value = RestUrl.PRICE_REQUEST_FORMAT_TRIM_ZERO_URI,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(
            value = FORMAT_PRICE_WITH_TRIM_ZERO,
            notes = FORMAT_PRICE_WITH_TRIM_ZERO,
            response = FormattedPriceDTO.class
    )
    public ResponseEntity<FormattedPriceDTO> priceTrimZeroFormat(
            @ApiParam(value = DISPLAY_FORMAT_REMINDER, required = true)
            @Valid @RequestBody PriceDisplayDTO priceDisplay) {
        return new ResponseEntity<>(priceTrimZeroFormatter.formatPrice(priceDisplay), HttpStatus.OK);
    }


}
