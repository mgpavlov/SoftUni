package cresla.entities.reactors;

import cresla.entities.containers.ModuleContainer;
import cresla.interfaces.AbsorbingModule;
import cresla.interfaces.Container;

public class HeatReactor extends AbstractReactor {
    private int heatReductionIndex;
    public HeatReactor(Container moduleContainer, int additionalParameter) {
        super(moduleContainer);
        this.heatReductionIndex = additionalParameter;
    }

    @Override
    public long getTotalHeatAbsorbing() {
        return super.getTotalHeatAbsorbing() + this.heatReductionIndex;
    }
}
