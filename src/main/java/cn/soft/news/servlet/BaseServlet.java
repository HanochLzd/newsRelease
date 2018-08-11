package cn.soft.news.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = (String) req.getAttribute("method");
        try {
            Method m = this.getClass().getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            String redirect = m.invoke(this, req, resp).toString();


        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        super.service(req, resp);
    }
}
