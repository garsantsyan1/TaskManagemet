package servlet;

import manager.UserManager;
import model.Type;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private UserManager userManager = new UserManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = userManager.getByEmailAndPassword(login, password);

        if(userManager.getByEmailAndPassword(login, password) != null) {
            if(user.getType() == Type.MANAGER) {
                req.getRequestDispatcher("/managerHome").forward(req, resp);
            } else if(user.getType() == Type.USER) {
                req.setAttribute("user", user);
                req.getRequestDispatcher("/userHome").forward(req, resp);
            }

        }

    }
}
