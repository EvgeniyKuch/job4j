package ru.job4j.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.models.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(value = "/ajax")
public class Ajax extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(Ajax.class.getName());
    private final Map<Integer, Client> clients = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger(0);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder json = new StringBuilder(reader.readLine());
        ObjectMapper mapper = new ObjectMapper();
        Client client = mapper.readValue(json.toString(), Client.class);
        clients.put(id.incrementAndGet(), client);
    }
}
