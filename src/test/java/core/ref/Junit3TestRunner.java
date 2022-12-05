package core.ref;

import org.junit.Test;

import java.lang.reflect.Method;

public class Junit3TestRunner {
    @Test
    public void run() throws Exception {
        Class<Junit3Test> clazz = Junit3Test.class;

        Junit3Test junitTest =clazz.getConstructor().newInstance();


        junitTest.test1();
        junitTest.test2();


        for (Method method : clazz.getMethods()){
            if (method.getName().startsWith("test"))
                method.invoke(clazz.newInstance());
        }
    }
}
