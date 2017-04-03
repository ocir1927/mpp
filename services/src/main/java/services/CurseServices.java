package services;

import domain.Cursa;
import observer.Observable;
import observer.Observer;
import repository.CurseRepository;
import repository.JdbcUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Costi on 18.03.2017.
 */
public class CurseServices implements Observable<Cursa> {
    private CurseRepository curseRepository;
    List<Observer<Cursa>> observers=new ArrayList<>();

    public CurseServices() {
        curseRepository=new CurseRepository(JdbcUtils.loadProps());
    }

    public ArrayList<Cursa> getAll(){
        return (ArrayList<Cursa>) curseRepository.findAll();
    }

    public void updateCursa(int id, Cursa c){
        curseRepository.update(id,c);
        notifyObservers();
    }

    @Override
    public void addObserver(Observer<Cursa> o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer<Cursa> o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        observers.stream().forEach(x->x.update(this));
    }
}
