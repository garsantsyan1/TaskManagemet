package servlet;


import model.manager.TaskManager;
import model.manager.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    private TaskManager taskManager = new TaskManager();
    private UserManager userManager = new UserManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        int id = Integer.parseInt(idStr);

        taskManager.updateUserID(id);
        userManager.deleteUserById(id);
        resp.sendRedirect("/managerHome");
    }
}
