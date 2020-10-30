package example.springdata.jdbc.jooq;

import static example.springdata.jdbc.basics.simpleentity.domain.tables.Category.*;

import java.util.List;

import org.jooq.DSLContext;

/**
 * Implementations for custom repository access using jOOQ.
 *
 * @author Florian Lüdiger
 */
public class JooqRepositoryImpl implements JooqRepository {

	private final DSLContext dslContext;

	public JooqRepositoryImpl(DSLContext dslContext) {
		this.dslContext = dslContext;
	}

	public List<Category> getCategoriesWithAgeGroup(AgeGroup ageGroup) {
		return this.dslContext.select().from(CATEGORY).where(CATEGORY.AGE_GROUP.equal(ageGroup.name()))
				.fetchInto(Category.class);
	}
}
