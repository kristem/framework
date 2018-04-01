package main.java.org.redrock.ioc.framework2.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**Target为java的元注解（修饰注解的注解）
 *表示注解Autowried只能用来修饰类中的FIELD
 * @Target说明了Annotation所修饰的对象范围：
 * Annotation可被用于 packages、types（类、接口、枚举、Annotation类型）、类型成员（方法、构造方法、成员变量、枚举值）、方法参数和本地变量（如循环变量、catch参数）
 *1.CONSTRUCTOR:用于描述构造器
　2.FIELD:用于描述域
　3.LOCAL_VARIABLE:用于描述局部变量
　4.METHOD:用于描述方法
　5.PACKAGE:用于描述包
　6.PARAMETER:用于描述参数
　7.TYPE:用于描述类、接口(包括注解类型) 或enum声明
 */
@Target(ElementType.FIELD)
/**
 * @Retention定义了该Annotation被保留的时间长短：
 * 某些Annotation仅出现在源代码中，而被编译器丢弃；
 * 而另一些却被编译在class文件中；
 * 编译在class文件中的Annotation可能会被虚拟机忽略，而另一些在class被装载时将被读取
 * （请注意并不影响class的执行，因为Annotation与class在使用上是被分离的）。
 * 使用这个meta-Annotation可以对 Annotation的“生命周期”限制。
 * 作用：表示需要在什么级别保存该注释信息，用于描述注解的生命周期（即：被描述的注解在什么范围内有效）
 *　取值（RetentionPoicy）有：
 * 1.SOURCE:在源文件中有效（即源文件保留）
　 2.CLASS:在class文件中有效（即class保留）
　 3.RUNTIME:在运行时有效（即运行时保留）
 * Column注解的的RetentionPolicy的属性值是RUTIME,这样注解处理器可以通过反射，获取到该注解的属性值，从而去做一些运行时的逻辑处理
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Autowried{
/**
 * @Autowired 注释，它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作。
 * 通过 @Autowired的使用来消除 set ，get方法。
 * 根据属性对象信息完成依赖注入
 */
}