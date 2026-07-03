package mpp.telodon.service;

import mpp.telodon.repository.HasId;
import mpp.telodon.repository.IRepository;

public abstract class BaseService<ID, T extends HasId<ID>>{
    protected final IRepository<ID,T> repo;

    protected BaseService(IRepository<ID, T> repo) {
        this.repo = repo;
    }

    public Iterable<T> getAll()
    {
        return repo.findAll();
    }

    public T findOne(ID id){
        return repo.findOne(id);
    }

    public void remove(ID id){
        repo.delete(id);
    }

    protected void add(T entity){
        repo.save(entity);
    }

    protected void update(ID id, T entity){
        repo.update(id,entity);
    }

    //Foarte iffy castu la Integer
    protected int generateId() {
        int maxId = 0;
        for (T entity : repo.findAll()) {
            if ((Integer) entity.getId() > maxId) {
                maxId = (Integer) entity.getId();
            }
        }
        return maxId + 1;
    }
}
