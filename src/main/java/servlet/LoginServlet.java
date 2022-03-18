package servlet;

import model.manager.UserManager;
import model.Type;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private UserManager userManager = new UserManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = userManager.getByEmailAndPassword(login, password);

        if(userManager.getByEmailAndPassword(login, password) != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            if(user.getType() == Type.MANAGER) {
                resp.sendRedirect("/managerHome");
            } else if(user.getType() == Type.USER) {
                resp.sendRedirect("/userHome");
            }
        } else {
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }

    }
}
