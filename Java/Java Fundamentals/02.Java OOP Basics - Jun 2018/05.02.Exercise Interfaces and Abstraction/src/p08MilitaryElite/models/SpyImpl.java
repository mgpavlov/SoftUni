package p08MilitaryElite.models;

import p08MilitaryElite.interfaces.Spy;

public class SpyImpl extends SoldierImpl implements Spy {
    private int codeNumber;

    public SpyImpl(int id, String name, String lastName, int codeNumber) {
        super(id, name, lastName);
        this.setCodeNumber(codeNumber);
    }

    @Override
    public int getCodeNumber() {
        return this.codeNumber;
    }

    private void setCodeNumber(int codeNumber) {
        this.codeNumber = codeNumber;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(super.toString());
        builder.append("\n")
                .append(String.format("Code Number: %d", this.getCodeNumber()));

        return builder.toString();
    }
}
