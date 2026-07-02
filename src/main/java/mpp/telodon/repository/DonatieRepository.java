package mpp.telodon.repository;

import mpp.telodon.model.Donatie;

public class DonatieRepository extends AbstractRepository{

    public DonatieRepository(Validator valid) {
        super(valid);
        save(new Donatie(1, 1, 1, 100));
        save(new Donatie(2, 1, 2, 200));
        save(new Donatie(3, 3, 3, 300));
        save(new Donatie(4, 4, 4, 400));

    }
}
