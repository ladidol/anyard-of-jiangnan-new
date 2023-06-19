package org.cuit.fhzheng.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication(scanBasePackages = { "org.cuit.fhzheng" })
@EnableFeignClients(basePackages = {"org.cuit.fhzheng.api.**.feign"})
public class PlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlatformApplication.class, args);
	}

}
