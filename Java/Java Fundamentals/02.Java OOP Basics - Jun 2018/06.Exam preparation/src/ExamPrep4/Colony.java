package ExamPrep4;

import ExamPrep4.colonists.Colonist;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Colony {
    private int maxFamilyCount;
    private int maxFamilyCapacity;
    private Map<String, List<Colonist>> colonists;

    public Colony(int maxFamilyCount, int maxFamilyCapacity) {
        this.maxFamilyCount = maxFamilyCount;
        this.maxFamilyCapacity = maxFamilyCapacity;
        this.colonists = new LinkedHashMap<>();
    }

    public int getMaxFamilyCount() {
        return this.maxFamilyCount;
    }

    public int getMaxFamilyCapacity() {
        return this.maxFamilyCapacity;
    }

    public List<Colonist> getColonistsByFamilyId(String familyId) {
        return this.colonists.get(familyId).stream()
                .sorted(Comparator.comparing(Colonist::getId))
                .collect(Collectors.toList());
    }

    public void addColonist(Colonist colonist){

    }

    public void removeColonist(String familyId, String memberId){

    }

    public void removeFamily(String id){

    }

    public void grow(int years){

    }

    public Map<String, List<Colonist>> getColonists() {
        return this.colonists;
    }

    public int getPotential(){
        return 0;
    }

    public String getCapacity(){
        return null;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
