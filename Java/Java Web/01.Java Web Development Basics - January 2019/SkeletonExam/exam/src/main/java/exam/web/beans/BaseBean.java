package exam.web.beans;

import javax.faces.context.FacesContext;
import java.io.IOException;

abstract class BaseBean {
    void redirect(String url) {
        try {
            FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .redirect("/view/" + url + ".xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
