package servlet;

import manager.UserManager;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/removeUser")
public class RemoveUserServlet extends HttpServlet {
    UserManager userManager=new UserManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        long userId= Long.parseLong(id);
        userManager.removeUserById(userId);
        resp.sendRedirect("/home");


    }
}
