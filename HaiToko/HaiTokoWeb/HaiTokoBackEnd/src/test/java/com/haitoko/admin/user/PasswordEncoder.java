package com.haitoko.admin.user;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

	@Test
	public void testEncoder() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		String rawPass = "akbar";
		String encodedPass = passwordEncoder.encode(rawPass);
		
		System.out.println();
		
	}
}
