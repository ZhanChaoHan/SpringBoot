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
package example.springdata.mongodb.people;

import static org.assertj.core.api.Assertions.*;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.test.context.junit4.SpringRunner;

import com.mongodb.reactivestreams.client.MongoCollection;

/**
 * Integration test for {@link RxJava2PersonRepository} using RxJava2 types. Note that {@link ReactiveMongoOperations}
 * is only available using Project Reactor types as the native Template API implementation does not come in multiple
 * reactive flavors.
 *
 * @author Mark Paluch
 * @author Jens Schauder
 * @author Christoph Strobl
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RxJava2PersonRepositoryIntegrationTest {

	@Autowired RxJava2PersonRepository repository;
	@Autowired ReactiveMongoOperations operations;

	@Before
	public void setUp() {

		Mono<MongoCollection<Document>> recreateCollection = operations.collectionExists(Person.class) //
				.flatMap(exists -> exists ? operations.dropCollection(Person.class) : Mono.just(exists)) //
				.then(operations.createCollection(Person.class, CollectionOptions.empty() //
						.size(1024 * 1024) //
						.maxDocuments(100) //
						.capped()));

		StepVerifier.create(recreateCollection).expectNextCount(1).verifyComplete();

		repository.saveAll(Flowable.just(new Person("Walter", "White", 50), //
				new Person("Skyler", "White", 45), //
				new Person("Saul", "Goodman", 42), //
				new Person("Jesse", "Pinkman", 27))) //
				.test() //
				.awaitCount(4) //
				.assertNoErrors() //
				.awaitTerminalEvent();
	}

	/**
	 * This sample performs a count, inserts data and performs a count again using reactive operator chaining. It prints
	 * the two counts ({@code 4} and {@code 6}) to the console.
	 */
	@Test
	public void shouldInsertAndCountData() {

		Flowable<Person> people = Flowable.just(new Person("Hank", "Schrader", 43), //
				new Person("Mike", "Ehrmantraut", 62));

		repository.count() //
				.doOnSuccess(System.out::println) //
				.toFlowable() //
				.switchMap(count -> repository.saveAll(people)) //
				.lastElement() //
				.toSingle() //
				.flatMap(v -> repository.count()) //
				.doOnSuccess(System.out::println) //
				.test() //
				.awaitCount(1) //
				.assertValue(6L) //
				.assertNoErrors() //
				.awaitTerminalEvent();
	}

	/**
	 * Note that the all object conversions are performed before the results are printed to the console.
	 */
	@Test
	public void shouldPerformConversionBeforeResultProcessing() {

		repository.findAll() //
				.doOnNext(System.out::println) //
				.test() //
				.awaitCount(4) //
				.assertNoErrors() //
				.awaitTerminalEvent();

	}

	/**
	 * A tailable cursor streams data using {@link Flowable} as it arrives inside the capped collection.
	 */
	@Test
	public void shouldStreamDataWithTailableCursor() throws Exception {

		Queue<Person> people = new ConcurrentLinkedQueue<>();

		Disposable subscription = repository.findWithTailableCursorBy() //
				.doOnNext(System.out::println) //
				.doOnNext(people::add) //
				.doOnComplete(() -> System.out.println("Complete")) //
				.doOnTerminate(() -> System.out.println("Terminated")) //
				.subscribe();

		Thread.sleep(100);

		repository.save(new Person("Tuco", "Salamanca", 33)).test().awaitTerminalEvent();
		Thread.sleep(100);

		repository.save(new Person("Mike", "Ehrmantraut", 62)).test().awaitTerminalEvent();
		Thread.sleep(100);

		subscription.dispose();

		repository.save(new Person("Gus", "Fring", 53)).test().awaitTerminalEvent();
		Thread.sleep(100);

		assertThat(people).hasSize(6);
	}

	/**
	 * Fetch data using query derivation.
	 */
	@Test
	public void shouldQueryDataWithQueryDerivation() {

		repository.findByLastname("White") //
				.test() //
				.awaitCount(2) //
				.assertNoErrors() //
				.awaitTerminalEvent();

	}

	/**
	 * Fetch data using a string query.
	 */
	@Test
	public void shouldQueryDataWithStringQuery() {

		repository.findByFirstnameAndLastname("Walter", "White") //
				.test() //
				.awaitCount(1) //
				.assertNoErrors() //
				.awaitTerminalEvent();
	}

	/**
	 * Fetch data using query derivation.
	 */
	@Test
	public void shouldQueryDataWithDeferredQueryDerivation() {

		repository.findByLastname(Single.just("White")) //
				.test() //
				.awaitCount(2) //
				.assertNoErrors() //
				.awaitTerminalEvent();
	}

	/**
	 * Fetch data using query derivation and deferred parameter resolution.
	 */
	@Test
	public void shouldQueryDataWithMixedDeferredQueryDerivation() {

		repository.findByFirstnameAndLastname(Single.just("Walter"), "White") //
				.test() //
				.awaitCount(1) //
				.assertNoErrors() //
				.awaitTerminalEvent();
	}
}
