package com.priceformatter;

import com.priceformatter.config.SwaggerConfig;
import com.priceformatter.config.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({WebConfig.class,SwaggerConfig.class})
public class PriceFormatterApplication {

	public static void main(String[] args) {
		SpringApplication.run(PriceFormatterApplication.class, args);
	}

}

