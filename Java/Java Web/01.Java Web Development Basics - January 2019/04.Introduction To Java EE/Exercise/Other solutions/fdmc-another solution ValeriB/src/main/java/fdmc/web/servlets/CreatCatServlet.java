package fdmc.web.servlets;

import fdmc.domain.entities.Cat;
import fdmc.util.ViewsProvider;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/cats/create")
public class CreatCatServlet extends HttpServlet {

    private final ViewsProvider viewsProvider;

    @Inject
    public CreatCatServlet(ViewsProvider viewsProvider) {
        this.viewsProvider = viewsProvider;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        writer.write(this.viewsProvider.view("create-cat"));
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cat cat = new Cat();
        cat.setName(req.getParameter("name"));
        cat.setBreed(req.getParameter("breed"));
        cat.setColor(req.getParameter("color"));
        cat.setAge(Integer.parseInt(req.getParameter("age")));

        if (req.getSession().getAttribute("cats") == null) {
            req.getSession().setAttribute("cats", new LinkedHashMap<>());
        }

        ((Map<String, Cat>) req.getSession().getAttribute("cats")).putIfAbsent(cat.getName(), cat);

        resp.sendRedirect(String.format("/cats/profile?catName=%s", cat.getName()));
    }
}
