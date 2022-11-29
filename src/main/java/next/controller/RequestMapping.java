package next.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {

    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    static Map<String, Controller> controllerMap = new HashMap<String, Controller>();

    void initMapping() {
        controllerMap.put("/", new HomeController());
        controllerMap.put("/users/loginForm", new ForwardController("/user/login.jsp"));
        controllerMap.put("/users/form", new ForwardController("/user/form.jsp"));
        controllerMap.put("/users", new ListUserController());
        controllerMap.put("/users/create", new CreateUserController());
        controllerMap.put("/users/login", new LoginController());
        controllerMap.put("/users/logout", new LogoutController());
        controllerMap.put("/users/profile", new ProfileController());
        controllerMap.put("/users/update", new UpdateUserController());
        controllerMap.put("/users/updateForm", new UpdateUserController());
    }


    public static Controller findController(String url){
        return controllerMap.get(url);
    }

}
