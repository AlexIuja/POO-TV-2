package classes.packet.otherClasses;

import java.util.ArrayList;

public interface SubjectInterface {
    public void addUser(ObserverInterface o);
    public void delUser(ObserverInterface o);
    public void notifyUpdate(DatabaseAux databaseAux);
}
