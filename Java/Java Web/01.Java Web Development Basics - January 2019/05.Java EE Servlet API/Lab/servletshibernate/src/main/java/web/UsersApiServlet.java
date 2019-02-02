package web;

import entities.User;
import services.JsonParser;
import services.UsersService;
import services.implementations.GsonJsonParser;
import services.implementations.UsersServiceImpl;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/users")
public class UsersApiServlet extends HttpServlet {
    private final UsersService usersService;
    private final JsonParser jsonParser;

    @Inject
    public UsersApiServlet(UsersService usersService, JsonParser jsonParser) {
        this.usersService = usersService;
        this.jsonParser = jsonParser;
    }

    public UsersApiServlet() {
        this(new UsersServiceImpl(), new GsonJsonParser());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = usersService.getAllUsers();
        String usersJson = jsonParser.toJson(users);

        resp.setHeader("Content-Type", "application/json");

        resp.getWriter()
                .write(usersJson);
    }
}
