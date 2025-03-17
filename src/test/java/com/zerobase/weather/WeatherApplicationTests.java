package com.zerobase.weather;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class WeatherApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testApiKey(){
		String apiKey = System.getenv("API_KEY");
		assertNotNull(apiKey,"apiKey is null");
		System.out.println(apiKey);
	}

}