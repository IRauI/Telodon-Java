package mpp.telodon.service;

import mpp.telodon.model.Donator;
import mpp.telodon.repository.IRepository;

public class DonatorService extends BaseService<Integer, Donator>{

    public DonatorService(IRepository<Integer, Donator> repo) {
        super(repo);
    }

    public void addDonator(String nume, String prenume, String adresa, String telefon)
    {
        Donator d = new Donator(generateId(), nume, prenume, adresa, telefon);
        add(d);
    }
}
