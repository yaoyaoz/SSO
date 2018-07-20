package com.ssoclient.app1.ssoclientapp1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;

@ServletComponentScan
@SpringBootApplication
public class SsoclientApp1Application {

	public static void main(String[] args) {
		SpringApplication.run(SsoclientApp1Application.class, args);
	}
}
