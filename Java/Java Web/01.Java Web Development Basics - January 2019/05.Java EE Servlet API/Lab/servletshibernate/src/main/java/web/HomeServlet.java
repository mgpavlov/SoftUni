package web;

import viewmodels.HomeViewModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static constants.ViewConstants.VIEW_MODEL_ATTRIBUTE_NAME;

@WebServlet("/")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Prepare Request
        String name = "Gosho";
        int age = 3;

        req.setAttribute(VIEW_MODEL_ATTRIBUTE_NAME, new HomeViewModel(name, age));

        req.getRequestDispatcher("home.jsp")
                .forward(req, resp);
    }
}
