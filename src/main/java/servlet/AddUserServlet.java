package servlet;

import com.mysql.cj.Constants;
import manager.UserManager;
import model.Type;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet(urlPatterns = "/addUser")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class AddUserServlet extends HttpServlet {

    private UserManager userManager = new UserManager();
    private final String UPLOAD_DIR = "C:\\Users\\Джоник\\IdeaProjects\\TaskManagemet\\upload";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String type = req.getParameter("type");

        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPassword(password);
        user.setType(Type.valueOf(type));

        for(Part part: req.getParts()) {
            if(getFileName(part) != null) {
                String fileName = System.currentTimeMillis() + getFileName(part);
                String fullFileName = UPLOAD_DIR + File.separator  + fileName;
                part.write(fullFileName);
                user.setPictureUrl(fileName);
            }
        }

        userManager.add(user);

        resp.sendRedirect("/managerHome");
    }

    private String getFileName(Part part) {
        for(String content : part.getHeader("content-disposition").split(";")) {
            if(content.trim().startsWith("filename")) {
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
            }
        }
        return null;
    }

}
