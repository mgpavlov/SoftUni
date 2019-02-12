package metube.web.servlets;

import metube.domain.models.service.TubeServiceModel;
import metube.domain.models.view.TubeAllViewModel;
import metube.service.interfaces.TubeService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static metube.constants.Constants.*;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 29.1.2019 г.
 * Time: 11:39 ч.
 */

@WebServlet(TUBE_ALL_URL)
public class TubeAllServlet extends HttpServlet {

    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    @Inject
    public TubeAllServlet(TubeService tubeService, ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        List<TubeServiceModel> tubeServiceModels = this.tubeService
                .findAllTubes();

        if (tubeServiceModels.isEmpty()) {
            req.getRequestDispatcher(TUBE_NO_ALL_FILE_NAME)
                    .forward(req, resp);
            return;
        }

        List<TubeAllViewModel> tubeAllViewModels = tubeServiceModels
                .stream()
                .map(tsm -> this.modelMapper
                        .map(tsm, TubeAllViewModel.class))
                .collect(Collectors.toList());

        req.setAttribute(MAIN_ENTITIES, tubeAllViewModels);

        req.getRequestDispatcher(TUBE_ALL_FILE_NAME)
                .forward(req, resp);
    }
}