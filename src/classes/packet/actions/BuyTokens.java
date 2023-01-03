package classes.packet.actions;

import classes.packet.otherClasses.Output;
import classes.packet.otherClasses.Site;
import classes.fileio.ActionInput;

public final class BuyTokens implements Action {
    private String type;
    private String feature;
    private int count;
    private Site site;

    public BuyTokens(final ActionInput input, final Site site) {
        type = input.getType();
        feature = input.getFeature();
        count = input.getCount();
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

    public int getCount() {
        return count;
    }

    public void setCount(final int count) {
        this.count = count;
    }


    @Override
    public Output accept(final ActionVisitor visitor) {
        return visitor.visit(this, site);
    }

    @Override
    public String toString() {
        return "BuyTokens{"
                + "feature='" + feature + '\''
                + ", count=" + count
                + '}';
    }
}
