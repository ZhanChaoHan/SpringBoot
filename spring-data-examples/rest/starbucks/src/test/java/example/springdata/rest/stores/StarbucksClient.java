/*
 * Copyright 2014-2019 the original author or authors.
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
package example.springdata.rest.stores;

import static org.springframework.hateoas.MediaTypes.*;

import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.StreamSupport;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.PagedModel.PageMetadata;
import org.springframework.hateoas.client.Traverson;
import org.springframework.hateoas.client.Traverson.TraversalBuilder;
import org.springframework.hateoas.server.core.TypeReferences.CollectionModelType;
import org.springframework.hateoas.server.core.TypeReferences.EntityModelType;
import org.springframework.hateoas.server.core.TypeReferences.PagedModelType;
import org.springframework.http.RequestEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

/**
 * A test case to discover the search resource and execute a predefined search with it.
 *
 * @author Oliver Gierke
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Slf4j
public class StarbucksClient {

	@SpringBootApplication
	static class Config {

		@Bean
		public RestTemplate restTemplate() {
			return new RestTemplate();
		}
	}

	@LocalServerPort int port;

	private static final String SERVICE_URI = "http://localhost:%s/api";

	@Test
	public void discoverStoreSearch() {

		Traverson traverson = new Traverson(URI.create(String.format(SERVICE_URI, port)), MediaTypes.HAL_JSON);

		// Set up path traversal
		TraversalBuilder builder = traverson. //
				follow("stores", "search", "by-location");

		// Log discovered
		log.info("");
		log.info("Discovered link: {}", builder.asTemplatedLink());
		log.info("");

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("location", "40.740337,-73.995146");
		parameters.put("distance", "0.5miles");

		PagedModel<EntityModel<Store>> resources = builder.//
				withTemplateParameters(parameters).//
				toObject(new PagedModelType<EntityModel<Store>>() {});

		PageMetadata metadata = resources.getMetadata();

		log.info("Got {} of {} stores: ", resources.getContent().size(), metadata.getTotalElements());

		StreamSupport.stream(resources.spliterator(), false).//
				map(EntityModel::getContent).//
				forEach(store -> log.info("{} - {}", store.name, store.address));
	}

	@Autowired RestOperations restOperations;

	@Test
	public void accessServiceUsingRestTemplate() {

		// Access root resource

		URI uri = URI.create(String.format(SERVICE_URI, port));
		RequestEntity<Void> request = RequestEntity.get(uri).accept(HAL_JSON).build();
		EntityModel<Object> rootLinks = restOperations.exchange(request, new EntityModelType<Object>() {}).getBody();
		Links links = rootLinks.getLinks();

		// Follow stores link

		Link storesLink = links.getRequiredLink("stores").expand();

		request = RequestEntity.get(URI.create(storesLink.getHref())).accept(HAL_JSON).build();
		CollectionModel<Store> stores = restOperations.exchange(request, new CollectionModelType<Store>() {}).getBody();

		stores.getContent().forEach(store -> log.info("{} - {}", store.name, store.address));
	}

	static class Store {

		public String name;
		public Address address;

		static class Address {

			public String city, zip, street;
			public Location location;

			@Override
			public String toString() {
				return String.format("%s, %s %s - lat: %s, long: %s", street, zip, city, location.y, location.x);
			}

			static class Location {
				public double x, y;
			}
		}
	}
}
