package metube.web.filters;

import metube.domain.models.binding.TubeCreateBindingModel;
import metube.util.ValidationUtil;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/tubes/create")
public class TubeCreateFilter implements Filter {

    private final ValidationUtil validationUtil;

    @Inject
    public TubeCreateFilter(ValidationUtil validationUtil) {
        this.validationUtil = validationUtil;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (req.getMethod().toLowerCase().equals("post")) {
            TubeCreateBindingModel tubeCreateBindingModel = new TubeCreateBindingModel();
            tubeCreateBindingModel.setName(req.getParameter("name"));
            tubeCreateBindingModel.setDescription(req.getParameter("description"));
            tubeCreateBindingModel.setYouTubeLink(req.getParameter("youTubeLink"));
            tubeCreateBindingModel.setUploader(req.getParameter("uploader"));

            if (!this.validationUtil.isValid(tubeCreateBindingModel)) {
                res.sendRedirect("/tubes/error");
                return;
            }
            req.setAttribute("tubeCreateBindingModel", tubeCreateBindingModel);
        }
        chain.doFilter(req, res);
    }
}
