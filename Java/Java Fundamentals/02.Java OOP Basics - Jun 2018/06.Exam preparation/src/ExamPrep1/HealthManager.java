package ExamPrep1;

import ExamPrep1.models.Cluster;
import ExamPrep1.models.Organism;
import ExamPrep1.models.cells.Cell;
import ExamPrep1.models.cells.bloodCells.RedBloodCell;
import ExamPrep1.models.cells.bloodCells.WhiteBloodCell;
import ExamPrep1.models.cells.microbe.Bacteria;
import ExamPrep1.models.cells.microbe.Fungi;
import ExamPrep1.models.cells.microbe.Virus;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class HealthManager {
    private Map<String, Organism> namesOrganisms;

    public HealthManager() {
        this.namesOrganisms = new LinkedHashMap<>();
    }

    public String checkCondition(String organismName) {
        try {
            return namesOrganisms.get(organismName).toString();

        } catch (Exception ignored) {
            return "";
        }
    }

    public String createOrganism(String name) {
        if (namesOrganisms.containsKey(name)) {
            return "Organism " + name + " already exists\n";
        }
        Organism organism = new Organism(name);
        namesOrganisms.put(name, organism);
        return "Created organism " + name + "\n";
    }

    public String addCluster(String organismName, String id, int rows, int cols) {
        if (!namesOrganisms.containsKey(organismName)) {
            return "";
        }
        for (Cluster cluster : namesOrganisms.get(organismName).getClusters()) {
            if (cluster.getId().equals(id)) {
                return "";
            }
        }

        if (rows < 0 || cols < 0) {
            return "";
        }
        Cluster cluster = new Cluster(id, rows, cols);

        namesOrganisms.get(organismName).addCluster(cluster);
        return "Organism " + organismName + ": Created cluster " + id + "\n";
    }

    public String addCell(String organismName, String clusterId, String cellType, String cellId,
                          int health, int positionRow, int positionCol, int additionalProperty) {
        if (!namesOrganisms.containsKey(organismName)) {
            return "";
        }
        if (namesOrganisms.get(organismName).getClusters().stream().noneMatch(c -> c.getId().equals(clusterId))) {
            return "";
        }
        for (Cluster cluster : namesOrganisms.get(organismName).getClusters()) {
            if (cluster.getId().equals(clusterId)) {
                if (positionRow >= cluster.getRows() || positionRow < 0) {
                    return "";
                }
                if (positionCol >= cluster.getCols() || positionCol < 0) {
                    return "";
                }
            }
        }

        Cell cell = null;
        switch (cellType) {
            case "RedBloodCell":
                cell = new RedBloodCell(cellId, health, positionRow, positionCol, additionalProperty);
                break;
            case "WhiteBloodCell":
                cell = new WhiteBloodCell(cellId, health, positionRow, positionCol, additionalProperty);
                break;
            case "Bacteria":
                cell = new Bacteria(cellId, health, positionRow, positionCol, additionalProperty);
                break;
            case "Virus":
                cell = new Virus(cellId, health, positionRow, positionCol, additionalProperty);
                break;
            case "Fugi":
                cell = new Fungi(cellId, health, positionRow, positionCol, additionalProperty);
                break;
            default:
                return "";
        }

        Cell finalCell = cell;
        namesOrganisms.get(organismName).getClusters().stream().filter(c -> c.getId().equals(clusterId)).limit(1).forEach(c -> c.addCells(finalCell));

        return "Organism " + organismName + ": Created cell " + cellId + " in cluster " + clusterId + "\n";

    }

/*    public String activateCluster(String organismName) {
        if (this.namesOrganisms.containsKey(organismName)) {
            if (this.namesOrganisms.get(organismName).getClusters().size() > 0) {

                Cluster cluster = this.namesOrganisms.get(organismName).getClusters().get(0);

                cluster.activate();

                this.namesOrganisms.get(organismName).moveClusterToTheEnd();

                return String.format("Organism %s: Activated cluster %s. " +
                        "Cells left: %d%n", organismName, cluster.getId(), cluster.getCellsCount());
            }
        }
        return "";
    }*/

   /* public String activateCluster(String organismName) {
        if (!namesOrganisms.containsKey(organismName)) {
            return "";
        }
        Cluster cluster = namesOrganisms.get(organismName).getClusters().pop();
        String clusterId = cluster.getId();
        Cluster newCluster = new Cluster(clusterId, cluster.getRows(), cluster.getCols());
        newCluster.addCells(clusterActivation(cluster));
        namesOrganisms.get(organismName).addCluster(newCluster);
        return "Organism " + organismName + ": Activated cluster " + clusterId + ". Cells left: " + "1\n";
    }

    private Cell clusterActivation(Cluster cluster) {
        Deque<Cell> cells = cluster.getCells().stream().sorted((c1, c2) -> {
            if (Integer.compare(c1.getPositionRow(), c2.getPositionRow()) == 0) {
                return Integer.compare(c1.getPositionCol(), c2.getPositionCol());
            }
            return Integer.compare(c1.getPositionRow(), c2.getPositionRow());
        }).collect(Collectors.toCollection(ArrayDeque::new));


        Cell firstCell = cells.getFirst();
        Cell lastCell = cells.getLast();
        int totalHealth = cells.stream().mapToInt(Cell::getHealth).sum();
        if (firstCell.getType().equals("WhiteBloodCell")) {
            return new WhiteBloodCell(firstCell.getId(), totalHealth, lastCell.getPositionRow(), lastCell.getPositionCol(), firstCell.getParameter());
        } else if (firstCell.getType().equals("RedBloodCell")) {
            return new RedBloodCell(firstCell.getId(), totalHealth, lastCell.getPositionRow(), lastCell.getPositionCol(), firstCell.getParameter());
        } else if (firstCell.getType().equals("Virus")) {
            while (cells.size() > 1) {
                Cell cellFirst = cells.pop();
                Cell cell2nd = cells.pop();
                if (cellFirst.getType().equals("WhiteBloodCell")) {
                    return new WhiteBloodCell(cellFirst.getId(), totalHealth, lastCell.getPositionRow(), lastCell.getPositionCol(), cellFirst.getParameter());
                } else if (cellFirst.getType().equals("RedBloodCell")) {
                    return new RedBloodCell(cellFirst.getId(), totalHealth, lastCell.getPositionRow(), lastCell.getPositionCol(), cellFirst.getParameter());
                }

                int health1stCell = cellFirst.getHealth();
                int energy1stCell = cellFirst.calculateEnergy();

                int health2ndCell = cell2nd.getHealth();
                int energy2ndCell = cell2nd.calculateEnergy();

                while (true) {
                    health2ndCell -= energy1stCell;
                    if (health2ndCell <= 0) {
                        Cell newCell = new Virus(cellFirst.getId(), health1stCell, cell2nd.getPositionRow(), cell2nd.getPositionCol(), cellFirst.getParameter());
                        cells.push(newCell);
                        break;
                    }
                    health1stCell -= energy2ndCell;
                    if (health1stCell <= 0) {
                        cell2nd.setHealth(health2ndCell);
                        cells.push(cell2nd);
                        break;
                    }
                }
            }
        } else if (firstCell.getType().equals("Fungi")) {
            while (cells.size() > 1) {
                Cell cellFirst = cells.pop();
                Cell cell2nd = cells.pop();
                if (cellFirst.getType().equals("WhiteBloodCell")) {
                    return new WhiteBloodCell(cellFirst.getId(), totalHealth, lastCell.getPositionRow(), lastCell.getPositionCol(), cellFirst.getParameter());
                } else if (cellFirst.getType().equals("RedBloodCell")) {
                    return new RedBloodCell(cellFirst.getId(), totalHealth, lastCell.getPositionRow(), lastCell.getPositionCol(), cellFirst.getParameter());
                }
                int health1stCell = cellFirst.getHealth();
                int energy1stCell = cellFirst.calculateEnergy();

                int health2ndCell = cell2nd.getHealth();
                int energy2ndCell = cell2nd.calculateEnergy();

                while (true) {
                    health2ndCell -= energy1stCell;
                    if (health2ndCell <= 0) {
                        Cell newCell = new Fungi(cellFirst.getId(), health1stCell, cell2nd.getPositionRow(), cell2nd.getPositionCol(), cellFirst.getParameter());
                        cells.push(newCell);
                        break;
                    }
                    health1stCell -= energy2ndCell;
                    if (health1stCell <= 0) {
                        cell2nd.setHealth(health2ndCell);
                        cells.push(cell2nd);
                        break;
                    }
                }
            }
        } else if (firstCell.getType().equals("Bacteria")) {
            while (cells.size() > 1) {
                Cell cellFirst = cells.pop();
                Cell cell2nd = cells.pop();
                if (cellFirst.getType().equals("WhiteBloodCell")) {
                    return new WhiteBloodCell(cellFirst.getId(), totalHealth, lastCell.getPositionRow(), lastCell.getPositionCol(), cellFirst.getParameter());
                } else if (cellFirst.getType().equals("RedBloodCell")) {
                    return new RedBloodCell(cellFirst.getId(), totalHealth, lastCell.getPositionRow(), lastCell.getPositionCol(), cellFirst.getParameter());
                }
                int health1stCell = cellFirst.getHealth();
                int energy1stCell = cellFirst.calculateEnergy();

                int health2ndCell = cell2nd.getHealth();
                int energy2ndCell = cell2nd.calculateEnergy();

                while (true) {
                    health2ndCell -= energy1stCell;
                    if (health2ndCell <= 0) {
                        Cell newCell = new Bacteria(cellFirst.getId(), health1stCell, cell2nd.getPositionRow(), cell2nd.getPositionCol(), cellFirst.getParameter());
                        cells.push(newCell);
                        break;
                    }
                    health1stCell -= energy2ndCell;
                    if (health1stCell <= 0) {
                        cell2nd.setHealth(health2ndCell);
                        cells.push(cell2nd);
                        break;
                    }
                }
            }
        }
        return cells.peek();
    }*/

}
