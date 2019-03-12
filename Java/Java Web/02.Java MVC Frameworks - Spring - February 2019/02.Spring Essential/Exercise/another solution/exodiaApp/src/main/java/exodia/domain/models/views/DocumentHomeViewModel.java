package exodia.domain.models.views;

public class DocumentHomeViewModel {

    private String id;
    private String title;

    public DocumentHomeViewModel() {
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
