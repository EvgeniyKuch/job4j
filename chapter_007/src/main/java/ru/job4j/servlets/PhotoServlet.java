package ru.job4j.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.logic.Validate;
import ru.job4j.logic.ValidateService;
import ru.job4j.models.User;
import ru.job4j.models.UserDoesNotExist;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/photo")
public class PhotoServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(PhotoServlet.class.getName());

    private final Validate<User> logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = logic.findByID(Integer.parseInt(req.getParameter("id")));
            try (InputStream photo = user.getPhoto();
                ServletOutputStream photoOut = resp.getOutputStream()) {
                photoOut.write(photo.readAllBytes());
            }
        } catch (UserDoesNotExist e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
