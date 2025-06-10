package com.piggymetrics.account;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AccountServiceApplication.class)
@ActiveProfiles(profiles = { "test" })
class AccountServiceApplicationTests {
	@Test
	void test() {

	}
}
