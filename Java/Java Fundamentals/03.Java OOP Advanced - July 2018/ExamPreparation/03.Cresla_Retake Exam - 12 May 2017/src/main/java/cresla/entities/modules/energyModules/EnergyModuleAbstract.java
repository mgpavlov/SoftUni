package cresla.entities.modules.energyModules;

import cresla.entities.modules.AbstractModule;
import cresla.interfaces.EnergyModule;


public abstract class EnergyModuleAbstract extends AbstractModule implements EnergyModule {
    /*private int id;*/
    private int energyOutput ;

    protected EnergyModuleAbstract(/*int id, */int energyOutput) {
        /*this.id = id;*/
        super();
        this.energyOutput = energyOutput;
    }

    /*@Override
    public int getId() {
        return this.id;
    }*/

    @Override
    public int getEnergyOutput() {
        return this.energyOutput;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s Module - %d", this.getClass().getSimpleName(), this.getId())).append(System.lineSeparator());
        sb.append(String.format("Energy Output: %d", this.getEnergyOutput()));

        return sb.toString();
    }
}
