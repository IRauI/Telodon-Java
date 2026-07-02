package mpp.telodon.repository;

import mpp.telodon.model.Voluntar;

public class VoluntarRepository extends AbstractRepository<Integer, Voluntar>{

    public VoluntarRepository(Validator<Voluntar> valid) {
        super(valid);

        save(new Voluntar(1, "voluntar1", "voluntar1"));
    }
}
