package ExamPrep1.models;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;

public class Organism{
    private String name;
    private ArrayDeque<Cluster> clusters;

    public Organism(String name) {
        this.setName(name);
        this.clusters = new ArrayDeque<>();
    }

    public String getName() {
        return this.name;
    }

    public ArrayDeque<Cluster> getClusters() {
        return this.clusters;
    }

    public void addCluster(Cluster cluster) {
        this.clusters.add(cluster);
    }

    private void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Organism - ").append(this.getName()).append(System.lineSeparator())
                .append("--Clusters: ").append(this.getClusters().size()).append(System.lineSeparator())
                .append("--Cells: ").append(this.getClusters().stream().mapToInt(c->c.getCells().size()).sum()).append(System.lineSeparator());
        this.getClusters().forEach(c->{
            sb.append("----Cluster ").append(c.getId()).append(System.lineSeparator());
            c.getCells().stream().sorted((a,b)->{
                if (Integer.compare(a.getPositionRow(), b.getPositionCol()) == 0){
                    return Integer.compare(a.getPositionCol(), b.getPositionCol());
                }
                return Integer.compare(a.getPositionRow(), b.getPositionCol());
            }).forEach(cell->{
               sb.append(cell.toString());
            });
        });
        return sb.toString();
    }
}
