package main.java.org.redrock.ioc.framework2.component;

import main.java.org.redrock.ioc.framework2.annotation.Component;

@Component
public class Users {

    private String name;
    private int age;
    private String sex;
    private String email;
    private int phoneNum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(int phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return "user name:"+name+" age:"+age+" sex:"+sex+" email:"+email
                +" phoneNum:"+phoneNum;
    }

    private String testForUser(){
         return "testForUser";
    }

}
