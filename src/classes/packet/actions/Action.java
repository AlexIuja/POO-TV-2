package classes.packet.actions;

import classes.packet.otherClasses.Output;

public interface Action {
    /**
     * interfata necesara pentru Visitor Pattern
     * @param visitor
     * @return
     */
    Output accept(ActionVisitor visitor);
}
