package classes.packet.actions;

import classes.packet.otherClasses.Output;
import classes.packet.otherClasses.Site;
import classes.fileio.ActionInput;

public final class BuyPrem implements Action {
    private String type;
    private String feature;
    private Site site;

    public BuyPrem(final ActionInput input, final Site site) {
        type = input.getType();
        feature = input.getFeature();
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

    @Override
    public Output accept(final ActionVisitor visitor) {
        return visitor.visit(this, site);
    }

    @Override
    public String toString() {
        return "BuyPrem{"
                + "feature='" + feature + '\''
                + '}';
    }
}
