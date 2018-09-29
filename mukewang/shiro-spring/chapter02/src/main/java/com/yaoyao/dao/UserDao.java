package com.yaoyao.dao;

import com.yaoyao.web.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HOME on 2017-07-05.
 */
@Repository //通过Spring注解定义一个DAO
public class UserDao {

    private JdbcTemplate jdbcTemlate;

    private final String AUTHENTICATION_QUERY = "select username, password from users where username = ?";

    private final String USER_ROLES_QUERY = "select role_name from user_roles where username = ?";

    private final String PERMISSIONS_QUERY = "select permission from roles_permissions where role_name in(?)";

    @Autowired //自动注入JdbcTemplate的Bean
    public void setJdbcTemlate(JdbcTemplate jdbcTemlate) {
        this.jdbcTemlate = jdbcTemlate;
    }

    public User getUserByUserName(final String userName) {
        final User user = new User();
        jdbcTemlate.query(AUTHENTICATION_QUERY, new String[]{userName}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUserName(userName);
                user.setPassword(resultSet.getString("password"));
            }
        });
        return user;
    }

    public List<String> queryRolesByUserName(String userName) {
        return jdbcTemlate.query(USER_ROLES_QUERY, new String[]{userName}, new RowMapper<String>() {
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("role_name");
            }
        });
    }

    public List<String> queryPermissionByRoleNames(String roleNames) {
        if (!StringUtils.isEmpty(roleNames)) {
            return jdbcTemlate.query(PERMISSIONS_QUERY, new String[]{roleNames}, new RowMapper<String>() {
                public String mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getString("permission");
                }
            });
        }
        return null;
    }

}