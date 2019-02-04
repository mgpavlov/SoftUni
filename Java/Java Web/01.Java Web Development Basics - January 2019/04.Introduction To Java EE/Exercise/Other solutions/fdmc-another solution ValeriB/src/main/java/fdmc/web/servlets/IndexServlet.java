package fdmc.web.servlets;

import fdmc.util.ViewsProvider;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/")
public class IndexServlet extends HttpServlet {
    private final ViewsProvider viewsProvider;

    @Inject
    public IndexServlet( ViewsProvider viewsProvider) {
        this.viewsProvider = viewsProvider;

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        writer.println(this.viewsProvider.view("Index"));
    }
}
