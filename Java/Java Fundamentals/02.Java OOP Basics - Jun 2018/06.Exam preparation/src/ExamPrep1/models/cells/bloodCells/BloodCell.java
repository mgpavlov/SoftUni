package ExamPrep1.models.cells.bloodCells;

import ExamPrep1.models.cells.Cell;

public abstract class BloodCell extends Cell {

    protected BloodCell(String id, int health, int positionRow, int positionCol) {
        super(id, health, positionRow, positionCol);
    }

}
