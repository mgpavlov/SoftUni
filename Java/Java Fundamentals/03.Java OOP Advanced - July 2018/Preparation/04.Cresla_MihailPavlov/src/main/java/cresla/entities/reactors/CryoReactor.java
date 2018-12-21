package cresla.entities.reactors;

import cresla.interfaces.Container;

public class CryoReactor extends AbstractReactor {
    private int cryoProductionIndex;

    public CryoReactor(Container moduleContainer, int additionalParameter) {
        super(moduleContainer);
        this.cryoProductionIndex = additionalParameter;
    }

    /*@Override
    public long getTotalEnergyOutput() {
        return super.getTotalEnergyOutput() * this.cryoProductionIndex;
    }*/
    @Override
    protected long getEnergy() {
        return super.getEnergy() * this.cryoProductionIndex;
    }
}
