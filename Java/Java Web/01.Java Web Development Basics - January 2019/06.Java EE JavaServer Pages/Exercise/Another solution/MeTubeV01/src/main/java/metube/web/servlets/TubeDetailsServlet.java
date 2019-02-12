package metube.web.servlets;

import metube.domain.models.service.TubeServiceModel;
import metube.domain.models.view.TubeDetailsViewModel;
import metube.service.interfaces.TubeService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static metube.constants.Constants.*;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 29.1.2019 г.
 * Time: 11:39 ч.
 */

@WebServlet(TUBE_DETAILS_URL)
public class TubeDetailsServlet extends HttpServlet {

    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    @Inject
    public TubeDetailsServlet(TubeService tubeService,
                              ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        String tubeName = req.getQueryString()
                .split(QUERY_ARG_SPLITTER)[1];

        Optional<TubeServiceModel> tubeServiceModel = this.tubeService
                .findTubeByName(tubeName);

        if (tubeServiceModel.isEmpty()) {

            req.setAttribute(PARAMETER_NAME, tubeName);

            req.getRequestDispatcher(TUBE_NO_DETAILS_FILE_NAME)
                    .forward(req, resp);
            return;
        }

        TubeDetailsViewModel tubeDetailsViewModel = this.modelMapper
                .map(tubeServiceModel.get(), TubeDetailsViewModel.class);

        req.setAttribute(MAIN_ENTITIES, tubeDetailsViewModel);

        req.getRequestDispatcher(TUBE_DETAILS_FILE_NAME)
                .forward(req, resp);
    }
}