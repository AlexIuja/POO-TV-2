package classes.packet.otherClasses;


public interface SubjectInterface {
    /**
     *
     * @param o userul de adaugat in lista site-ului
     */
    void addUser(ObserverInterface o);

    /**
     *
     * @param o userul care trebuie sters din lista site-ului
     */
    void delUser(ObserverInterface o);

    /**
     *
     * @param databaseAux structura in care retinem numele filmului
     *                    si comanda care genereaza o notificare
     */
    void notifyUpdate(DatabaseAux databaseAux);
}
