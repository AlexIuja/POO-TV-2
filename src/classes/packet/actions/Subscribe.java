package classes.packet.actions;

import classes.fileio.ActionInput;
import classes.packet.otherClasses.Output;
import classes.packet.otherClasses.Site;

public final class Subscribe implements Action {
    private String type;
    private String subscribedGenre;
    private Site site;

    public Subscribe(final ActionInput input, final Site site) {
        type = input.getType();
        subscribedGenre = input.getSubscribedGenre();
        this.site = site;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getSubscribedGenre() {
        return subscribedGenre;
    }

    public void setSubscribedGenre(final String subscribedGenre) {
        this.subscribedGenre = subscribedGenre;
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
        return "Subscribe{"
                + "type='" + type + '\''
                + ", subscribedGenre='" + subscribedGenre + '\''
                + ", site=" + site
                + '}';
    }
}
