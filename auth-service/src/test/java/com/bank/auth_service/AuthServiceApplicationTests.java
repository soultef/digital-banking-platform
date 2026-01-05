package com.bank.auth_service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class AuthServiceApplicationTests {

	@Autowired
	private Environment env;

	@Test
	void shouldPrintActiveProfile() {
		System.out.println("Active profiles: " + Arrays.toString(env.getActiveProfiles()));
		assertThat(env.getActiveProfiles()).contains("test");
	}

}
