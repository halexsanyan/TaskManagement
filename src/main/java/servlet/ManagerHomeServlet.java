package servlet;

import manager.TaskManager;
import manager.UserManager;
import model.Task;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = ("/home"))
public class ManagerHomeServlet extends HttpServlet {

    UserManager userManager=new UserManager();
    TaskManager taskManager=new TaskManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        Task task = (Task) req.getSession().getAttribute("task");
        if(user!=null){
            List<User> allUsers=userManager.getAllUsers();
            List<Task> allTasks =taskManager.getAll();
            req.setAttribute("users", allUsers);
            req.setAttribute("tasks", allTasks);
            req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req,resp);
        } else{
            resp.sendRedirect("/");
        }




    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
