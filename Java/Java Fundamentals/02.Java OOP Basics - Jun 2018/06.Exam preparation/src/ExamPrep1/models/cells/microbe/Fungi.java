package ExamPrep1.models.cells.microbe;

public class Fungi extends Microbe {
    public Fungi(String id, int health, int positionRow, int positionCol, int virulence) {
        super(id, health, positionRow, positionCol, virulence);
    }

    @Override
    public int calculateEnergy() {
        return (super.getHealth() + super.getVirulence())/4;
    }

    @Override
    public String getType() {
        return "Fungi";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(this.calculateEnergy()).append(System.lineSeparator());
        return sb.toString();
    }
}
