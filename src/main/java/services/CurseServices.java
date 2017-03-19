package services;

import domain.Cursa;
import repository.CurseRepository;
import repository.JdbcUtils;

import java.util.ArrayList;

/**
 * Created by Costi on 18.03.2017.
 */
public class CurseServices {
    private CurseRepository curseRepository;

    public CurseServices() {
        curseRepository=new CurseRepository(JdbcUtils.loadProps());
    }

    public ArrayList<Cursa> getAll(){
        return (ArrayList<Cursa>) curseRepository.findAll();
    }
}
