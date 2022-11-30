package next.dao;

import core.jdbc.ConnectionManager;
import next.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class InsertJdbcTemplate {
    void insert(User user) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = ConnectionManager.getConnection();
            String sql = createQueryForInsert();
            pstmt = con.prepareStatement(sql);

            setValuesForInsert(user, pstmt);

            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            if (con != null) {
                con.close();
            }
        }
    }

    abstract String createQueryForInsert();

    abstract void setValuesForInsert(User user, PreparedStatement pstmt) throws SQLException;
//    private String createQueryForInsert() {
//        return "INSERT INTO USERS VALUES (?, ?, ?, ?)";
//    }

//    private void setValuesForInsert(User user, PreparedStatement pstmt) throws SQLException {
//        pstmt.setString(1, user.getUserId());
//        pstmt.setString(2, user.getPassword());
//        pstmt.setString(3, user.getName());
//        pstmt.setString(4, user.getEmail());
//    }
}
