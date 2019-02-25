package exam.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({
        "/faces/view/print.xhtml",
        "/view/print.xhtml",
        "/print/*",
        "/faces/view/home.xhtml",
        "/view/home.xhtml",
        "/home/",
        "/faces/view/details/*",
        "/view/details/*",
        "/details/*",
        "/faces/view/schedule.xhtml",
        "/view/schedule.xhtml",
        "/schedule/",
})
public class GuestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        if (httpServletRequest.getSession().getAttribute("username") == null) {
            httpServletResponse.sendRedirect("/view/login.xhtml");
            return;
        }
        chain.doFilter(httpServletRequest, httpServletResponse);

        /*String userId = (String) ((HttpServletRequest) servletRequest).getSession().getAttribute("user-id");

        if(userId == null) {
            ((HttpServletResponse) servletResponse).sendRedirect("/views/login.jsf");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);*/
    }
}
