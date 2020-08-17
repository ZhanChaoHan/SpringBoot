package com.jachs.security.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.jachs.security.entity.RoleUser;
import com.jachs.security.entity.SecurityUser;

import lombok.extern.slf4j.Slf4j;

/****
 * 
 * @author zhanchaohan
 *
 */
@Repository
@Slf4j
public class RoleUserDao {
	public static final String QUERY_ROLE_SQL = "select Name,Phone,Enabled,Username,Password,AccountNonExpired,AccountNonLocked,CredentialsNonExpired  from roleuser where Name = ?";
	private String queryRoleSql = QUERY_ROLE_SQL;
	
	public static final String QUERY_AUTHORITIES = "select username,series,token,last_used from my_mytoken where series = ?";
	private String queryAuthorities = QUERY_AUTHORITIES;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public RoleUser queryRoleUser(String Name) {
		return jdbcTemplate.queryForObject(queryRoleSql,new Object[] {Name},  new RowMapper<RoleUser>() {
            @Override
            public RoleUser mapRow(ResultSet rs, int paramInt)
                    throws SQLException {
            	RoleUser ru = new RoleUser();
            	ru.setName(rs.getString("Name"));
            	ru.setPhone(rs.getLong("Phone"));
            	ru.setEnabled(rs.getInt("Enabled")==1?true:false);
            	ru.setUsername(rs.getString("Username"));
            	ru.setPassword(rs.getString("Password"));
            	ru.setAccountNonExpired(rs.getInt("AccountNonExpired")==1?true:false);
            	ru.setAccountNonLocked(rs.getInt("AccountNonLocked")==1?true:false);
            	ru.setCredentialsNonExpired(rs.getInt("CredentialsNonExpired")==1?true:false);
                return ru;
            }});
	    }

	public Set<SecurityUser> queryAuthorities(String username) {
		return null;
	}
}
