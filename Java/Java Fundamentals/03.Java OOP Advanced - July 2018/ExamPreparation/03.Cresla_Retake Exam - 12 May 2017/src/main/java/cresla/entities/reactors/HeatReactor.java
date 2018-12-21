package cresla.entities.reactors;

import cresla.entities.containers.ModuleContainer;
import cresla.interfaces.Container;

import java.lang.reflect.InvocationTargetException;

public class HeatReactor extends ReactorAbstract{
    private int heatReduction;
    public HeatReactor(/*int id, */Container moduleContainer, int additionalParameter) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        super(/*id, */moduleContainer);
        this.heatReduction = additionalParameter;
    }

    @Override
    public long getTotalHeatAbsorbing() {
        return super.getTotalHeatAbsorbing() + this.heatReduction;
    }

    @Override
    public long getTotalEnergyOutput() {
        if (super.getTotalEnergyOutput()>this.getTotalHeatAbsorbing()){
            return 0;
        }
        return super.getTotalEnergyOutput();
    }
}
