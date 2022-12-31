package classes.packet.actions;

import classes.packet.Output;
import classes.packet.Site;
import classes.fileio.ActionInput;

public final class Search implements Action {
    private String type;
    private String feature;
    private String startsWith;
    private Site site;

    public Search(final ActionInput input, final Site site) {
        type = input.getType();
        feature = input.getFeature();
        startsWith = input.getStartsWith();
        this.site = site;
    }


    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(final String feature) {
        this.feature = feature;
    }

    public String getStartsWith() {
        return startsWith;
    }

    public void setStartsWith(final String startsWith) {
        this.startsWith = startsWith;
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
        return "Search{"
                + "feature='" + feature + '\''
                + ", startsWith='" + startsWith + '\''
                + '}';
    }
}
