package servlet;

import manager.TaskManager;
import manager.UserManager;
import model.Task;
import model.TaskStatus;
import model.User;
import util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(urlPatterns = "/add")
public class AddTaskServlet extends HttpServlet {
    UserManager userManager = new UserManager();
    TaskManager taskManager = new TaskManager();
    //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", userManager.getAllUsers());
        req.getRequestDispatcher("/WEB-INF/addTask.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String deadline = req.getParameter("deadline");
        String status = req.getParameter("status");
        String userId = req.getParameter("userId");
        StringBuilder msg = new StringBuilder();
        if (name == null || name.length() == 0) {
            msg.append("name field is required <br>");
        }
        if (description == null || description.length() == 0) {
            msg.append("description field is required <br>");
        }
        if (status == null) {
            msg.append("status field is required <br>");
        }
        if (userId == null) {
            msg.append("userId field is required <br>");
        }

        if (msg.toString().equals("")) {

                Task task = Task.builder()
                        .name(name)
                        .description(description)
                        .deadline(DateUtil.convertStringToDate(deadline))
                        .status(TaskStatus.valueOf(status))
                        .user(userManager.getById(Long.parseLong(userId)))
                        .build();
                taskManager.create(task);
                msg.append("<span style=\"color:green\">Task successfully added</span");

        }
        req.getSession().setAttribute("msg", msg.toString());
        resp.sendRedirect("/add");
    }
}
