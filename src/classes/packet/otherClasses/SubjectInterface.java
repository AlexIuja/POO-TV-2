package classes.packet.otherClasses;


public interface SubjectInterface {
    /**
     *
     * @param o
     */
    void addUser(ObserverInterface o);

    /**
     *
     * @param o
     */
    void delUser(ObserverInterface o);

    /**
     *
     * @param databaseAux
     */
    void notifyUpdate(DatabaseAux databaseAux);
}
