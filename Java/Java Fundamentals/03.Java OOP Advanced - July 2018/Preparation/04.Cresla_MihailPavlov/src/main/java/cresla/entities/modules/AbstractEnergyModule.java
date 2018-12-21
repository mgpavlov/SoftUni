package cresla.entities.modules;

import cresla.entities.AbstractIdentifiable;
import cresla.interfaces.EnergyModule;

public abstract class AbstractEnergyModule extends AbstractIdentifiable implements EnergyModule {
private int energyOutput;

    protected AbstractEnergyModule(int energyOutput) {
        super();
        this.energyOutput = energyOutput;
    }

    @Override
    public int getEnergyOutput() {
        return this.energyOutput;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName()).append(" Module - ").append(this.getId()).append(System.lineSeparator());
        sb.append("Energy Output: ").append(this.getEnergyOutput());
        return sb.toString();
    }
}
