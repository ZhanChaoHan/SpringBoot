package com.jachs.security.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.stereotype.Repository;

import com.jachs.security.entity.RememberMeToken;

import lombok.extern.slf4j.Slf4j;
/**
 * @author zhanchaohan
 * @see JdbcTokenRepositoryImpl
 * @see InMemoryTokenRepositoryImpl
 */
@Repository
@Slf4j
public class RememberMeTokenDao {
	// 重写JdbcTokenRepositoryImpl定义自定义表
	// =====================================================================================
	/** Default SQL for creating the database table to store the tokens */
	public static final String CREATE_TABLE_SQL = "create table my_mytoken (username varchar(64) not null, series varchar(64) primary key, "
			+ "token varchar(64) not null, last_used timestamp not null)";
	/** The default SQL used by the <tt>getTokenBySeries</tt> query */
	public static final String DEF_TOKEN_BY_SERIES_SQL = "select username,series,token,last_used from my_mytoken where series = ?";
	/** The default SQL used by <tt>createNewToken</tt> */
	public static final String DEF_INSERT_TOKEN_SQL = "insert into my_mytoken (username, series, token, last_used) values(?,?,?,?)";
	/** The default SQL used by <tt>updateToken</tt> */
	public static final String DEF_UPDATE_TOKEN_SQL = "update my_mytoken set token = ?, last_used = ? where series = ?";
	/** The default SQL used by <tt>removeUserTokens</tt> */
	public static final String DEF_REMOVE_USER_TOKENS_SQL = "delete from my_mytoken where username = ?";

	// ~ Instance fields
	// ================================================================================================

	private String tokensBySeriesSql = DEF_TOKEN_BY_SERIES_SQL;
	private String insertTokenSql = DEF_INSERT_TOKEN_SQL;
	private String updateTokenSql = DEF_UPDATE_TOKEN_SQL;
	private String removeUserTokensSql = DEF_REMOVE_USER_TOKENS_SQL;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void createNewToken(RememberMeToken token) {
		jdbcTemplate.update(insertTokenSql, token.getLoginName(), token.getSeries(),
				token.getToken(), token.getLastUsed());
	}

	public void updateToken(String series, String tokenValue, Date lastUsed) {
		jdbcTemplate.update(updateTokenSql, tokenValue, lastUsed, series);
	}

	/**
	 * Loads the token data for the supplied series identifier.
	 *
	 * If an error occurs, it will be reported and null will be returned (since the result
	 * should just be a failed persistent login).
	 *
	 * @param seriesId
	 * @return the token matching the series, or null if no match found or an exception
	 * occurred.
	 */
	public RememberMeToken getTokenForSeries(String seriesId) {
		try {
			return jdbcTemplate.queryForObject(tokensBySeriesSql,
					(rs, rowNum) -> new RememberMeToken(rs.getString(1), rs
							.getString(2), rs.getString(3), rs.getTimestamp(4)), seriesId);
		}
		catch (EmptyResultDataAccessException zeroResults) {
			if (log.isDebugEnabled()) {
				log.debug("Querying token for series '" + seriesId
						+ "' returned no results.", zeroResults);
			}
		}
		catch (IncorrectResultSizeDataAccessException moreThanOne) {
			log.error("Querying token for series '" + seriesId
					+ "' returned more than one value. Series" + " should be unique");
		}
		catch (DataAccessException e) {
			log.error("Failed to load token for series " + seriesId, e);
		}

		return null;
	}

	public void removeUserTokens(String username) {
		jdbcTemplate.update(removeUserTokensSql, username);
	}

	/**
	 * Intended for convenience in debugging. Will create the persistent_tokens database
	 * table when the class is initialized during the initDao method.
	 *
	 * @param createTableOnStartup set to true to execute the
	 */
	public void CreateTableOnStartup(boolean createTableOnStartup) {
		if(createTableOnStartup) {
			jdbcTemplate.execute(CREATE_TABLE_SQL);
		}
	}

}
