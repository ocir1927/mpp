package services;

import domain.Operator;

/**
 * Created by Costi on 01.04.2017.
 */
public interface IOperatoriServices {
    Operator login(String username, String password) throws ClientException;
}
