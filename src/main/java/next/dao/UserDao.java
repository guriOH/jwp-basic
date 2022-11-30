package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.jdbc.ConnectionManager;
import next.model.User;
import org.h2.command.dml.Insert;

public class UserDao {
    public void insert(User user) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getUserId());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getName());
                pstmt.setString(4, user.getEmail());
            }
        };
        jdbcTemplate.update("INSERT INTO USERS VALUES (?, ?, ?, ?)", pss);

        /*
        * 가변인자를 활용한 insert
        * */
        jdbcTemplate.update("INSERT INTO USERS VALUES (?, ?, ?, ?)",
                user.getUserId(), user.getPassword(), user.getName(), user.getEmail());

    }

    public void update(User user) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getUserId());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getName());
                pstmt.setString(4, user.getEmail());
            }
        };

        jdbcTemplate.update("UPDATE USERS SET password=?, name=?, email=?  WHERE userid=?",pss);

        jdbcTemplate.update("UPDATE USERS SET password=?, name=?, email=?  WHERE userid=?",
                 user.getPassword(), user.getName(), user.getEmail(),user.getUserId());
    }


    public User findByUserId(String userId) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, userId);
            }
        };

        RowMapper<User> rowMapper = new RowMapper<User>(){
            @Override
            public User mapRow(ResultSet rs) throws SQLException {
                return new User(
                        rs.getString("userID"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email")
                );
            }
        };

//        return jdbcTemplate.queryForObject("SELECT userId, password, name, email FROM USERS WHERE userId = ?",pss,rowMapper);


        return jdbcTemplate.queryForObject("SELECT userId, password, name, email FROM USERS WHERE userId = ?",
                pss,
                rowMapper);
    }

    public List<User> findAll() throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
            }
        };

        RowMapper<User> rowMapper = new RowMapper<User>(){
            @Override
            public User mapRow(ResultSet rs) throws SQLException {
                return new User(
                        rs.getString("userID"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email")
                );
            }
        };

        return jdbcTemplate.query("SELECT userId, password, name, email FROM USERS",pss, rowMapper);
    }
}
