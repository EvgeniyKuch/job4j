package ru.job4j.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.logic.ValidateService;
import ru.job4j.models.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet(value = "/simplelist", loadOnStartup = 0)
public class UserServlet extends HttpServlet {

    private static final Logger LOG = LogManager.getLogger(UserServlet.class.getName());

    private final ValidateService logic = ValidateService.getInstance();
    private final Map<String, ConsumerEx<HttpServletRequest>> dispatch = new HashMap<>();

    @Override
    public void init() {
        this.dispatch.put("add", this.add());
        this.dispatch.put("update", this.update());
        this.dispatch.put("delete", this.delete());
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<Integer, User> userMap = logic.findAll();
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        for (User user : userMap.values()) {
            out.println(user);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            dispatch.get(req.getParameter("action")).accept(req);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private ConsumerEx<HttpServletRequest> add() {
        return req -> {
            String name = req.getParameter("name");
            logic.add(
                    new User(
                            name, name,
                            name + "@gmail.com",
                            new Date().getTime()
                    )
            );
        };
    }

    private ConsumerEx<HttpServletRequest> update() {
        return req -> {
            int id = Integer.parseInt(req.getParameter("id"));
            String newName = req.getParameter("name");
            logic.update(
                    new User(
                            id, newName, newName,
                            newName + "@gmail.com",
                            new Date().getTime()
                    )
            );
        };
    }

    private ConsumerEx<HttpServletRequest> delete() {
        return req -> {
            int id = Integer.parseInt(req.getParameter("id"));
            logic.delete(new User(id));
        };
    }
}
