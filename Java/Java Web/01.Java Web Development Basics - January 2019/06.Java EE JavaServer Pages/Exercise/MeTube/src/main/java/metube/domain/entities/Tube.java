package metube.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity(name = "tubes")
public class Tube extends BaseEntity {

    private String name;
    private String description;
    private String youTubeLink;
    private String uploader;

    public Tube() {
    }

    @NotNull
    @Size(min = 1)
    @Column(name = "name", unique = true)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @Size(min = 1)
    @Column(name = "description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "youTube_link")
    @Pattern(regexp = "https://www\\.youtube\\.com/.*")
    public String getYouTubeLink() {
        return this.youTubeLink;
    }

    public void setYouTubeLink(String youTubeLink) {
        this.youTubeLink = youTubeLink;
    }

    @Column(name = "uploader")
    @NotNull
    @Size(min = 2)
    public String getUploader() {
        return this.uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

}
