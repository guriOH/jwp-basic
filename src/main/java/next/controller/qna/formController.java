package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.Controller;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;
import next.dao.QuestionDao;
import next.model.Question;
import next.model.User;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class formController extends AbstractController {

    QuestionDao questionDao = new QuestionDao();


    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (!UserSessionUtils.isLogined(request.getSession())) {
            return jspView("redirect:/users/loginForm");
        }

        User user = (User)request.getSession().getAttribute("user");
        Question question = new Question(
                StringUtils.isEmpty(request.getParameter("writer"))
                        ? user.getName() :request.getParameter("writer")
                ,
                request.getParameter("title"),
                request.getParameter("contents"));



        QuestionDao questionDao = new QuestionDao();

        Question newQuestion = questionDao.insert(question);

        return jspView("redirect:/");
    }
}
