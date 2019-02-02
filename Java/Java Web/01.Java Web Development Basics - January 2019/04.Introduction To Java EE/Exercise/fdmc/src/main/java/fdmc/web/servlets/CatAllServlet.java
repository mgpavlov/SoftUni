package fdmc.web.servlets;

import fdmc.Constants;
import fdmc.domain.entities.Cat;
import fdmc.util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/cats/all")
public class CatAllServlet extends HttpServlet {
    private final HtmlReader htmlReader;

    @Inject
    public CatAllServlet(HtmlReader htmlReader) {
        this.htmlReader = htmlReader;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String htmlFileContent = this.htmlReader.readHtmlFile(Constants.ALL_CATS_HTML_FILE_PATH);

        if(req.getSession().getAttribute("cats") == null){
            htmlFileContent = htmlFileContent
                    .replace("{{allCats}}", Constants.THERE_ARE_NO_CATS_MESSAGE);
        }else {
            StringBuilder allCats = new StringBuilder();

            ((Map<String, Cat>)req.getSession()
                    .getAttribute("cats"))
                    .values()
                    .forEach(cat -> {
                        allCats.append(String.format(Constants.CAT_NAME_AND_DETAILS_URL,
                                cat.getName(), cat.getName()))
                                .append(System.lineSeparator());
                    });

            htmlFileContent = htmlFileContent.replace("{{allCats}}", allCats.toString().trim());

        }
        resp.getWriter().println(htmlFileContent);
    }
}
