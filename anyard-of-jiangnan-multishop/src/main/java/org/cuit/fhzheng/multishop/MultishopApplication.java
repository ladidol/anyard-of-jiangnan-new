package org.cuit.fhzheng.multishop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication(scanBasePackages = { "org.cuit.fhzheng" })
@EnableFeignClients(basePackages = {"org.cuit.fhzheng.api.**.feign"})
public class MultishopApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultishopApplication.class, args);
	}

}
