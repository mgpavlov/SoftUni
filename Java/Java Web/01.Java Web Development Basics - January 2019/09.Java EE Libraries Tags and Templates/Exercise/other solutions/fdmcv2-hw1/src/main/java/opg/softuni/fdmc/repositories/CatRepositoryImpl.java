package opg.softuni.fdmc.repositories;

import opg.softuni.fdmc.domain.dto.CatViewDto;
import opg.softuni.fdmc.domain.entities.Cat;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class CatRepositoryImpl extends GenericRepository implements CatRepository {

    @Inject
    public CatRepositoryImpl(EntityManager em) {
        super(em);
    }



    @Override
    public Cat findById(String id) {
        return (Cat) this.getEntityByStringValue("id", id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<CatViewDto> findAll() {
        return (List<CatViewDto>) execute(entityManager ->
                entityManager.createQuery("select new opg.softuni.fdmc.domain.dto.CatViewDto(c) " +
                        "from Cat c", CatViewDto.class)
                        .getResultList());
    }

    @Override
    public Cat save(Cat cat) {
        return (Cat) execute(entityManager ->{
            Cat e = entityManager.merge(cat);
            entityManager.flush();
            return e;
        });

    }

    @Override
    public Cat delete(Cat cat) {
        return (Cat) execute(entityManager ->{
            entityManager.remove(cat);
            entityManager.flush();
            return cat;
        });
    }



}
