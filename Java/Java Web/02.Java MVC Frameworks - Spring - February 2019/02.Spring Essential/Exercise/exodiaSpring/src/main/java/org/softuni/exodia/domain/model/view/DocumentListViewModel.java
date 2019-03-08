package org.softuni.exodia.domain.model.view;

public class DocumentListViewModel {
    private String id;
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title.length() > 12 ? title.substring(0, 12) + "..." : title;
    }
}
