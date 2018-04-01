package main.java.org.redrock.ioc.framework1.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class IndexController {

    public void index(HttpServletRequest request, HttpServletResponse response){
        try {
            response.getWriter().println("IndexController");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
