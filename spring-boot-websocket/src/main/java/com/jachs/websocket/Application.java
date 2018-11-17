package com.jachs.websocket;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.jachs.websocket.entity.Status;


@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
//		for (Status status : Status.values()) {
//			System.out.println(status);
//		}
	}
}
