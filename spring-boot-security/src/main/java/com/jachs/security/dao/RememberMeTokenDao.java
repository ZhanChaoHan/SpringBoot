package com.jachs.security.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.stereotype.Repository;

import com.jachs.security.entity.RememberMeToken;

/**
 * @author zhanchaohan
 * @see JdbcTokenRepositoryImpl
 * @see InMemoryTokenRepositoryImpl
 */
@Repository
public class RememberMeTokenDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //插入
    public void saveRememberMeToken(RememberMeToken token){
        String sql = "insert into t_remember_token(login_name, series, token, last_used ) values(?,?,?,?)";
        int i = jdbcTemplate.update(sql,token.getLoginName(),token.getSeries(),  token.getToken(), token.getLastUsed());
        if(i > 0){
            System.err.println("token 插入成功===============");
        }
    }
    public void deleteRememberMeToken(String loginName){
        String sql ="delete from t_remember_token where login_name = ?";
        int i = jdbcTemplate.update(sql, loginName);
        if(i > 0){
            System.err.println("token 删除成功===============");
        }
    }
    public void updateRememberMeToken(String series, String token, Date lastUsed){
        String sql = "update t_remember_token set token=?,last_used=? where series = ?";
        int i = jdbcTemplate.update(sql,token, lastUsed,series);
        if (i > 0) {
            System.err.println("token 更新成功===============");
        } else {
            System.err.println("token 更新失败===============");
        }
    }
    public RememberMeToken getRememberMeToken( String seriesId){
        String sql ="select login_name,series,token,last_used from t_remember_token where series=?";
        RememberMeToken rememberMeToken = null;
        try {
             rememberMeToken = jdbcTemplate.queryForObject(sql,new Object[]{seriesId},(rs, rowNum) -> {
                RememberMeToken token = new RememberMeToken();
                token.setSeries(rs.getString("SERIES"));
                token.setToken(rs.getString("TOKEN"));
                token.setLastUsed(rs.getTimestamp("LAST_USED"));
                token.setLoginName(rs.getString("LOGIN_NAME"));
                return token;
            });
        } catch (Exception e) {
        return null;
    }
        return rememberMeToken;
    }
}
