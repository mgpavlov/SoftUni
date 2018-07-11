package p11CatLady;

public class Cat {
    private String name;
    private Double characteristic;
    private String type;

    public Cat(String name, Double characteristic, String type) {
        this.setName(name);
        this.setCharacteristic(characteristic);
        this.setType(type);
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public Double getCharacteristic() {
        return this.characteristic;
    }

    private void setCharacteristic(Double characteristic) {
        this.characteristic = characteristic;
    }

    public String getType() {
        return this.type;
    }

    private void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        String value = String.format("%1$.2f",this.getCharacteristic());

        return this.getType() + " " + this.getName() + " " + value;
    }
}
