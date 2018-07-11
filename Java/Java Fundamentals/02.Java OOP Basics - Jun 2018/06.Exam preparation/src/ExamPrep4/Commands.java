package ExamPrep4;

import ExamPrep4.colonists.Colonist;
import ExamPrep4.colonists.engineers.HardwareEngineer;
import ExamPrep4.colonists.engineers.SoftwareEngineer;
import ExamPrep4.colonists.medics.GeneralPractitioner;
import ExamPrep4.colonists.medics.Surgeon;
import ExamPrep4.colonists.soldiers.Soldier;

import java.util.ArrayList;

public class Commands {
    private Colony colony;
    public Commands(int familyCount, int familyCapacity) {
        colony = new Colony(familyCount, familyCapacity);
    }

    public void insertMedics ( String className, String colonistId, String familyId, int talent, int age, String sign){
        if (this.isColonyFull()){
            System.out.println("colony is full");
            return;
        }
        colony.getColonists().putIfAbsent(familyId, new ArrayList<>());
        if (this.isFamilyFull(familyId)){
            System.out.println("family is full");
            return;
        }
        Colonist colonist = null;
        switch (className){
            case "GeneralPractitioner":
                colonist = new GeneralPractitioner(colonistId, familyId, talent, age, sign);
                colony.getColonists().get(familyId).add(colonist);
                break;
            case "Surgeon":
                colonist = new Surgeon(colonistId, familyId, talent, age, sign);
                colony.getColonists().get(familyId).add(colonist);
                break;
        }

    }
    public void insert ( String className, String colonistId, String familyId, int talent, int age){
        if (this.isColonyFull()){
            System.out.println("colony is full");
            return;
        }
        colony.getColonists().putIfAbsent(familyId, new ArrayList<>());
        if (this.isFamilyFull(familyId)){
            System.out.println("family is full");
            return;
        }
        Colonist colonist = null;
        switch (className){
            case "SoftwareEngineer":
                colonist = new SoftwareEngineer(colonistId, familyId, talent, age);
                colony.getColonists().get(familyId).add(colonist);
                break;
            case "HardwareEngineer":
                colonist = new HardwareEngineer(colonistId, familyId, talent, age);
                colony.getColonists().get(familyId).add(colonist);
                break;
            case "Soldier":
                colonist = new Soldier(colonistId, familyId, talent, age);
                colony.getColonists().get(familyId).add(colonist);
                break;
        }
    }
    public void remove (String modifier, String familyId, String colonistId){

    }

    public void grow(int years){

    }
    public void potential(){

    }
    public void capacity(){
        System.out.println();
    }
    public void family(){

    }


    public boolean isColonyFull() {
        return colony.getMaxFamilyCount() <= colony.getColonists().size();
    }
    public boolean isFamilyFull(String familyId) {
        return colony.getMaxFamilyCapacity() <= colony.getColonistsByFamilyId(familyId).size();
    }

}
