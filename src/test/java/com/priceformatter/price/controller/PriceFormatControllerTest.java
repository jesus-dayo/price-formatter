package com.priceformatter.price.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import com.priceformatter.constants.RestUrl;
import com.priceformatter.price.dto.InstrumentConfigDTO;
import com.priceformatter.price.dto.PriceDisplayDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(PriceFormatController.class)
public class PriceFormatControllerTest {
	
	@Autowired
	private MockMvc mvc;

	@Test
	public void givenValidDecimal_whenPriceFormat_thenReturnOk() throws Exception {
		PriceDisplayDTO dto = new PriceDisplayDTO();
		dto.setPrice(new BigDecimal(47.92));
		dto.setConfig(new InstrumentConfigDTO(0, 2, 3, 4));
		Gson gson = new Gson();
	    String json = gson.toJson(dto);
		this.mvc.perform(MockMvcRequestBuilders.post(RestUrl.PRICE_REQUEST_URI+RestUrl.PRICE_REQUEST_FORMAT_ZERO_URI)
			.content(json)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
	}
	
	@Test
	public void givenNullRawPrice_whenPriceFormat_thenReturnError() throws Exception {
		PriceDisplayDTO dto = new PriceDisplayDTO();
		Gson gson = new Gson();
	    String json = gson.toJson(dto);
		this.mvc.perform(post(RestUrl.PRICE_REQUEST_URI+RestUrl.PRICE_REQUEST_FORMAT_ZERO_URI)
			.content(json)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest());
	}
	
	@Test
	public void givenNegativePrice_whenPriceFormat_thenReturnError() throws Exception {
		PriceDisplayDTO dto = new PriceDisplayDTO();
		dto.setPrice(new BigDecimal(-47.92));
		dto.setConfig(new InstrumentConfigDTO(0, 2, 3, 4));
		Gson gson = new Gson();
	    String json = gson.toJson(dto);
		this.mvc.perform(post(RestUrl.PRICE_REQUEST_URI+RestUrl.PRICE_REQUEST_FORMAT_ZERO_URI)
			.content(json)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest());
	}
	
	@Test
	public void givenValidDecimal_whenPriceFormatTrim_thenReturnOk() throws Exception {
		PriceDisplayDTO dto = new PriceDisplayDTO();
		dto.setPrice(new BigDecimal(47.92));
		dto.setConfig(new InstrumentConfigDTO(0, 2, 3, 4));
		Gson gson = new Gson();
	    String json = gson.toJson(dto);
		this.mvc.perform(post(RestUrl.PRICE_REQUEST_URI+RestUrl.PRICE_REQUEST_FORMAT_TRIM_ZERO_URI)
			.content(json)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
	}
	
	@Test
	public void givenNullRawPrice_whenPriceFormatTrim_thenReturnError() throws Exception {
		PriceDisplayDTO dto = new PriceDisplayDTO();
		Gson gson = new Gson();
	    String json = gson.toJson(dto);
		this.mvc.perform(post(RestUrl.PRICE_REQUEST_URI+RestUrl.PRICE_REQUEST_FORMAT_TRIM_ZERO_URI)
			.content(json)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest());
	}
	
	@Test
	public void givenNegativePrice_whenPriceFormatTrim_thenReturnError() throws Exception {
		PriceDisplayDTO dto = new PriceDisplayDTO();
		dto.setPrice(new BigDecimal(-47.92));
		dto.setConfig(new InstrumentConfigDTO(0, 2, 3, 4));
		Gson gson = new Gson();
	    String json = gson.toJson(dto);
		this.mvc.perform(post(RestUrl.PRICE_REQUEST_URI+RestUrl.PRICE_REQUEST_FORMAT_TRIM_ZERO_URI)
			.content(json)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest());
	}
	
}
