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
        JdbcTemplate jdbcTemplate = new JdbcTemplate(){

//            @Override
//            String createQuery() {
//                return "INSERT INTO USERS VALUES (?, ?, ?, ?)";
//            }

            @Override
            void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getUserId());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getName());
                pstmt.setString(4, user.getEmail());
            }

            @Override
            Object mapRow(ResultSet rs) throws SQLException {
                return null;
            }
        };
        jdbcTemplate.update("INSERT INTO USERS VALUES (?, ?, ?, ?)");
    }

    public void update(User user) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(){
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getPassword());
                pstmt.setString(2, user.getName());
                pstmt.setString(3, user.getEmail());
                pstmt.setString(4, user.getUserId());
            }

//            @Override
//            public String createQuery() {
//                return "UPDATE USERS SET password=?, name=?, email=?  WHERE userid=?";
//            }


            @Override
            Object mapRow(ResultSet rs) throws SQLException {
                return null;
            }
        };
        jdbcTemplate.update("UPDATE USERS SET password=?, name=?, email=?  WHERE userid=?");
    }


    public User findByUserId(String userId) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionManager.getConnection();
            String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userId);

            rs = pstmt.executeQuery();

            User user = null;
            if (rs.next()) {
                user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                        rs.getString("email"));
            }

            return user;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public List<User> findAll() throws SQLException {
        SelectJdbcTemplate jdbcTemplate = new SelectJdbcTemplate(){
            @Override
            void setValues(PreparedStatement pstmt) throws SQLException {

            }

            @Override
            Object mapRow(ResultSet rs) throws SQLException {
                return new User(
                        rs.getString("userID"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email")
                );
            }
        };

        return (List<User>)jdbcTemplate.query("SELECT userId, password, name, email FROM USERS");
    }
}
