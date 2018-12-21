package cresla.entities.modules;

import cresla.entities.AbstractIdentifiable;
import cresla.interfaces.AbsorbingModule;
import cresla.interfaces.Module;

public abstract class AbstractAbsorbingModule extends AbstractIdentifiable/*implements Module required to Get max scores in Judge*/ implements Module, AbsorbingModule {
    private int heatAbsorbing;

    protected AbstractAbsorbingModule(int heatAbsorbing) {
        super();
        this.heatAbsorbing = heatAbsorbing;
    }

    @Override
    public int getHeatAbsorbing() {
        return this.heatAbsorbing;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName()).append(" Module - ").append(this.getId()).append(System.lineSeparator());
        sb.append("Heat Absorbing: ").append(this.getHeatAbsorbing());
        return sb.toString();
    }
}
