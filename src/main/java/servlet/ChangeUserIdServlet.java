package servlet;

import manager.TaskManager;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/changeUserId")
public class ChangeUserIdServlet extends HttpServlet {

    private TaskManager taskManager = new TaskManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userIdStr = req.getParameter("user_id");
        String taskIdStr = req.getParameter("taskId");
        int userId = Integer.parseInt(userIdStr);
        int taskId = Integer.parseInt(taskIdStr);
        taskManager.changeUserId(userId, taskId);

        resp.sendRedirect("/managerHome");
    }
}
