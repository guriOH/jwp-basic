package next.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="dispatcher", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    private static final String DEFAULT_REDIRECT_PREFIX = "redirect:";
    private RequestMapping rm;

    @Override
    public void init() throws ServletException {
        rm = new RequestMapping();
        rm.initMapping();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Controller controller = RequestMapping.findController(req.getPathInfo());

        try {
            String viewName = controller.execute(req, resp);
            move(viewName, req,resp);
        } catch (Throwable e){

        }

//        if(result.startsWith(DEFAULT_REDIRECT_PREFIX)){
//            try {
//                resp.sendRedirect(result.substring(result.indexOf(":")));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }else{
//            RequestDispatcher rd = req.getRequestDispatcher(result);
//            try {
//                rd.forward(req, resp);
//            } catch (ServletException e) {
//                throw new RuntimeException(e);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
    }

    private void move(String viewName, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if(viewName.startsWith(DEFAULT_REDIRECT_PREFIX)){
            resp.sendRedirect(viewName.substring(DEFAULT_REDIRECT_PREFIX.length()));
            return;
        }
        RequestDispatcher rd = req.getRequestDispatcher(viewName);
        rd.forward(req, resp);
    }

//    @Override
//    public String execute(HttpServletRequest req, HttpServletResponse resp) {
//
//        Controller controller = RequestMapping.getController(req.getPathInfo());
//
//        String result = controller.execute(req,resp);
//
//        if(result.startsWith("redirect:")){
//            try {
//                resp.sendRedirect(result.substring(result.indexOf(":")));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }else{
//            RequestDispatcher rd = req.getRequestDispatcher(result);
//            try {
//                rd.forward(req, resp);
//            } catch (ServletException e) {
//                throw new RuntimeException(e);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        return null;
//    }
}
