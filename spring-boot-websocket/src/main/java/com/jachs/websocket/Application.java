package com.jachs.websocket;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/***
 * 
 * @author zhanchaohan
 *
 */
@SpringBootApplication
@MapperScan("com.jachs.websocket.dao")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
