package ExamPrep1.models.cells.bloodCells;

public class RedBloodCell extends BloodCell {
    private int velocity;

    public RedBloodCell(String id, int health, int positionRow, int positionCol, int velocity) {
        super(id, health, positionRow, positionCol);
        this.setVelocity(velocity);

    }
    private void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public int getVelocity() {
        return this.velocity;
    }

    @Override
    public int calculateEnergy() {
        return (super.getHealth() + this.getVelocity());
    }

    @Override
    public String getType() {
        return "RedBloodCell";
    }

    @Override
    public int getParameter() {
        return this.velocity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(String.format("--------Health: %d | Velocity: %d | Energy: %d",super.getHealth(), this.getVelocity(), this.calculateEnergy()))
                .append(System.lineSeparator());
        return sb.toString();
    }
}
