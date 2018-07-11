package p08MilitaryElite.models;


import p08MilitaryElite.interfaces.Soldier;

public abstract class SoldierImpl implements Soldier {
    private int id;
    private String name;
    private String lastName;

    protected SoldierImpl(int id, String name, String lastName) {
        this.setId(id);
        this.setName(name);
        this.setLastName(lastName);
    }

    @Override
    public int getId() {
        return this.id;
    }

    private void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Name: %s %s Id: %s", this.getName(), this.getLastName(), this.getId()));

        return builder.toString();
    }
}
