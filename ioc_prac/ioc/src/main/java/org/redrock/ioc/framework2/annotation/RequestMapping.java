package main.java.org.redrock.ioc.framework2.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
/**
 * 在Spring MVC 中使用 @RequestMapping 来映射请求，也就是通过它来指定控制器可以处理哪些URL请求，
 * 相当于Servlet中在web.xml中配置
 * 在@Target中有两个属性，分别为 ElementType.METHOD 和 ElementType.TYPE ，
 * 也就是说 @RequestMapping 可以在方法和类的声明中使用
 */
public @interface RequestMapping {
    String value();
    RequestMethod method() default RequestMethod.GET;
}
