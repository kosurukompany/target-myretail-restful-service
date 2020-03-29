package com.target.casestudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Satya Kosuru
 *
 */

@PropertySource("classpath:instance-based.properties")
@SpringBootApplication
public class MyretailRestapiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyretailRestapiServiceApplication.class, args);
	}

}
