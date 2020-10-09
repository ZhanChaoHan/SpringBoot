/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.oreilly.springdata.gemfire.core;

import java.util.List;

import org.springframework.data.repository.Repository;

/**
 * Repository interface to access {@link Customer}s.
 * 
 * @author Oliver Gierke
 * @author David Turanski
 */

public interface CustomerRepository extends Repository<Customer, Long> {

	/**
	 * Returns all {@link Customer}s.
	 * 
	 * @return
	 */
	List<Customer> findAll();

	/**
	 * Saves the given {@link Customer}.
	 * 
	 * @param customer the {@link Customer} to save.
	 * @return
	 */
	Customer save(Customer customer);

	/**
	 * Deletes the given {@link Customer}.
	 * 
	 * @param customer
	 */
	void delete(Customer customer);

	/**
	 * Finds all {@link Customer}s with the given lastname.
	 * 
	 * @param lastname
	 * @return
	 */
	List<Customer> findByLastname(String lastname);

	/**
	 * Finds the Customer with the given {@link EmailAddress}.
	 * 
	 * @param emailAddress
	 * @return
	 */
	Customer findByEmailAddress(EmailAddress emailAddress);
}
