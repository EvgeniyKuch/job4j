package ru.job4j.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.logic.ValidateService;
import ru.job4j.models.Role;
import ru.job4j.models.User;
import ru.job4j.models.UserAlreadyExists;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Date;

@WebServlet(value = "/create", loadOnStartup = 2)
@MultipartConfig
public class UserCreateServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(UserCreateServlet.class.getName());

    private final ValidateService logic = ValidateService.getInstance();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        req.getRequestDispatcher("/WEB-INF/jsp/create.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            User newUser = new User(
                    req.getParameter("name"),
                    req.getParameter("login"),
                    req.getParameter("email"),
                    new Date().getTime(),
                    req.getParameter("password"),
                    new Role(req.getParameter("role"))
            );
            Part photo = req.getPart("photo");
            if (!"".equals(photo.getSubmittedFileName())) {
                newUser.setPhoto(photo.getInputStream());
            }
            logic.add(newUser);
            resp.sendRedirect(String.format("%s/create", req.getContextPath()));
        } catch (UserAlreadyExists e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
