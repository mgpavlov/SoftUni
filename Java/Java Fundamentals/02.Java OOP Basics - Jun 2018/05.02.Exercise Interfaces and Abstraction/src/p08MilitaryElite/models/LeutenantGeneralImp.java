package p08MilitaryElite.models;


import p08MilitaryElite.interfaces.LeutenantGeneral;
import p08MilitaryElite.interfaces.Private;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class LeutenantGeneralImp extends PrivateImpl implements LeutenantGeneral {
    private Collection<Private> privates;

    public LeutenantGeneralImp(int id, String name, String lastName, double salary) {
        super(id, name, lastName, salary);
        this.privates = new ArrayList<>();
    }

    @Override
    public Collection<Private> getPrivates() {
        return this.privates;
    }

    @Override
    public void addPrivate(Private privateSoldier) {
        this.privates.add(privateSoldier);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(super.toString());
        builder.append("\nPrivates:\n");

        this.getPrivates().stream().sorted((a,b)-> Integer.compare(b.getId(), a.getId())).forEach(p->{
            builder.append(String.format("  %s\n", p.toString()));
        });

        return builder.toString().trim();
    }
}
