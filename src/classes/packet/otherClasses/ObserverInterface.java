package classes.packet.otherClasses;

public interface ObserverInterface {
    /**
     *
     * @param databaseAux structura in care retinem numele filmului
     *                    si comanda care genereaza o notificare
     */
    void update(DatabaseAux databaseAux);
}
