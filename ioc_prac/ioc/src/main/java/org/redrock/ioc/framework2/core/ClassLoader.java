package main.java.org.redrock.ioc.framework2.core;

import main.java.org.redrock.ioc.framework2.annotation.Component;
import main.java.org.redrock.ioc.framework2.annotation.Controller;

import java.util.Set;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;

/**
 * 类加载器
 * ClassLoader主要对类的请求提供服务，当JVM需要某类时，它根据名称向ClassLoader要求这个类，
 * 然后由ClassLoader返回 这个类的class对象。ClassLoader负责载入系统的所有Resources（Class，文件，来自网络的字节流 等），
 * 通过ClassLoader从而将资源载入JVM,每个class都有一个reference，指向自己的ClassLoader。
 * Class.getClassLoader()(基本数据类型没有)
 */


/**
 * 包扫描
 */

public class ClassLoader {
    private static final String packageName = "org.redrock.ioc.framework2";

    private Set<Class<?>> classSet;
    private Set<Class<?>> controllerSet;
    private Set<Class<?>> componentSet;

    public ClassLoader(){
        load();
    }

    /**
     * 加载指定包下所有类的类型信息
     */
    private void load(){
        classSet = new HashSet<>();
        try {
            Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(packageName.replace("\\.","/"));
            while (resources.hasMoreElements()){
                URL resource = resources.nextElement();
                String protocol = resource.getProtocol();//获取协议名称
                if(protocol.equalsIgnoreCase("file")){
                    String packagePath = resource.getPath();
                    loadClass(classSet, packageName, packagePath);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadComponentSet();
        loadControllerSet();
    }

    /**
     * 分出controller和component类
     */

    public Set<Class<?>> getControllerSet(){
        return controllerSet;
    }

    public Set<Class<?>> getComponentSet() {
        return componentSet;
    }

    private void loadClass(Set<Class<?>> classSet, String packageName, String packagePath) {
        File[] files = new File(packagePath).listFiles(pathname -> pathname.isDirectory() || (pathname.isFile() && pathname.getName().endsWith(".class")));
        if (files != null && files.length > 0) {
            for (File file : files) {

                String fileName = file.getName();
                if (file.isFile()) {
                    if (packageName != null && !packageName.equals("")) {
                        fileName = packageName + "." + fileName.substring(0, fileName.lastIndexOf("."));
                    }
                    Class<?> clazz = getClass(fileName);
                    classSet.add(clazz);
                } else {
                    String subPackageName = fileName;
                    if (packageName != null && !packageName.equals("")) {
                        subPackageName = packageName + "." + subPackageName;
                    }
                    String subPackagePath = fileName;
                    if (packagePath != null && !packagePath.equals("")) {
                        subPackagePath = packagePath + "/" + subPackagePath;
                    }
                    loadClass(classSet, subPackageName, subPackagePath);
                }
            }
        }
    }

    /**
     * 实例化所有组件
     */

    private void loadComponentSet() {
        componentSet = new HashSet<>();
        if(classSet != null) {
            for(Class<?> clazz : classSet) {
                if (clazz.getAnnotation(Component.class) != null) {
                    componentSet.add(clazz);
                }
            }
        }
    }

    /**
     * 实例Controller类
     */
    private void loadControllerSet() {
        controllerSet = new HashSet<>();
        if (classSet != null) {
            for (Class<?> clazz : classSet) {
                if (clazz.getAnnotation(Controller.class) != null) {
                    controllerSet.add(clazz);
                }
            }
        }
    }

    /**
     * 创建分出来的类class对象
     * @param className
     * @return
     */
    private Class<?> getClass(String className) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }
}
