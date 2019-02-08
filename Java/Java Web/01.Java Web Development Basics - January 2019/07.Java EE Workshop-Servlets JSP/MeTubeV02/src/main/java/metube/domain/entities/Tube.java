package metube.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "tubes")
public class Tube extends BaseEntity{
    private String title;
    private String author;
    private String description;
    private String youtubeId;
    private Long views;
    private User uploader;

    public Tube() {
    }

    @Column(name = "title", nullable = false)
    public String getTitle() {
        return this.title;
    }

    @Column(name = "author", nullable = false)
    public String getAuthor() {
        return this.author;
    }

    @Column(name = "description")
    public String getDescription() {
        return this.description;
    }

    @Column(name = "youtube_id", nullable = false, updatable = false, unique = true)
    public String getYoutubeId() {
        return this.youtubeId;
    }

    @Column(name = "views", nullable = false)
    public Long getViews() {
        return this.views;
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(
            name = "uploader",
            referencedColumnName = "id"
    )
    public User getUploader() {
        return this.uploader;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public void setUploader(User uploader) {
        this.uploader = uploader;
    }
}
