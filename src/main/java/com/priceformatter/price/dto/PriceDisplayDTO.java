package com.priceformatter.price.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@SuperBuilder
@NoArgsConstructor
@ApiModel(value="PriceFormat",description="details required for formatting the raw price.")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceDisplayDTO {

	@ApiModelProperty(value="Raw Price")
	@NotNull(message="Price is Required")
	@Min(value=0,message="Negative raw price is not allowed.")
	private BigDecimal price;
	@ApiModelProperty(value="Instrument Config")
	@NotNull(message="Config is Required")
	private InstrumentConfigDTO config;

	@JsonIgnore
	private char[] longPriceChar;

	public PriceDisplayDTO(BigDecimal price, InstrumentConfigDTO config) {
		super();
		this.price = price;
		this.config = config;
	}
}
