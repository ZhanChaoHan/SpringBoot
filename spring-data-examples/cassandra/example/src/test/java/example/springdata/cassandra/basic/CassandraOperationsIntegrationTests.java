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
package example.springdata.cassandra.basic;

import static org.assertj.core.api.Assertions.*;

import example.springdata.cassandra.util.CassandraKeyspace;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.cassandra.core.AsyncCassandraTemplate;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.insert.Insert;

/**
 * Integration test showing the basic usage of {@link CassandraTemplate}.
 *
 * @author Mark Paluch
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BasicConfiguration.class)
public class CassandraOperationsIntegrationTests {

	@ClassRule public final static CassandraKeyspace CASSANDRA_KEYSPACE = CassandraKeyspace.onLocalhost();

	@Autowired CqlSession session;
	@Autowired CassandraOperations template;

	@Before
	public void setUp() throws Exception {
		template.getCqlOperations().execute("TRUNCATE users");
	}

	/**
	 * Cassandra {@link com.datastax.driver.core.Statement}s can be used together with {@link CassandraTemplate} and the
	 * mapping layer.
	 */
	@Test
	public void insertAndSelect() {

		Insert insert = QueryBuilder.insertInto("users").value("user_id", QueryBuilder.literal(42L)) //
				.value("uname", QueryBuilder.literal("heisenberg")) //
				.value("fname", QueryBuilder.literal("Walter")) //
				.value("lname", QueryBuilder.literal("White")) //
				.ifNotExists(); //

		template.getCqlOperations().execute(insert.asCql());

		User user = template.selectOneById(42L, User.class);
		assertThat(user.getUsername()).isEqualTo("heisenberg");

		List<User> users = template.select(QueryBuilder.selectFrom("users").all().asCql(), User.class);
		assertThat(users).hasSize(1);
		assertThat(users.get(0)).isEqualTo(user);
	}

	/**
	 * Objects can be inserted and updated using {@link CassandraTemplate}. What you {@code update} is what you
	 * {@code select}.
	 */
	@Test
	public void insertAndUpdate() {

		User user = new User();
		user.setId(42L);
		user.setUsername("heisenberg");
		user.setFirstname("Walter");
		user.setLastname("White");

		template.insert(user);

		user.setFirstname(null);
		template.update(user);

		User loaded = template.selectOneById(42L, User.class);
		assertThat(loaded.getUsername()).isEqualTo("heisenberg");
		assertThat(loaded.getFirstname()).isNull();
	}

	/**
	 * Asynchronous query execution using callbacks.
	 */
	@Test
	public void insertAsynchronously() throws InterruptedException {

		User user = new User();
		user.setId(42L);
		user.setUsername("heisenberg");
		user.setFirstname("Walter");
		user.setLastname("White");

		final CountDownLatch countDownLatch = new CountDownLatch(1);

		AsyncCassandraTemplate asyncTemplate = new AsyncCassandraTemplate(session);

		ListenableFuture<User> future = asyncTemplate.insert(user);

		future.addCallback(it -> countDownLatch.countDown(), throwable -> countDownLatch.countDown());

		countDownLatch.await(5, TimeUnit.SECONDS);

		User loaded = template.selectOneById(user.getId(), User.class);
		assertThat(loaded).isEqualTo(user);
	}

	/**
	 * {@link CassandraTemplate} allows selection of projections on template-level. All basic data types including
	 * {@link Row} can be selected.
	 */
	@Test
	@SuppressWarnings("unchecked")
	public void selectProjections() {

		User user = new User();
		user.setId(42L);
		user.setUsername("heisenberg");
		user.setFirstname("Walter");
		user.setLastname("White");

		template.insert(user);

		Long id = template.selectOne(QueryBuilder.selectFrom("users").column("user_id").asCql(), Long.class);
		assertThat(id).isEqualTo(user.getId());

		Row row = template.selectOne(QueryBuilder.selectFrom("users").column("user_id").asCql(), Row.class);
		assertThat(row.getLong(0)).isEqualTo(user.getId());

		Map<String, Object> map = template.selectOne(QueryBuilder.selectFrom("users").all().asCql(), Map.class);
		assertThat(map).containsEntry("user_id", user.getId());
		assertThat(map).containsEntry("fname", "Walter");
	}
}
