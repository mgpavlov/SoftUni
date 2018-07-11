package p06RawData2;

public class Cargo {
    private int weight;
    private String type;

    Cargo(int weight, String type){
        this.weight = weight;
        this.type = type;
    }

    public int getWeight() {
        return this.weight;
    }

    public String getType() {
        return this.type;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
