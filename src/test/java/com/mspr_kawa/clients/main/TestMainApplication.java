package com.mspr_kawa.clients.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.*;
import org.springframework.context.annotation.Bean;

import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class TestMainApplication {

	public static void main(String[] args) {
		SpringApplication.from(MainApplication::main).with(TestMainApplication.class).run(args);
	}

}
