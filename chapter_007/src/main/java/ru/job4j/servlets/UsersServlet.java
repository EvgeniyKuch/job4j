package ru.job4j.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.logic.ValidateService;
import ru.job4j.models.User;
import ru.job4j.models.UserDoesNotExist;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(value = "/list", loadOnStartup = 1)
public class UsersServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(UsersServlet.class.getName());

    private final ValidateService logic = ValidateService.getInstance();

    private final String htmlBegin = "<!DOCTYPE HTML>"
            + "<html>"
            + " <head>"
            + "  <meta charset=\"utf-8\">"
            + "  <title>Users list</title>"
            + " </head>"
            + " <body>"
            + "  <table border=\"2\" cellspacing=\"0\">"
            + "   <caption>Users list</caption>"
            + "   <tr>"
            + "    <th>id</th>"
            + "    <th>Name</th>"
            + "    <th>Login</th>"
            + "    <th>E-mail</th>"
            + "    <th>Create date</th>"
            + "    <th>Edit</th>"
            + "    <th>Delete</th>"
            + "   </tr>";

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        StringBuilder sb = new StringBuilder(htmlBegin);
        for (User user : logic.findAll().values()) {
            sb.append("<tr><td>")
                    .append(user.getId()).append("</td><td>")
                    .append(user.getName()).append("</td><td>")
                    .append(user.getLogin()).append("</td><td>")
                    .append(user.getEmail()).append("</td><td>")
                    .append(new Date(user.getCreateDate())).append("</td><td>")
                    .append(button(user.getId(), req.getContextPath(), "/edit", "get", "Edit"))
                    .append("</td><td>")
                    .append(button(user.getId(), req.getContextPath(), "/list", "post", "Delete"))
                    .append("</td></tr>");
        }
        sb.append("</table><p><a href=\"").append(req.getContextPath())
                .append("/create\">Create new user</a></p>")
                .append("</body></html>");
        PrintWriter writer = resp.getWriter();
        writer.println(sb.toString());
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            logic.delete(new User(id));
            doGet(req, resp);
        } catch (UserDoesNotExist e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private String button(int id, String contextPath, String action, String method, String name) {
        return "<form method=\"" + method + "\" action=\"" + contextPath + action + "\">"
                + "<input type=\"hidden\" name=\"id\" value=\"" + id + "\">"
                + "<input type=\"submit\" value=\"" + name + "\">"
                + "</form>";
    }
}
