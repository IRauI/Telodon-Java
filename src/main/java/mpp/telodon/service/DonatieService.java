package mpp.telodon.service;

import mpp.telodon.model.Caritabil;
import mpp.telodon.model.Donatie;
import mpp.telodon.repository.IRepository;

public class DonatieService extends BaseService<Integer, Donatie> {
    private final IRepository<Integer, Caritabil> caritabil_repo;

    public DonatieService(IRepository<Integer, Donatie> repo,
                          IRepository<Integer, Caritabil> caritabil_repo) {
        super(repo);
        this.caritabil_repo = caritabil_repo;
    }

    public void registerDonatie(int donatorId, int caritabilId, double suma) {
        if (suma <= 0) {
            throw new ServiceException("Suma trebuie sa fie pozitiva!");
        }

        Caritabil caritabil = caritabil_repo.findOne(caritabilId);
        if (caritabil == null) {
            throw new ServiceException("Caritabilul cu id " + caritabilId + " nu exista!");
        }

        Donatie donatie = new Donatie(generateId(), donatorId, caritabilId, suma);
        add(donatie);

        caritabil.setTotal(caritabil.getTotal() + suma);
        caritabil_repo.update(caritabilId, caritabil);
    }

    @Override
    public void remove(Integer id) {
        Donatie donatie = findOne(id);
        if (donatie == null) {
            throw new ServiceException("Donatia cu id " + id + " nu exista!");
        }

        Caritabil caritabil = caritabil_repo.findOne(donatie.getCaritabil_id());
        if (caritabil != null) {
            caritabil.setTotal(caritabil.getTotal() - donatie.getSuma());
            caritabil_repo.update(caritabil.getId(), caritabil);
        }

        super.remove(id);
    }
}