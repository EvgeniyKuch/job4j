package ru.job4j.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.logic.ValidateService;
import ru.job4j.models.Role;
import ru.job4j.models.User;
import ru.job4j.models.UserAlreadyExists;
import ru.job4j.models.UserDoesNotExist;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Date;

@WebServlet(value = "/edit", loadOnStartup = 3)
@MultipartConfig
public class UserUpdateServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(UserUpdateServlet.class.getName());

    private final ValidateService logic = ValidateService.getInstance();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            User user = logic.findByID(id);
            req.setAttribute("editingUser", user);
            req.getRequestDispatcher("/WEB-INF/jsp/update.jsp").forward(req, resp);
        } catch (UserDoesNotExist e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        try {
            String role = ((User) req.getSession().getAttribute("user")).getRole().getRule();
            role = "root".equals(role) ? req.getParameter("role") : role;
            int id = Integer.parseInt(req.getParameter("id"));
            User editedUser = new User(
                    id,
                    req.getParameter("name"),
                    req.getParameter("login"),
                    req.getParameter("email"),
                    new Date().getTime(),
                    req.getParameter("password"),
                    new Role(role)
            );
            Part photo = req.getPart("photo");
            if (!"".equals(photo.getSubmittedFileName())) {
                editedUser.setPhoto(photo.getInputStream());
            } else {
                editedUser.setPhoto(logic.findByID(id).getPhoto());
            }
            logic.update(editedUser);
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } catch (UserAlreadyExists e) {
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } catch (UserDoesNotExist e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
