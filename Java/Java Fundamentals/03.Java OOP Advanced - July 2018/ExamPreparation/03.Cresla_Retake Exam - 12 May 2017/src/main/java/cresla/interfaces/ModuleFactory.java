package cresla.interfaces;

import cresla.interfaces.Module;

public interface ModuleFactory {
    Module create(String type, int id, int additionalParam);
}
