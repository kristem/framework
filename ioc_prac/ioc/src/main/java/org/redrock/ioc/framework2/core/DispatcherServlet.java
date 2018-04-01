package main.java.org.redrock.ioc.framework2.core;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 前置控制器
 * 拦截匹配的请求，Servlet拦截匹配规则要自己定义，把拦截下来的请求，
 * 依据相应的规则分发到目标Controller来处理
 */
@WebServlet("/*")
public class DispatcherServlet extends GenericServlet{

    private Map<Class<?>, Object> controllers;
    private Map<String, Method> handlers;

    /**
     * 初始化servlet
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        ClassLoader classLoader = new ClassLoader();
        BeanFactory beanFactory = new BeanFactory(classLoader);
        controllers = beanFactory.getControllers();
        handlers = beanFactory.getHandlers();
    }

    /**
     * 根据请求处理对象handle（key,method实例）
     * 根据handle获取该方法所属控制器实体对象
     * 反射执行，请求处理方法
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String handlerKey = req.getMethod() + ":" + req.getRequestURI();

        Method handler = handlers.get(handlerKey);
        Object controller = controllers.get(handler.getDeclaringClass());

        if (controller != null) {
            try {

                handler.invoke(controller, req, resp);

            } catch (IllegalAccessException | InvocationTargetException e) {
                Throwable cause = e.getCause();
                if (cause instanceof IOException) {
                    IOException exception = (IOException) cause;
                    exception.printStackTrace();
                }
                e.printStackTrace();
            }
        }
    }
}
