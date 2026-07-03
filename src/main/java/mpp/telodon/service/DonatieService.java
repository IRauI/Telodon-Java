package mpp.telodon.service;

import mpp.telodon.model.Donatie;
import mpp.telodon.repository.IRepository;

public class DonatieService extends BaseService<Integer, Donatie>{

    public DonatieService(IRepository<Integer, Donatie> repo) {
        super(repo);
    }

    @Override
    public void add(Donatie entity)
    {

    }

}
