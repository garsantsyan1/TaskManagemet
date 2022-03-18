package servlet;

import manager.TaskManager;
import model.Status;
import model.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;



@WebServlet(urlPatterns = "/addTask")
public class AddTaskServlet extends HttpServlet {

    private TaskManager taskManager = new TaskManager();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String description = req.getParameter("description");
        Status status = Status.valueOf(req.getParameter("statusType"));
        String date = req.getParameter("date");
        int userId = Integer.parseInt(req.getParameter("user_id"));

        try {
            taskManager.add(Task.builder()
                    .name(name)
                    .description(description)
                    .status(status)
                    .deadline(sdf.parse(date))
                    .userID(userId)
                    .build());
            resp.sendRedirect("/managerHome");
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}
