package next.dao;

import core.jdbc.JdbcTemplate;
import next.model.Answer;
import next.model.User;

public class AnswerDao {

    public void insert(Answer answer) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "INSERT INTO ANSWERS VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, answer.getWriter(), answer.getContents(), answer.getQuestionId());

    }
}
