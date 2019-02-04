package fdmc.web.servlets;


import fdmc.domain.entities.Cat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/cats/profile")
public class ProfileCatServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String,Cat> cats = (Map<String,Cat>) this.getServletContext().getAttribute("cats");
        String catName = req.getParameter("catName");

        req.getRequestDispatcher("/cats/profile.jsp").forward(req,resp);
    }
}
