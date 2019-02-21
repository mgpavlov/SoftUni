package opg.softuni.fdmc.repositories;

public interface Repository<Id, Entity> {
    Entity findById(Id id);

//    List<Entity> findAll();

    Entity save(Entity entity);

    Entity delete(Entity entity);
}
