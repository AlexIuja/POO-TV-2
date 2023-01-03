package classes.packet.actions;

import classes.packet.otherClasses.Output;
import classes.packet.otherClasses.Site;

public class LastRecom implements Action{
    private Site site;

    public LastRecom(Site site) {
        this.site = site;
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

}
