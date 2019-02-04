package fdmc.web.servlets;

import fdmc.domain.entities.Cat;
import fdmc.util.ViewsProvider;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/cats/profile")
public class CatsProfileServlet extends HttpServlet {

    private final ViewsProvider viewsProvider;

    @Inject
    public CatsProfileServlet(ViewsProvider viewsProvider) {
        this.viewsProvider = viewsProvider;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cat cat = ((Map<String, Cat>) req.getSession().getAttribute("cats")).get(req.getQueryString().split("=")[1]);
        String htmFileContent;
        if (cat == null) {
            htmFileContent = this.viewsProvider.view("non-existing")
                    .replace("{{nonExistent}}", req.getQueryString().split("=")[1]);
        } else {
            htmFileContent = this.viewsProvider.view("cat-profile")
                    .replace("{{catName}}", cat.getName())
                    .replace("{{catBreed}}", cat.getBreed())
                    .replace("{{catColor}}", cat.getColor())
                    .replace("{{catAge}}", cat.getAge().toString());
        }

        resp.getWriter().println(htmFileContent);
    }
}
