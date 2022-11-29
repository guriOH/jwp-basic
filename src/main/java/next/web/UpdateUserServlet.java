//package next.web;
//
//import core.db.DataBase;
//import next.model.User;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet("/user/update")
//public class UpdateUserServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//    private static final Logger log = LoggerFactory.getLogger(UpdateUserServlet.class);
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////        User user = new User(req.getParameter("userId"), req.getParameter("password"), req.getParameter("name"),
////                req.getParameter("email"));
////        log.debug("user : {}", user);
//        User user = DataBase.findUserById(req.getParameter("userId"));
//        user.setUserId(req.getParameter("userId"));
//        user.setEmail(req.getParameter("email"));
//        user.setPassword(req.getParameter("password"));
//        user.setName(req.getParameter("name"));
//
//        resp.sendRedirect("/user/list");
//    }
//}
