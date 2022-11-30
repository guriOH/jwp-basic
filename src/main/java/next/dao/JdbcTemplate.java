package next.dao;

import core.jdbc.ConnectionManager;
import next.exception.DataAccessException;
import next.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {

    public void update(String sql, PreparedStatementSetter pss) throws DataAccessException {
        /*
        *  자바 7 에서 제공하는 java.io.AutoClosable 인터페이스 활용
        * AutoClosable를 구현해야함
        *  try-with-resources 구문으로 자원을 자동 반납
        * */
        try (Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pss.setValues(pstmt);
            pstmt.executeUpdate(sql);
        } catch (SQLException e){
            throw new DataAccessException(e);
        }
    }

    public Object queryForObject(String sql,PreparedStatementSetter pss, RowMapper rowMapper) throws SQLException{
        List result = query(sql,pss,rowMapper);
        if(result.isEmpty()){
            return null;
        }
        return result.get(0);
    }

    public List query(String sql,PreparedStatementSetter pss, RowMapper rowMapper) throws SQLException {
        ResultSet rs = null;
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pss.setValues(pstmt);
            rs = pstmt.executeQuery();

            List<Object> result = new ArrayList<Object>();
            while(rs.next()){
                result.add(rowMapper.mapRow(rs));
            }
            return result;
        }catch (SQLException e){
            throw new DataAccessException(e);
        }
    }

}
