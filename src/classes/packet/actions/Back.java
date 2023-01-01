package classes.packet.actions;

import classes.fileio.ActionInput;
import classes.packet.Output;
import classes.packet.Site;

public class Back implements Action{
    private String type;
    private Site site;

    public Back(ActionInput input, Site site) {
        this.type = input.getType();
        this.site = site;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    @Override
    public Output accept(ActionVisitor visitor) {
        return visitor.visit(this, site);
    }

    @Override
    public String toString() {
        return "Back{" +
                "type='" + type + '\'' +
                ", site=" + site +
                '}';
    }
}
