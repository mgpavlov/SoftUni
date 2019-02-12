package metube.web.servlets;

import metube.domain.models.binding.TubeCreateBindingModel;
import metube.domain.models.service.TubeServiceModel;
import metube.service.interfaces.TubeService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static metube.constants.Constants.*;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 29.1.2019 г.
 * Time: 11:39 ч.
 */

@WebServlet(TUBE_CREATE_URL)
public class TubeCreateServlet extends HttpServlet {

    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    @Inject
    public TubeCreateServlet(TubeService tubeService,
                             ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher(TUBE_CREATE_FILE_NAME)
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TubeCreateBindingModel tcbm = (TubeCreateBindingModel) req.getAttribute(MAIN_ENTITIES);

        TubeServiceModel tubeServiceModel = this.modelMapper
                .map(tcbm, TubeServiceModel.class);

        this.tubeService.saveTube(tubeServiceModel);

        resp.sendRedirect(TUBE_DETAILS_URL_QUERY + tubeServiceModel.getName());
    }
}