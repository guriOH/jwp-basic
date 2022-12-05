package core.ref;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.model.Question;
import next.model.User;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionTest {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    public void showClass() {
        System.out.println("########################### Field ###########################");
        Class<Question> clazz = Question.class;
        for (Field field : clazz.getFields()) {
            System.out.println(field.getName());
        }

        System.out.println("########################### Private/Protected Field ###########################");
        for (Field field : clazz.getDeclaredFields()) {
            System.out.println(field.getName());
        }


        System.out.println("########################### Method ###########################");
        for (Method method:clazz.getMethods()) {
            System.out.println(method.getName());
        }

        System.out.println("########################### Private/Protected Method ###########################");
        for (Method method:clazz.getDeclaredMethods()) {
            System.out.println(method.getName());
        }
    }
    
    @Test
    public void newInstanceWithConstructorArgs() {
        Class<User> clazz = User.class;
        logger.debug(clazz.getName());
    }
    
    @Test
    public void privateFieldAccess() {
        Class<Student> clazz = Student.class;
        logger.debug(clazz.getName());
    }
}
