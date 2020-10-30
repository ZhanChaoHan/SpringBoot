/*
 * Copyright 2019 the original author or authors.
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

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.*
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.test.context.junit4.SpringRunner
import reactor.test.StepVerifier

/**
 * Tests showing Coroutine capabilities.
 *
 * @author Christoph Strobl
 */
@RunWith(SpringRunner::class)
@SpringBootTest
class FlowAndCoroutinesTests {

	@Autowired
	lateinit var operations: ReactiveMongoOperations

	@Before
	fun before() {
		StepVerifier.create(operations.dropCollection<Person>()).verifyComplete()
	}

	@Test
	fun `find - the coroutine way`() {

		StepVerifier.create(operations.insert<Person>().one(Person("Tyrion", "Lannister"))).expectNextCount(1).verifyComplete()

		assertThat(
				runBlocking {
					operations.find<Person>(Query(where("firstname").isEqualTo("Tyrion"))).awaitSingle()
				}
		).extracting("firstname").isEqualTo("Tyrion")
	}

	@Test
	@ExperimentalCoroutinesApi
	fun `find - the flow way`() {

		StepVerifier.create(operations.insert<Person>().one(Person("Tyrion", "Lannister"))).expectNextCount(1).verifyComplete()
		StepVerifier.create(operations.insert<Person>().one(Person("Cersei", "Lannister"))).expectNextCount(1).verifyComplete()

		assertThat(
				runBlocking {
					operations.findAll<Person>().asFlow().toList()
				}
		).extracting("firstname").containsExactlyInAnyOrder("Tyrion", "Cersei")
	}
}
