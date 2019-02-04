package fdmc.web.servlets;

import fdmc.domain.entities.Cat;
import fdmc.util.HtmlReader;
import fdmc.util.ViewsProvider;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/cats/all")
public class CatsAllServlet extends HttpServlet {

    private final ViewsProvider viewsProvider;

    @Inject
    public CatsAllServlet(ViewsProvider viewsProvider) {
        this.viewsProvider = viewsProvider;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String htmlFileContent = viewsProvider.view("all-cats");

        if (req.getSession().getAttribute("cats") == null) {
            htmlFileContent = htmlFileContent
                    .replace("{{allCats}}",
                            "There are no cats. <a href=\"/cats/create\">Create some!</a>");
        } else {
            StringBuilder catToString = new StringBuilder();

            ((Map<String,Cat>)req.getSession().getAttribute("cats")).values().stream()
                    .forEach(cat -> catToString.append(String.format("<a href=\"/cats/profile?catName=%s\">%s</a><br/>",cat.getName(),cat.getName())).append(System.lineSeparator()));

            htmlFileContent= htmlFileContent.replace("{{allCats}}", catToString.toString().trim());
        }
        resp.getWriter().write(htmlFileContent);
    }
}

