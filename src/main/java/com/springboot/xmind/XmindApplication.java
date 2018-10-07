package com.springboot.xmind;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class XmindApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(XmindApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
		System.out.println("外部tomcat,chapter启动!");
		return application.sources(XmindApplication.class);
	}

}
