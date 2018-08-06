package cn.soft.news.servlet;

import cn.soft.news.po.TbUser;
import cn.soft.news.service.UserService;
import cn.soft.news.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Hanoch
 */
@WebServlet(name = "UserServlet", urlPatterns = "/adminLogin")
public class UserServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        TbUser user = userService.getUserByUserName(username);
        if (null == user || !user.getUserPassword().equals(password)) {
            request.setAttribute("message", "用户名或密码错误！");
            request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("admin", user);
            request.getRequestDispatcher("WEB-INF/view/adminjsps/adminindex.jsp").forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);

    }
}
