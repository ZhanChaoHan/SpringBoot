/*
 * Copyright 2011-2018 the original author or authors.
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
package example.springdata.jpa.showcase.snippets;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Oliver Gierke
 */
@Repository
class AccountRepositoryJdbcImpl implements AccountRepositoryCustom {

	private JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	/*
	 * (non-Javadoc)
	 * @see example.springdata.jpa.showcase.snippets.AccountRepositoryCustom#removedExpiredAccounts(org.joda.time.LocalDate)
	 */
	@Override
	public void removedExpiredAccounts(LocalDate reference) {
		template.update("DELETE Account AS a WHERE a.expiryDate < ?", Date.valueOf(reference));
	}
}
