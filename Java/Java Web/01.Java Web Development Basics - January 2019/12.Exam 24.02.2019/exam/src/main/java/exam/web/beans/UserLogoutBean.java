package exam.web.beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Named
@RequestScoped
public class UserLogoutBean extends BaseBean{
    public void logout() throws IOException {
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
        /*FacesContext.getCurrentInstance().getExternalContext().invalidateSession();*/

        /*FacesContext.getCurrentInstance()
                .getExternalContext()
                .redirect("/view/index.xhtml");*/
        this.redirect("index");
    }
}
