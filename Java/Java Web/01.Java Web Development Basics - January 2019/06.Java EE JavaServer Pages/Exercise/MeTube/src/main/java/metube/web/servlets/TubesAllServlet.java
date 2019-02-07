package metube.web.servlets;


import metube.domain.models.view.AllTubesViewModel;
import metube.service.TubeService;
import metube.util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet("/tubes/all")
public class TubesAllServlet extends HttpServlet {

    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    @Inject
    public TubesAllServlet(TubeService tubeService, ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("allTubes", this.tubeService.findAllTubes()
                .stream()
                .map(t -> this.modelMapper.map(t, AllTubesViewModel.class))
                .collect(Collectors.toList()));

        req.getRequestDispatcher("/jsps/all-tubes.jsp").forward(req, resp);
    }
}
