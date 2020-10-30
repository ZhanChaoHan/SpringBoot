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
package example.springdata.mongodb.reactive;

import example.springdata.mongodb.Process;
import example.springdata.mongodb.State;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author Christoph Strobl
 * @currentRead The Core - Peter V. Brett
 */
@Service
@RequiredArgsConstructor
public class ReactiveTransitionService {

	final ReactiveProcessRepository repository;
	final ReactiveMongoTemplate template;

	final AtomicInteger counter = new AtomicInteger(0);

	public Mono<Process> newProcess() {
		return repository.save(new Process(counter.incrementAndGet(), State.CREATED, 0));
	}

	public Mono<Integer> run(Integer id) {

		return template.inTransaction().execute(action -> {

			return lookup(id) //
					.flatMap(process -> start(action, process)) //
					.flatMap(it -> verify(it)) //
					.flatMap(process -> finish(action, process));

		}).next().map(Process::getId);
	}

	private Mono<Process> finish(ReactiveMongoOperations operations, Process process) {

		return operations.update(Process.class).matching(Query.query(Criteria.where("id").is(process.getId())))
				.apply(Update.update("state", State.DONE).inc("transitionCount", 1)).first() //
				.then(Mono.just(process));
	}

	Mono<Process> start(ReactiveMongoOperations operations, Process process) {

		return operations.update(Process.class).matching(Query.query(Criteria.where("id").is(process.getId())))
				.apply(Update.update("state", State.ACTIVE).inc("transitionCount", 1)).first() //
				.then(Mono.just(process));
	}

	Mono<Process> lookup(Integer id) {
		return repository.findById(id);
	}

	Mono<Process> verify(Process process) {

		Assert.state(process.getId() % 3 != 0, "We're sorry but we needed to drop that one");
		return Mono.just(process);
	}
}
