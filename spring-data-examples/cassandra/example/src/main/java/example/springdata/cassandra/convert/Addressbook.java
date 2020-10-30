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
package example.springdata.cassandra.convert;

import lombok.Data;

import java.util.Currency;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 * Sample Addressbook class.
 *
 * @author Mark Paluch
 */
@Data
@Table
public class Addressbook {

	@Id String id;

	Contact me;
	List<Contact> friends;

	Address address;
	Map<Integer, Currency> preferredCurrencies;
}
