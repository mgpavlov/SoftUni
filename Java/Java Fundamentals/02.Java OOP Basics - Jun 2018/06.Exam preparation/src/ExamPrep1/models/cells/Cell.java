package ExamPrep1.models.cells;

import java.awt.font.ShapeGraphicAttribute;

public abstract class Cell {
    private String id;
    private int health;
    private int positionRow;
    private int positionCol;


    protected Cell(String id, int health, int positionRow, int positionCol) {
        this.setId(id);
        this.setHealth(health);
        this.setPositionRow(positionRow);
        this.setPositionCol(positionCol);
    }

    public String getId() {
        return this.id;
    }


    public int getHealth() {
        return this.health;
    }


    public int getPositionRow() {
        return this.positionRow;
    }


    public int getPositionCol() {
        return this.positionCol;
    }

    private void setId(String id) {
        this.id = id;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    private void setPositionRow(int positionRow) {
        this.positionRow = positionRow;
    }

    private void setPositionCol(int positionCol) {
        this.positionCol = positionCol;
    }

    public abstract int calculateEnergy();
    public abstract String getType();
    public abstract int getParameter();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(String.format("------Cell %s [%d,%d]", this.id, this.positionRow, this.positionCol));
        sb.append(System.lineSeparator());
        return sb.toString();
    }


}
