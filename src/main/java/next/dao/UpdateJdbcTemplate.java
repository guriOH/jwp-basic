package next.dao;

import core.jdbc.ConnectionManager;
import next.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UpdateJdbcTemplate {

    void update(User user) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            con = ConnectionManager.getConnection();
            String sql = createQueryForUpdate();
            pstmt = con.prepareStatement(sql);
            setValuesForUpdate(user, pstmt);

            pstmt.executeUpdate();
        }finally {

        }
    }

   public abstract void setValuesForUpdate(User user, PreparedStatement pstmt) throws SQLException;
//    private void setValuesForUpdate(User user, PreparedStatement pstmt) throws SQLException {
//        pstmt.setString(1, user.getPassword());
//        pstmt.setString(2, user.getName());
//        pstmt.setString(3, user.getEmail());
//        pstmt.setString(4, user.getUserId());
//    }

    public abstract String createQueryForUpdate();
//    private String createQueryForUpdate() {
//        return "UPDATE USERS SET password=?, name=?, email=?  WHERE userid=?";
//    }
}
