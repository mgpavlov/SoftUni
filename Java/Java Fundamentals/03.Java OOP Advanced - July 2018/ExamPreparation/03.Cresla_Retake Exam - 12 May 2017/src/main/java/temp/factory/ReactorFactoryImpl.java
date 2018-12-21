/*
package temp.factory;

import cresla.entities.reactors.CryoReactor;
import cresla.entities.reactors.HeatReactor;
import cresla.interfaces.Container;
import cresla.interfaces.Reactor;
import temp.interfaces.ReactorFactory;

import java.lang.reflect.InvocationTargetException;

public class ReactorFactoryImpl implements ReactorFactory {
    private Reactor identifiable;

    public ReactorFactoryImpl() {
    }

    @Override
    public Reactor create(String reactorType, int id, Container moduleContainer, int additionalParam) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        switch (reactorType) {
            case "Cryo":
                this.identifiable = new CryoReactor(*/
/*id, *//*
moduleContainer, additionalParam);
                break;
            case "Heat":
                this.identifiable = new HeatReactor(*/
/*id, *//*
moduleContainer, additionalParam);
                break;
        }
        return this.identifiable;
    }


}
*/
