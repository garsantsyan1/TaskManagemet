package servlet;

import Utill.DateUtil;
import manager.TaskManager;
import model.Status;
import model.Task;
import model.Type;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.Proxy;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@WebServlet(urlPatterns = "/addTask")
public class AddTask extends HttpServlet {

    private TaskManager taskManager = new TaskManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String desc = req.getParameter("desc");
        Date deadline = null;
        try {
            deadline = DateUtil.stringToDate(req.getParameter("deadline"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int userId = Integer.parseInt(req.getParameter("userId"));
        Status status = Status.valueOf(req.getParameter("statusType"));

        Task task = new Task();
        task.setName(name);
        task.setDescription(desc);
        task.setDeadline(deadline);
        task.setUserID(userId);
        task.setStatus(status);

        taskManager.add(task);

        resp.sendRedirect("/managerHome");
    }
}
