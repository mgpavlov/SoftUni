package p08MilitaryElite2;

public class Mission {
    private String codeName;
    private String state;

    Mission(String codeName, String state) {
        this.setCodeName(codeName);
        this.setState(state);
    }
    public void CompleteMission(){

    }

    private void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    private void setState(String state) {
        if (state.equals("inProgress")||state.equals("Finished")){
            this.state = state;
        }else {
            throw new IllegalArgumentException();
        }
    }

    public void completeMission() {
        this.state = "Finished";
    }


    @Override
    public String toString() {
        return String.format("  Code Name: %s State: %s", this.codeName, this.state);
    }

}
