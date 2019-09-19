package ru.job4j.servlets;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.logic.Validate;
import ru.job4j.logic.ValidateService;
import ru.job4j.logic.ValidateStub;
import ru.job4j.models.Role;
import ru.job4j.models.User;
import ru.job4j.models.UserDoesNotExist;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "org.w3c.*", "javax.management.*"})
@PrepareForTest(ValidateService.class)
public class ServletsTest {

    private HttpServletRequest req;
    private HttpServletResponse resp;
    private Validate<User> validate;

    @Before
    public void setUp() {
        validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);
        req = mock(HttpServletRequest.class);
        resp = mock(HttpServletResponse.class);
    }

    @Test
    public void whenAddUserThenStoreIt() throws ServletException, IOException, UserDoesNotExist {
        when(req.getParameter("name")).thenReturn("Eugene Kuchumov");
        new UserCreateServlet().doPost(req, resp);
        assertThat(validate.findByID(1).getName(), is("Eugene Kuchumov"));
    }

    @Test
    public void whenRequestAllUsersThenGetAllUsers() throws ServletException, IOException, UserDoesNotExist {
        when(req.getParameter("name")).thenReturn("Eugene");
        new UserCreateServlet().doPost(req, resp);
        when(req.getParameter("name")).thenReturn("Kristina");
        new UserCreateServlet().doPost(req, resp);
        assertThat(validate.findAll().size(), is(2));
        assertThat(validate.findByID(1).getName(), is("Eugene"));
        assertThat(validate.findByID(2).getName(), is("Kristina"));
    }

    @Test
    public void whenDeleteOneUserFromTwoThenItOneUserRemains() throws IOException, ServletException, UserDoesNotExist {
        when(req.getParameter("name")).thenReturn("Eugene");
        new UserCreateServlet().doPost(req, resp);
        when(req.getParameter("name")).thenReturn("Kristina");
        new UserCreateServlet().doPost(req, resp);
        when(req.getParameter("id")).thenReturn("1");
        new UsersServlet().doPost(req, resp);
        assertThat(validate.findAll().size(), is(1));
        assertThat(validate.findByID(2).getName(), is("Kristina"));
    }

    @Test
    public void whenWeChangeTheUserThenItIsUpdated() throws IOException, ServletException, UserDoesNotExist {
        when(req.getParameter("name")).thenReturn("Eugene");
        new UserCreateServlet().doPost(req, resp);
        User root = new User(0);
        root.setRole(new Role("root"));
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("user")).thenReturn(root);
        when(req.getSession()).thenReturn(session);
        when(req.getParameter("id")).thenReturn("1");
        when(req.getParameter("name")).thenReturn("Kristina");
        new UserUpdateServlet().doPost(req, resp);
        assertThat(validate.findByID(1).getName(), is("Kristina"));
    }
}