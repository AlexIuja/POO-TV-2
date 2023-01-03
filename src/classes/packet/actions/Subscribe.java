package classes.packet.actions;

import classes.fileio.ActionInput;
import classes.packet.otherClasses.Output;
import classes.packet.otherClasses.Site;

public class Subscribe implements Action{
    private String type;
    private String subscribedGenre;
    private Site site;

    public Subscribe(ActionInput input, Site site) {
        type = input.getType();
        subscribedGenre = input.getSubscribedGenre();
        this.site = site;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubscribedGenre() {
        return subscribedGenre;
    }

    public void setSubscribedGenre(String subscribedGenre) {
        this.subscribedGenre = subscribedGenre;
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
        return "Subscribe{" +
                "type='" + type + '\'' +
                ", subscribedGenre='" + subscribedGenre + '\'' +
                ", site=" + site +
                '}';
    }
}
