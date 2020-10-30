/*
 * Copyright 2020 the original author or authors.
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
package example.springdata.geode.server.events;

import org.apache.geode.cache.CacheWriterException;
import org.apache.geode.cache.EntryEvent;
import org.apache.geode.cache.util.CacheWriterAdapter;
import org.apache.geode.internal.cache.EntryEventImpl;
import org.springframework.stereotype.Component;

/**
 * @author Patrick Johnson
 */
@Component
public class CustomerCacheWriter extends CacheWriterAdapter<Long, Customer> {

	@Override
	public void beforeCreate(EntryEvent<Long, Customer> event) throws CacheWriterException {
		EntryEventImpl e = (EntryEventImpl) event;
		super.beforeCreate(e);
	}
}
