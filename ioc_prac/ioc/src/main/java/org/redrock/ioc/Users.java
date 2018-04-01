package main.java.org.redrock.ioc;

import main.java.org.redrock.ioc.framework2.annotation.Autowried;
import main.java.org.redrock.ioc.framework2.annotation.Component;
import main.java.org.redrock.ioc.util.RequestUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class Users {

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Autowried
    private String username;
    private String password;



        public static void main(String[] args) {
            HttpServletRequest request = null;
            HttpServletResponse response = null;
            Users user = (Users) RequestUtil.getObject(request, Users.class);
            System.out.println("username: "+ user.username+"password"+user.password);
        }

    }
