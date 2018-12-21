package cars.dto.utilities;

public class CustomerIdYoungDriverDto {

    private long id;

    private boolean isYoungDriver;

    public CustomerIdYoungDriverDto() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isYoungDriver() {
        return this.isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }
}
