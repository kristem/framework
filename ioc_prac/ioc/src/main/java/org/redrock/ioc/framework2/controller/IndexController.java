package main.java.org.redrock.ioc.framework2.controller;

import main.java.org.redrock.ioc.framework2.annotation.Autowried;
import main.java.org.redrock.ioc.framework2.annotation.Controller;
import main.java.org.redrock.ioc.framework2.annotation.RequestMapping;
import main.java.org.redrock.ioc.framework2.annotation.RequestMethod;
import main.java.org.redrock.ioc.framework2.component.Users;
import main.java.org.redrock.ioc.framework2.component.World;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  在SpringMVC 中，控制器Controller 负责处理由DispatcherServlet 分发的请求，
 *  它把用户请求的数据经过业务处理层处理之后封装成一个Model ，然后再把该Model 返回给对应的View 进行展示。
 *  在SpringMVC 中提供了一个非常简便的定义Controller 的方法，你无需继承特定的类或实现特定的接口，
 *  只需使用@Controller 标记一个类是Controller ，然后使用@RequestMapping 和@RequestParam 等一些注解
 *  用以定义URL 请求和Controller 方法之间的映射，这样的Controller 就能被外界访问到。
 */

@Controller
public class IndexController {
    @Autowried
    Users users;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public void test(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        request.setCharacterEncoding("UTF-8");
    }
}
