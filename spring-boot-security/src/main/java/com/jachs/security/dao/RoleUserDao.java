package com.jachs.security.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.jachs.security.entity.RoleUser;
import com.jachs.security.entity.SecurityUser;

import lombok.extern.slf4j.Slf4j;

/****
 * 登录用Dao
 * @author zhanchaohan
 *
 */
@Repository
@Slf4j
public class RoleUserDao {
    public static final String QUERY_ROLE_SQL = "select Name,Phone,Enabled,Username,Password,AccountNonExpired,AccountNonLocked,CredentialsNonExpired  from roleuser where Name = ?";
    private String queryRoleSql = QUERY_ROLE_SQL;

    public static final String QUERY_AUTHORITIES = "select Username,Code,Authority from securityuser where  Username=?";
    private String queryAuthorities = QUERY_AUTHORITIES;

    public static final String ADD_ROLE_SQL="insert into roleuser (Name, Phone, Enabled, Username, Password, AccountNonExpired, AccountNonLocked, CredentialsNonExpired) values (?,?,?,?,?,?,?,?)";
    private String addRoleSql=ADD_ROLE_SQL;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public RoleUser queryRoleUser ( String Name ) {
        RoleUser user = null;
        try {
                user = jdbcTemplate.queryForObject ( queryRoleSql, new RowMapper<RoleUser> () {
                @Override
                public RoleUser mapRow ( ResultSet rs , int paramInt ) throws SQLException {
                        RoleUser ru = new RoleUser ();
                        ru.setName ( rs.getString ( "Name" ) );
                        ru.setPhone ( rs.getLong ( "Phone" ) );
                        ru.setEnabled ( rs.getInt ( "Enabled" ) == 0 ? true : false );
                        ru.setUsername ( rs.getString ( "Username" ) );
                        ru.setPassword ( rs.getString ( "Password" ) );
                        ru.setAccountNonExpired ( rs.getInt ( "AccountNonExpired" ) == 0 ? true : false );
                        ru.setAccountNonLocked ( rs.getInt ( "AccountNonLocked" ) == 0 ? true : false );
                        ru.setCredentialsNonExpired ( rs.getInt ( "CredentialsNonExpired" ) == 0 ? true : false );
                        return ru;
                }
            } ,Name);
        }
        catch ( EmptyResultDataAccessException e ) {
           return null;
        }
        return user;
    }

    public Set<SecurityUser> queryAuthorities ( String username ) {
        return jdbcTemplate.query ( queryAuthorities, new ResultSetExtractor<Set<SecurityUser>> () {
            @Override
            public Set<SecurityUser> extractData ( ResultSet rs ) throws SQLException {
                Set<SecurityUser> result = new HashSet<SecurityUser> ();
                while ( rs.next () ) {
                    SecurityUser user = new SecurityUser ();
                    user.setAuthority ( rs.getString ( "Authority" ) );
                    user.setCode ( rs.getInt ( "Code" ) );
                    result.add ( user );
                }
                return result;
            }
        }, username );
    }

    public boolean addUser ( RoleUser roleUser ) {
        return jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(addRoleSql);
                preparedStatement.setString(1,roleUser.getName ());
                preparedStatement.setLong(2,roleUser.getPhone ());
                preparedStatement.setString(3,roleUser.isEnabled ()==true?"1":"0");
                preparedStatement.setString(4,roleUser.getUsername ());
                preparedStatement.setString(5,roleUser.getPassword ());
                preparedStatement.setString(6,roleUser.isAccountNonExpired ()==true?"1":"0");
                preparedStatement.setString(7,roleUser.isAccountNonLocked ()==true?"1":"0");
                preparedStatement.setString(8,roleUser.isCredentialsNonExpired ()==true?"1":"0");
                return preparedStatement;
            }
        }) > 0;
    }
}
