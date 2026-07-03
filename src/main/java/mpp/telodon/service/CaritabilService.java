package mpp.telodon.service;

import mpp.telodon.model.Caritabil;
import mpp.telodon.repository.IRepository;

public class CaritabilService extends BaseService<Integer, Caritabil>{

    public CaritabilService(IRepository<Integer, Caritabil> repo) {
        super(repo);
    }

    public void addCaritabil(String denumire)
    {
        Caritabil c = new Caritabil(generateId(), denumire, 0);
        add(c);
    }

}
