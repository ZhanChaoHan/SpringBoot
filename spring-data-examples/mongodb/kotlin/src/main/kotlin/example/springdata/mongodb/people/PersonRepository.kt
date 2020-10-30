/*
 * Copyright 2018 the original author or authors.
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
package example.springdata.mongodb.people

import org.springframework.data.repository.CrudRepository

/**
 * Repository interface to manage [Person] instances.
 *
 * @author Mark Paluch
 */
interface PersonRepository : CrudRepository<Person, String> {

	/**
	 * Query method declaring a nullable return type that allows to return null values.
	 */
	fun findOneOrNoneByFirstname(firstname: String): Person?

	/**
	 * Query method declaring a nullable argument.
	 */
	fun findNullableByFirstname(firstname: String?): Person?

	/**
	 * Query method requiring a result. Throws [org.springframework.dao.EmptyResultDataAccessException] if no result is found.
	 */
	fun findOneByFirstname(firstname: String): Person
}
