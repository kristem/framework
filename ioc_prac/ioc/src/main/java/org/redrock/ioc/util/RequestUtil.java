package main.java.org.redrock.ioc.util;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Enumeration;


public class RequestUtil {
    public static <T> T getObject(HttpServletRequest request, Class<?> clazz) {
        T object = null;
        try {
            object = (T) clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Enumeration<String> names = request.getParameterNames();
        Field[] fields = clazz.getDeclaredFields();
        Method[] methods = clazz.getDeclaredMethods();
        while (names.hasMoreElements()) {
            for (Field field : fields) {
                String fieldName = field.getName();
                String value = request.getParameter(fieldName);
                if (value=="java.lang.Integer") {
                    field.setAccessible(true);
                    try {
                        field.set(object, value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    if (value=="java.lang.Long") {
                        field.setAccessible(true);
                        try {
                            field.set(object, value);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                    if (value=="java.lang.Double") {
                        field.setAccessible(true);
                        try {
                            field.set(object, value);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                    if (value=="java.lang.Float") {
                        field.setAccessible(true);
                        try {
                            field.set(object, value);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                    if (value=="java.lang.Boolean") {
                        field.setAccessible(true);
                        try {
                            field.set(object, value);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return object;
    }
}