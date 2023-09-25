package com.ft.restApiCreator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestApiCreatorApplication {

	public static void main(String[] args) {

		var ctx=SpringApplication.run(RestApiCreatorApplication.class, args);
		var a = ctx.getBeanDefinitionNames();
		for (int i = 0; i < a.length; i++) {
			System.out.println(i+" = " + a[i]);
		}
	}

}
