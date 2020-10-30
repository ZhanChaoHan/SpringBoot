/*
 * Copyright 2017-2020 the original author or authors.
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
package example.springdata.couchbase.repository;

import example.springdata.couchbase.model.Airline;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface to manage {@link Airline} instances.
 *
 * @author Chandana Kithalagama
 * @author Mark Paluch
 * @author Denis Rosa
 */
public interface AirlineRepository extends CrudRepository<Airline, String> {

	/**
	 * Derived query selecting by {@code iataCode}.
	 *
	 * @param code
	 * @return
	 */
	List<Airline> findByIata(String code);

	/**
	 * Query method using {@code airlines/all} view.
	 *
	 * @return
	 */
	List<Airline> findAllBy();
}
