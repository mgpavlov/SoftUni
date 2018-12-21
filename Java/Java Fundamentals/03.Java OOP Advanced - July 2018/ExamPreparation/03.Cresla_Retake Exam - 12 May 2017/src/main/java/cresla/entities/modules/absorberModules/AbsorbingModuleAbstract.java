package cresla.entities.modules.absorberModules;

import cresla.entities.modules.AbstractModule;
import cresla.interfaces.AbsorbingModule;

public abstract class AbsorbingModuleAbstract extends AbstractModule implements AbsorbingModule {
    /*private int id;*/
    private int heatAbsorbing;

    protected AbsorbingModuleAbstract(/*int id, */int heatAbsorbing) {
        /*this.id = id;*/
        super();
        this.heatAbsorbing = heatAbsorbing;
    }

    @Override
    public int getHeatAbsorbing() {
        return this.heatAbsorbing;
    }

/*    @Override
    public int getId() {
        return this.id;
    }*/

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s Module - %d", this.getClass().getSimpleName(), this.getId())).append(System.lineSeparator());
        sb.append(String.format("Heat Absorbing: %d", this.getHeatAbsorbing()));

        return sb.toString();
    }
}
