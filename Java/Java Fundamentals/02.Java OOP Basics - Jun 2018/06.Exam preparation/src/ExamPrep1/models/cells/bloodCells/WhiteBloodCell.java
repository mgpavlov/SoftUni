package ExamPrep1.models.cells.bloodCells;

public class WhiteBloodCell extends BloodCell {
    private int size;

    public WhiteBloodCell(String id, int health, int positionRow, int positionCol, int size) {
        super(id, health, positionRow, positionCol);
        this.setSize(size);
    }

    public int getSize() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    @Override
    public int calculateEnergy() {
        return (super.getHealth() + this.getSize())*2;
    }

    @Override
    public String getType() {
        return "WhiteBloodCell";
    }

    @Override
    public int getParameter() {
        return this.size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(String.format("--------Health: %d | Size: %d | Energy: %d", super.getHealth(), this.getSize(), this.calculateEnergy()))
                .append(System.lineSeparator());
        return sb.toString();
    }
}
