package observer;

public interface Observable<E> {
    /**
     * Register an utils.
     * @param o the utils
     */
    void addObserver(Observer<E> o);
    /**
     * Unregister an utils.
     * @param o the utils
     */
    void removeObserver(Observer<E> o);
    
    
    public void notifyObservers();
} 