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
package com.oreilly.springdata.rest.client;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import org.springframework.web.client.RestOperations;

/**
 * Sample client to access {@link Products} exposed by the REST exporter.
 * 
 * @author Oliver Gierke
 */
class ProductsClient {

	/**
	 * A sample DTO to have a {@link Resource} object typed to {@link com.oreilly.springdata.rest.core.Product}.
	 * 
	 * @author Oliver Gierke
	 */
	static class Product extends Resource<com.oreilly.springdata.rest.core.Product> {

	}

	/**
	 * A DTO for a paged collection resource of {@link Product}s.
	 * 
	 * @author Oliver Gierke
	 */
	static class Products extends Resources<Product> {

	}

	public static void main(String[] args) {

		// Bootstrap RestOperations instance using Spring
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(ClientConfiguration.class);
		context.registerShutdownHook();
		RestOperations operations = context.getBean(RestOperations.class);

		// Access root resource
		ResourceSupport root = operations.getForObject(ClientConfiguration.BASE_URL, Resource.class);
		Link productLink = root.getLink(ClientConfiguration.PRODUCTS_REL);

		// Follow link to access products
		Products products = operations.getForObject(productLink.getHref(), Products.class);
		System.out.println(products.getContent().iterator().next());
	}
}
