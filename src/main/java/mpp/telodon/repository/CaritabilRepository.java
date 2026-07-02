package mpp.telodon.repository;

import mpp.telodon.model.Caritabil;

public class CaritabilRepository extends AbstractRepository<Integer, Caritabil>{

    public CaritabilRepository(Validator valid) {
        super(valid);
        save(new Caritabil(1, "Caritate 1", 0));
        save(new Caritabil(2, "Caritate 2", 0));
        save(new Caritabil(3, "Caritate 3", 0));
        save(new Caritabil(4, "Caritate 4", 0));

    }
}
