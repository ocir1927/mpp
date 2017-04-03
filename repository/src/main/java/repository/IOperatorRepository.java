package repository;

/**
 * Created by Costi on 21.03.2017.
 */
public interface IOperatorRepository<ID,T> extends IRepository<ID,T> {
    T findByUsername(String username);
}
