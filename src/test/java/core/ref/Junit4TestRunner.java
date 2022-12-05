package core.ref;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Junit4TestRunner {
    @Test
    public void run() throws Exception {
        Class<Junit4Test> clazz = Junit4Test.class;

        for (Method method : clazz.getMethods()){

            if(method.isAnnotationPresent(MyTest.class)){
                method.invoke(clazz.newInstance());
            }

//            for (Annotation a: method.getAnnotations()) {
//                if (a.annotationType().getName().contains("MyTest")){
//                        method.invoke(clazz.newInstance());
//                }
//            }
        }


    }
}
