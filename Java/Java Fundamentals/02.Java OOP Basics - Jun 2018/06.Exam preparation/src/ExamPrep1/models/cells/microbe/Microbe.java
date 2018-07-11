package ExamPrep1.models.cells.microbe;

import ExamPrep1.models.cells.Cell;
public abstract class Microbe extends Cell {
    private int virulence;
    protected Microbe(String id, int health, int positionRow, int positionCol, int virulence) {
        super(id, health, positionRow, positionCol);
        this.setVirulence(virulence);
    }

    public int getVirulence() {
        return this.virulence;
    }

    private void setVirulence(int virulence) {
        this.virulence = virulence;
    }
    @Override
    public int getParameter() {
        return this.virulence;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(String.format("--------Health: %d | Virulence: %d | Energy: ", super.getHealth(), this.virulence));
        return sb.toString();
    }

}
