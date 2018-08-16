package com.liuzhen.wxgzh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication()
//@MapperScan("com.liuzhen.wxgzh.dao.inter")
public class WxgzhApplication /*extends SpringBootServletInitializer*/ {

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(WxgzhApplication.class);
//	}

	public static void main(String[] args) {
		SpringApplication.run(WxgzhApplication.class, args);
	}
}
