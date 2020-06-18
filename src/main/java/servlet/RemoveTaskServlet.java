package servlet;

import manager.TaskManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/removeTask")
public class RemoveTaskServlet extends HttpServlet {
    TaskManager taskManager =new TaskManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        long taskId= Long.parseLong(id);
        taskManager.delete(taskId);
        resp.sendRedirect("/home");
    }
}
