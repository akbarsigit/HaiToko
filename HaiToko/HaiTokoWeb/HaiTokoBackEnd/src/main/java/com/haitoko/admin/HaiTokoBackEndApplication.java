package com.haitoko.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.haitoko.share.entity", "com.haitoko.admin.user"})
public class HaiTokoBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(HaiTokoBackEndApplication.class, args);
	}

}
