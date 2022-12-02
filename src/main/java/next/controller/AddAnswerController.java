package next.controller;

import core.mvc.Controller;
import next.dao.AnswerDao;
import next.model.Answer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddAnswerController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Answer answer = new Answer(
                req.getParameter("writer"),
                req.getParameter("contents"),
                Long.valueOf(req.getParameter("questionId"))
        );

        AnswerDao answerDao = new AnswerDao();


        return null;
    }
}
