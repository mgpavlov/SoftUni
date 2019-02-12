package metube.constants;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 23.1.2019 г.
 * Time: 21:40 ч.
 */
public class Constants {

    public static final String PERSISTENCE_NAME = "metube";

    public static final String METHOD_POST = "POST";

    public static final String PARAMETER_ID = "id";
    public static final String PARAMETER_NAME = "name";
    public static final String PARAMETER_DESC = "description";
    public static final String PARAMETER_LINK = "youtube_link";
    public static final String PARAMETER_UPLOADER = "uploader";

    public static final String MAIN_ENTITIES = "tubes";

    private static final String JSP_VIEWS_RELATIVE_PATH = "/JSPs/%s.jsp";

    private static final String INDEX = "index";
    public static final String INDEX_URL = "/";

    private static final String HOME = "home";
    public static final String HOME_URL = "/" + HOME;
    public static final String HOME_FILE_NAME = String.format(JSP_VIEWS_RELATIVE_PATH, INDEX);

    private static final String TUBE_CREATE = "create";
    public static final String TUBE_CREATE_URL = "/" + MAIN_ENTITIES + "/" + TUBE_CREATE;
    public static final String TUBE_CREATE_FILE_NAME = String.format(JSP_VIEWS_RELATIVE_PATH,
            MAIN_ENTITIES + "-" + TUBE_CREATE);

    private static final String TUBE_ALL = "all";
    public static final String TUBE_ALL_URL = "/" + MAIN_ENTITIES + "/" + TUBE_ALL;
    public static final String TUBE_ALL_FILE_NAME = String.format(JSP_VIEWS_RELATIVE_PATH,
            MAIN_ENTITIES + "-" + TUBE_ALL);
    public static final String TUBE_NO_ALL_FILE_NAME = String.format(JSP_VIEWS_RELATIVE_PATH,
            MAIN_ENTITIES + "-no-" + TUBE_ALL);

    private static final String TUBE_DETAILS = "details";
    public static final String TUBE_DETAILS_URL = "/" + MAIN_ENTITIES + "/" + TUBE_DETAILS;
    public static final String TUBE_DETAILS_URL_QUERY = TUBE_DETAILS_URL + "?" + MAIN_ENTITIES + "Name=";
    public static final String TUBE_DETAILS_FILE_NAME = String.format(JSP_VIEWS_RELATIVE_PATH,
            MAIN_ENTITIES + "-" + TUBE_DETAILS);
    public static final String TUBE_NO_DETAILS_FILE_NAME = String.format(JSP_VIEWS_RELATIVE_PATH,
            MAIN_ENTITIES + "-no-" + TUBE_DETAILS);

    public static final String QUERY_ARG_SPLITTER = "=";
}