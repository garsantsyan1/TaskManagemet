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


@WebServlet(urlPatterns = "/userHome")
public class UserHomeServlet extends HttpServlet {
    private TaskManager taskManager = new TaskManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user  = (User) req.getAttribute("user");
        List<Task> tasks =  taskManager.getTasksByUserId(user.getId());

        req.setAttribute("tasks", tasks);
        req.getRequestDispatcher("/WEB-INF/user.jsp").forward(req, resp);

    }
}
