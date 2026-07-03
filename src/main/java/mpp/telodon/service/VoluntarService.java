package mpp.telodon.service;

import mpp.telodon.model.Voluntar;
import mpp.telodon.repository.IRepository;

public class VoluntarService extends BaseService<Integer, Voluntar>{

    public VoluntarService(IRepository<Integer, Voluntar> repo) {
        super(repo);
    }

    public Voluntar login(String username, String password){
        Iterable<Voluntar> voluntari =  repo.findAll();
        Voluntar curent = null;
        for( Voluntar v : voluntari) {
            if(v.getUsername().equals(username)) {
                curent = v;
            }
        }

        if(curent == null){
            throw new ServiceException("Numele de utilizator nu a fost gasit!");
        }
        if(!curent.getPassword() .equals(password)){
            throw new ServiceException("Parola incorecta!");
        }

        return curent;
    }

    public void addVoluntar(String username, String password)
    {
        Voluntar v = new Voluntar(generateId(), username, password);
        add(v);
    }
}
