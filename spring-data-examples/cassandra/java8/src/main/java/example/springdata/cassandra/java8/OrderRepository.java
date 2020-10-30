/*
 * Copyright 2016-2018 the original author or authors.
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
package example.springdata.cassandra.java8;

import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.Repository;

/**
 * Repository to manage {@link Order} instances.
 *
 * @author Mark Paluch
 */
public interface OrderRepository extends Repository<Order, String> {

	/**
	 * Method parameters are converted according the registered Converters into Cassandra types.
	 */
	@Query("SELECT * from pizza_orders WHERE orderdate = ?0 and zoneid = ?1 ALLOW FILTERING")
	Order findOrderByOrderDateAndZoneId(LocalDate orderDate, ZoneId zoneId);

	void deleteAll();

	Order save(Order order);
}
