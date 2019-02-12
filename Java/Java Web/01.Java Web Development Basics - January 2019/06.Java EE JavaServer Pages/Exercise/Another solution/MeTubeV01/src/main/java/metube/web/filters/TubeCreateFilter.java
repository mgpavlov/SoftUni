package metube.web.filters;

import metube.domain.models.binding.TubeCreateBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static metube.constants.Constants.*;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 5.2.2019 г.
 * Time: 13:30 ч.
 */
@WebFilter(TUBE_CREATE_URL)
public class TubeCreateFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if (METHOD_POST.equalsIgnoreCase(req.getMethod())) {

            TubeCreateBindingModel tcbm = new TubeCreateBindingModel();

            tcbm.setName(req.getParameter(PARAMETER_NAME));
            tcbm.setDescription(req.getParameter(PARAMETER_DESC));
            tcbm.setYouTubeLink(req.getParameter(PARAMETER_LINK));
            tcbm.setUploader(req.getParameter(PARAMETER_UPLOADER));

            req.setAttribute(MAIN_ENTITIES, tcbm);
        }

        filterChain.doFilter(req, resp);
    }
}