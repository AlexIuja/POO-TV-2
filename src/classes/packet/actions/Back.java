package classes.packet.actions;

import classes.fileio.ActionInput;
import classes.packet.otherClasses.Output;
import classes.packet.otherClasses.Site;

public final class Back implements Action {
    private String type;
    private Site site;

    public Back(final ActionInput input, final Site site) {
        this.type = input.getType();
        this.site = site;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(final Site site) {
        this.site = site;
    }

    @Override
    public Output accept(final ActionVisitor visitor) {
        return visitor.visit(this, site);
    }

    @Override
    public String toString() {
        return "Back{"
                + "type='" + type + '\''
                + ", site=" + site
                + '}';
    }
}
