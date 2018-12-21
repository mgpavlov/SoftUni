package temp.interfaces;

public interface ModuleFactory {
    Module create(String type, int id, int additionalParam);
}
