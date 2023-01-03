package classes.packet.actions;

import classes.packet.otherClasses.Output;
import classes.packet.otherClasses.Site;

public final class LastRecom implements Action {
    private Site site;

    public LastRecom(final Site site) {
        this.site = site;
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

}
