package web;

import entities.User;
import services.UsersService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {
    private final UsersService usersService;

    @Inject
    public UsersServlet(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        User user = new User();
        user.setName(username);
        usersService.add(user);
        resp.sendRedirect("/");
    }
}
