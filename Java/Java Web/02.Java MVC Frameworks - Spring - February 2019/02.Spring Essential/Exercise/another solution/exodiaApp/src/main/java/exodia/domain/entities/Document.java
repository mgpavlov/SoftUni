package exodia.domain.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "documents")
public class Document extends BaseEntity {

    private String title;
    private String content;

    public Document() {
    }

    @Column(name = "title", nullable = false)
    @Length(min = 3)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    @Length(min = 3)
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
