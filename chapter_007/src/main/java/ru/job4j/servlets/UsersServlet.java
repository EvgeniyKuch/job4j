package ru.job4j.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.logic.Validate;
import ru.job4j.logic.ValidateService;
import ru.job4j.models.Role;
import ru.job4j.models.User;
import ru.job4j.models.UserDoesNotExist;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/", loadOnStartup = 1)
public class UsersServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(UsersServlet.class.getName());

    private final Validate<User> logic = ValidateService.getInstance();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        try {
            User user = ((User) req.getSession().getAttribute("user"));
            Role role = user.getRole();
            if ("root".equals(role.getRule())) {
                req.setAttribute("list", logic.findAll().values());
            } else {
                req.setAttribute("list", new User[]{logic.findByID(user.getId())});
            }
            req.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(req, resp);
        } catch (UserDoesNotExist e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            logic.delete(new User(id));
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } catch (UserDoesNotExist e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
