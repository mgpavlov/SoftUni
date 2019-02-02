package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebFilter("/users")
public class DateFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setAttribute("currentDate", new Date());
        ((HttpServletResponse)servletResponse)
                .sendRedirect("/");
//        filterChain.doFilter(servletRequest, servletResponse);
    }
}
