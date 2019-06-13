package ru.job4j.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.logic.ValidateService;
import ru.job4j.models.User;
import ru.job4j.models.UserAlreadyExists;
import ru.job4j.models.UserDoesNotExist;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(value = "/edit", loadOnStartup = 3)
public class UserUpdateServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(UserUpdateServlet.class.getName());

    private final ValidateService logic = ValidateService.getInstance();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            User user = logic.findByID(id);
            String name = user.getName();
            String login = user.getLogin();
            String email = user.getEmail();
            String edit =
                    "<!DOCTYPE HTML>"
                            + "<html>"
                            + " <head>"
                            + "  <meta charset=\"utf-8\">"
                            + "  <title>Editing user</title>"
                            + " </head>"
                            + " <body>"
                            + " <form method=\"post\" action=\"" + req.getContextPath() + "/edit\">"
                            + " <input type=\"hidden\" name=\"id\" value=\"" + id + "\">"
                            + "  <p><b>Name:</b><input type=\"text\" name=\"name\" value=\"" + name + "\"></p>"
                            + "  <p><b>Login:</b><input type=\"text\" name=\"login\" value=\"" + login + "\"></p>"
                            + "  <p><b>E-mail:</b><input type=\"text\" name=\"email\" value=\"" + email + "\"></p>"
                            + "  <p><input type=\"submit\" value=\"Update\"></p>"
                            + " </form>"
                            + " <p><a href=\"" + req.getContextPath() + "/list\">User list</a></p>"
                            + " </body>"
                            + "</html>";
            resp.setContentType("text/html");
            PrintWriter writer = resp.getWriter();
            writer.println(edit);
        } catch (UserDoesNotExist e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            User editedUser = new User(
                    Integer.parseInt(req.getParameter("id")),
                    req.getParameter("name"),
                    req.getParameter("login"),
                    req.getParameter("email"),
                    new Date().getTime()
            );
            logic.update(editedUser);
            doGet(req, resp);
        } catch (UserDoesNotExist | UserAlreadyExists e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
