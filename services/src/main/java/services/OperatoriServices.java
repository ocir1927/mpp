package services;


import domain.Operator;
import repository.JdbcUtils;
import repository.OperatoriRepository;

/**
 * Created by Costi on 20.03.2017.
 */
public class OperatoriServices implements IOperatoriServices {
    private OperatoriRepository operatoriRepository;

    public OperatoriServices() {
        this.operatoriRepository = new OperatoriRepository(JdbcUtils.loadProps());
    }

    public Operator login(String username, String password) {
        return operatoriRepository.findBy(username,password);

    }
}
