package com.priceformatter.price.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@SuperBuilder
@NoArgsConstructor
@ApiModel(value="Instrument",description="config to display the formatted price")
@JsonIgnoreProperties(ignoreUnknown = true)
public class InstrumentConfigDTO {

	@ApiModelProperty(value="0=DECIMAL,1=PERCENTAGE,2=PERMILE,3=BASISPOINT")
	@Min(value=0,message="min is 0")
    @Max(value=3,message="max is 3")
	private int displayFormat;
	@Min(value=0,message="min is 0")
	private int scale;
	@Min(value=0,message="min is 0")
	private int dpl;
	@Min(value=0,message="min is 0")
	private int fpl;

	public InstrumentConfigDTO(int displayFormat, int dpl,int fpl,int scale) {
		super();
		this.displayFormat = displayFormat;
		this.scale = scale;
		this.dpl = dpl;
		this.fpl = fpl;
	}

}
