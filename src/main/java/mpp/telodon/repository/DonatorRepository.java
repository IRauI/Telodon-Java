package mpp.telodon.repository;

import mpp.telodon.model.Donator;

public class DonatorRepository extends AbstractRepository<Integer, Donator> {

    public DonatorRepository(Validator valid) {
        super(valid);

        save(new Donator(1, "Pop", "Sorin", "Albac 11", "0722123456"));
        save(new Donator(2, "Popescu", "Alex", "Albac 12", "0722123457"));
        save(new Donator(3, "Popica", "Adrian", "Albac 13", "0722123458"));
        save(new Donator(4, "Popa", "Mihai", "Albac 14", "0722123459"));
    }
}
