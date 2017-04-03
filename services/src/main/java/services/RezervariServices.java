package services;

import domain.Rezervare;
import repository.JdbcUtils;
import repository.RezervariRepository;
import observer.Observable;
import observer.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Costi on 18.03.2017.
 */
public class RezervariServices implements Observable<Rezervare> {
    List<Observer<Rezervare>> observers=new ArrayList<>();
    private RezervariRepository repo;

    public RezervariServices() {
        this.repo = new RezervariRepository(JdbcUtils.loadProps());
    }

    public void addRezervare(Rezervare rezervare){
        repo.save(rezervare);
        notifyObservers();
    }

    public void deleteRezervare(Rezervare rezervare){
        repo.delete(rezervare.getId());
        notifyObservers();
    }

    public ArrayList<Rezervare> getAllRezervari(){
        return (ArrayList<Rezervare>) repo.findAll();
    }

    public ArrayList<Rezervare> getAllByCursa(int cursa){
        return (ArrayList<Rezervare>) repo.getAllByCursa(cursa);
    }

    @Override
    public void addObserver(Observer<Rezervare> o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer<Rezervare> o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        observers.stream().forEach(x->x.update(this));
    }
}
