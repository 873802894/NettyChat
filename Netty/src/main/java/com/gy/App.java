package com.gy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan({"com.gy.mapper"})
@ComponentScan({"com.gy.idworker","com.gy"})
public class App {

    @Bean
    public SpringUtil getSpingUtil() {
	return new SpringUtil();
    }

    public static void main(String[] args) {

	SpringApplication.run(App.class,args);
    }
}
