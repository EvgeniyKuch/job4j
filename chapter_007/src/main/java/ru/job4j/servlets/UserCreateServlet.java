package ru.job4j.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.logic.ValidateService;
import ru.job4j.models.User;
import ru.job4j.models.UserAlreadyExists;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(value = "/create", loadOnStartup = 2)
public class UserCreateServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(UserCreateServlet.class.getName());

    private final ValidateService logic = ValidateService.getInstance();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String create =
                "<!DOCTYPE HTML>"
                        + "<html>"
                        + " <head>"
                        + "  <meta charset=\"utf-8\">"
                        + "  <title>Create user</title>"
                        + " </head>"
                        + " <body>"
                        + " <form method=\"post\" action=\"" + req.getContextPath() + "/create\">"
                        + "  <p><b>Name:</b><input type=\"text\" name=\"name\"></p>"
                        + "  <p><b>Login:</b><input type=\"text\" name=\"login\"></p>"
                        + "  <p><b>E-mail:</b><input type=\"text\" name=\"email\"></p>"
                        + "  <p><input type=\"submit\" value=\"Create\"></p>"
                        + " </form>"
                        + " <p><a href=\"" + req.getContextPath() + "/list\">User list</a></p>"
                        + " </body>"
                        + "</html>";
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println(create);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            User newUser = new User(
                    req.getParameter("name"),
                    req.getParameter("login"),
                    req.getParameter("email"),
                    new Date().getTime()
            );
            logic.add(newUser);
            doGet(req, resp);
        } catch (UserAlreadyExists e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
