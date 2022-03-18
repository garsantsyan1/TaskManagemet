package servlet;

import model.manager.TaskManager;
import model.Status;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet(urlPatterns = "/updateStatus")
public class UpdateStatusServlet  extends HttpServlet {

    private TaskManager taskManager = new TaskManager();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Status status = Status.valueOf(req.getParameter("status"));
        String idStr = req.getParameter("taskId");
        int id = Integer.parseInt(idStr);
        taskManager.updateTaskStatusById(id, status);

        resp.sendRedirect("/userHome");

    }
}
