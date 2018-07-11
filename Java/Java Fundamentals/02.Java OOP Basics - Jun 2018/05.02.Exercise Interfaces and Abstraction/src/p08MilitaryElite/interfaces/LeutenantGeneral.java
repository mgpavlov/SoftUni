package p08MilitaryElite.interfaces;

import java.util.Collection;

public interface LeutenantGeneral extends Private {
    Collection<Private> getPrivates();

    void addPrivate(Private privateSoldier);
}
