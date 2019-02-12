package metube.domain.models.binding;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 5.2.2019 г.
 * Time: 13:28 ч.
 */
public class TubeCreateBindingModel {
    private String name;
    private String description;
    private String youTubeLink;
    private String uploader;

    public TubeCreateBindingModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYouTubeLink() {
        return youTubeLink;
    }

    public void setYouTubeLink(String youTubeLink) {
        this.youTubeLink = youTubeLink;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }
}