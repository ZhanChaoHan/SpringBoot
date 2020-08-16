package com.jachs.security.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jachs.security.dao.RememberMeTokenDao;
import com.jachs.security.entity.RememberMeToken;

/**
 * @author zhanchaohan
 * 
 */
@Service
public class RememberMeTokenService implements PersistentTokenRepository {
	@Autowired
	private RememberMeTokenDao rememberMeTokenDao;
	
	public void setCreateTableOnStartup(boolean createTableOnStartup) {
		rememberMeTokenDao.CreateTableOnStartup(createTableOnStartup);
	}
	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		rememberMeTokenDao.createNewToken(
				new RememberMeToken(token.getSeries(), token.getUsername(), token.getTokenValue(), token.getDate()));
	}

	@Transactional
	@Override
	public void updateToken(String series, String tokenValue, Date lastUsed) {
		rememberMeTokenDao.updateToken(series, tokenValue, lastUsed);
	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		RememberMeToken rememberMeToken = rememberMeTokenDao.getTokenForSeries(seriesId);
		return Optional.ofNullable(rememberMeToken).map(a -> {
			return new PersistentRememberMeToken(a.getLoginName(), a.getSeries(), a.getToken(), a.getLastUsed());
		}).orElse(null);
	}

	@Transactional
	@Override
	public void removeUserTokens(String username) {
		rememberMeTokenDao.removeUserTokens(username);
	}
}
