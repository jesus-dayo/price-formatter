package com.priceformatter;

import com.priceformatter.config.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.priceformatter.config.SwaggerConfig;

@SpringBootApplication
@Import({WebConfig.class,SwaggerConfig.class})
public class ExamApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamApplication.class, args);
	}

}

