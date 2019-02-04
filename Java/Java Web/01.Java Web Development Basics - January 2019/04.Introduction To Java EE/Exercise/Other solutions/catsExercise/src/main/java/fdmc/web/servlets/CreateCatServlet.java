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
import java.net.URLEncoder;
import java.util.Map;

import static fdmc.Constants.CAT_CREATE_HTML_FILE_PATH;

@WebServlet("/cats/create")
public class CreateCatServlet extends HttpServlet {
    private final HtmlReader reader;

    @Inject
    public CreateCatServlet(HtmlReader reader) {
        this.reader = reader;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileContent = this.reader.readHtmlFile(CAT_CREATE_HTML_FILE_PATH);
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println(fileContent);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = new String(req.getParameter("catName").getBytes("iso-8859-1"), "utf-8");
        String breed = new String(req.getParameter("breed").getBytes("iso-8859-1"), "utf-8");
        String color = new String(req.getParameter("color").getBytes("iso-8859-1"), "utf-8");
        String age = new String(req.getParameter("age").getBytes("iso-8859-1"), "utf-8");

        Cat cat=new Cat(name,breed,color,Integer.parseInt(age));
        ((Map<String,Cat>)this.getServletContext().getAttribute("cats")).put(cat.getName(),cat);

        String encode = URLEncoder.encode(cat.getName(), "utf-8");
        String urlToProfile = String.format("/cats/profile?catName=%s",
                encode);
//        resp.setContentType("text/html;charset=UTF-8");
        resp.sendRedirect(urlToProfile);
    }
}
