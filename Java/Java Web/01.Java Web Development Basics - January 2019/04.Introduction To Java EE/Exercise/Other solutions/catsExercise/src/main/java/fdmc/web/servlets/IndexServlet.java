package fdmc.web.servlets;

import fdmc.domain.entities.Cat;
import fdmc.util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import static fdmc.Constants.INDEX_HTML_FILE_PATH;

@WebServlet("/")
public class IndexServlet extends HttpServlet {
    private final HtmlReader reader;

    @Inject
    public IndexServlet(HtmlReader reader) {
        super();
        this.reader = reader;
    }

    @Override
    public void init() throws ServletException {
        this.getServletContext().setAttribute("cats",new LinkedHashMap<>());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();
        writer.println(this.reader.readHtmlFile(INDEX_HTML_FILE_PATH));
    }
}
