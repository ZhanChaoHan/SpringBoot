/*
 * Copyright 2014-2018 the original author or authors.
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
package example.springdata.solr.test.util;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.hamcrest.core.Is;
import org.junit.Assume;
import org.junit.AssumptionViolatedException;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

/**
 * {@link TestRule} implementation using {@link CloseableHttpClient} to check if Solr is running by sending
 * {@literal GET} request to {@literal /admin/info/system}.
 *
 * @author Christoph Strobl
 */
public class RequiresSolrServer implements TestRule {

	private static final String PING_PATH = "/admin/info/system";

	private final String baseUrl;
	private final @Nullable String collection;

	private RequiresSolrServer(String baseUrl) {
		this(baseUrl, null);
	}

	private RequiresSolrServer(String baseUrl, @Nullable String collection) {

		this.baseUrl = baseUrl;
		this.collection = collection;
	}

	public static RequiresSolrServer onLocalhost() {
		return new RequiresSolrServer("http://localhost:8983/solr");
	}

	public RequiresSolrServer withCollection(String collection) {
		return new RequiresSolrServer(baseUrl, collection);
	}

	@Override
	public Statement apply(Statement base, Description description) {
		return new Statement() {

			@Override
			public void evaluate() throws Throwable {

				checkServerRunning();
				base.evaluate();
			}
		};
	}

	private void checkServerRunning() {

		try (CloseableHttpClient client = HttpClientBuilder.create().build()) {

			String url = StringUtils.hasText(collection) ? baseUrl + "/" + collection + "/select?q=*:*" : baseUrl + PING_PATH;

			CloseableHttpResponse response = client.execute(new HttpGet(url));
			if (response != null && response.getStatusLine() != null) {
				Assume.assumeThat(response.getStatusLine().getStatusCode(), Is.is(200));
			}
		} catch (IOException e) {
			throw new AssumptionViolatedException("SolrServer does not seem to be running", e);
		}
	}

}
