package app.ccb.constants;

public final class ResourcesPath {
    private static final String FILE_BASE = "/files";
    private static final String JSON_BASE =FILE_BASE +  "/json";
    private static final String XML_BASE =FILE_BASE +  "/xml";

    public final class JSON {

        public static final String BRANCHES = JSON_BASE + "/branches.json";
        public static final String CLIENTS = JSON_BASE + "/clients.json";
        public static final String EMPLOYEES = JSON_BASE + "/employees.json";

        private JSON() {

        }
    }

    public final class XML {

        public static final String BANK_ACCOUNTS = XML_BASE+ "/bank-accounts.xml";
        public static final String CARDS = XML_BASE+ "/cards.xml";

        private XML() {

        }
    }

    private ResourcesPath() {
    }
}
