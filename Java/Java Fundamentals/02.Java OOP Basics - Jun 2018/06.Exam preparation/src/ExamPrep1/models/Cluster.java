package ExamPrep1.models;

import ExamPrep1.models.cells.Cell;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;

public class Cluster {
    private String id;
    private int rows;
    private int cols;
    private Collection<Cell> cells;

    public Cluster(String id, int rows, int cols) {
        this.setId(id);
        this.setRows(rows);
        this.setCols(cols);
        this.cells = new ArrayDeque<>();
    }

    public String getId() {
        return this.id;
    }

    public int getRows() {
        return this.rows;
    }

    public int getCols() {
        return this.cols;
    }

    public Collection<Cell> getCells() {
        return Collections.unmodifiableCollection(this.cells);
    }

    public void addCells(Cell cell) {
        this.cells.add(cell);
    }

    private void setId(String id) {
        this.id = id;
    }

    private void setRows(int rows) {
        this.rows = rows;
    }

    private void setCols(int cols) {
        this.cols = cols;
    }

    /*public void activate() {
        Deque<Cell> cells = new ArrayDeque<>();

        while (cells.size() > 0) {

            if (cells.size() == 1) {
                break;
            }

            Cell startCell = cells.pop();

            int row = cells.getFirst().getPositionRow();
            int col = cells.getFirst().getPositionCol();

            Cell secondCell = cells.pop();
            Cell winner = fight(startCell, secondCell);
            cells.push(winner);
        }
    }*/

   /* private Cell fight(Cell startCell, Cell cell) {
        String cellType = startCell.getClass().getSimpleName();

        switch (cellType) {
            case "RedBloodCell":
            case "WhiteBloodCell":
                startCell.setHealth(startCell.getHealth() + cell.getHealth());
                startCell.setPositionRow(cell.getPositionRow());
                startCell.setPositionCol(cell.getPositionCol());
                return startCell;
            default:
                while (true) {
                    int startCellHealth = startCell.getHealth();
                    int secondCellHealth = cell.getHealth();

                    cell.setHealth(cell.getHealth() - startCell.getEnergy());

                    if (cell.getHealth() <= 0) {
                        startCell.setPositionRow(cell.getPositionRow());
                        startCell.setPositionCol(cell.getPositionCol());
                        return startCell;
                    }
                    startCell.setHealth(startCell.getHealth() - cell.getEnergy());

                    if (startCell.getHealth() <= 0) {
                        cell.setPositionRow(startCell.getPositionRow());
                        cell.setPositionCol(startCell.getPositionCol());
                        return cell;
                    }
                }
        }
    }*/
}
