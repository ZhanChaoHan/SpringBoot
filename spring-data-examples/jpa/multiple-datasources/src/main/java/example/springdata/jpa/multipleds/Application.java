/*
 * Copyright 2015-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package example.springdata.jpa.multipleds;

import example.springdata.jpa.multipleds.customer.Customer.CustomerId;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Core Spring Boot application configuration.
 *
 * @author Oliver Gierke
 * @author Mark Paluch
 * @see example.springdata.jpa.multipleds.customer.CustomerConfig
 * @see example.springdata.jpa.multipleds.order.OrderConfig
 */
@SpringBootApplication
@EnableTransactionManagement
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired DataInitializer initializer;

	@PostConstruct
	public void init() {

		CustomerId customerId = initializer.initializeCustomer();
		initializer.initializeOrder(customerId);
	}
}
