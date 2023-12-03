package com.techelevator.tenmo.dao;
import com.techelevator.tenmo.exception.DaoException;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Tenmo_User;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTenmo_UserDao implements Tenmo_UserDao {

    private static List<Tenmo_User> tenmo_userList = new ArrayList<>();

    private final JdbcTemplate jdbcTemplate;

    public JdbcTenmo_UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Tenmo_User> getUserListByUserId () {
        List<Tenmo_User> tenmo_userList = new ArrayList<>();
        for (Tenmo_User tenmo_user : tenmo_userList) {
            String sql = "SELECT user_id, username FROM tenmo_user";

            try {
                SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
                ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next())
                        String username = resultSet.getString("username");
                    int user_id = resultSet.getInt("user_id");
                    dataList.add(username);
                }

            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return tenmo_userList;

    }

    private Tenmo_User mapRowToTenmo_User(SqlRowSet rs) {
        Tenmo_user = new ArrayList<>();
        tenmo_user.setUser_id(rs.getInt("user_id"));
        tenmo_user.setUsername(rs.getString("username"));
        tenmo_user.add(new )


        return tenmo_user;
    }

}


