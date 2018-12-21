package cresla.entities.reactors;

import cresla.entities.containers.ModuleContainer;
import cresla.interfaces.Container;

import java.lang.reflect.InvocationTargetException;

public class CryoReactor extends ReactorAbstract{
    private int cryoProductionIndex;

    public CryoReactor(/*int id, */Container moduleContainer, int additionalParameter) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        super(/*id, */moduleContainer);
        this.cryoProductionIndex = additionalParameter;
    }

    @Override
    public long getTotalEnergyOutput() {
        long totalEnergyOutput = super.getTotalEnergyOutput()*this.cryoProductionIndex;
        if (totalEnergyOutput > super.getTotalHeatAbsorbing()){
            return 0;
        }
        return totalEnergyOutput;
    }
}
