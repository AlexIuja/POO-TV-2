package classes.packet.actions;

import classes.packet.Output;
import classes.packet.Site;
import classes.fileio.ActionInput;

public final class Rate implements Action {
    private String type;
    private String feature;
    private String objectType;
    private String movie;
    private int rate;

    private Site site;

    public Rate(final ActionInput input, final Site site) {
        type = input.getType();
        feature = input.getFeature();
        objectType = input.getObjectType();
        movie = input.getMovie();
        rate = input.getRate();
        this.site = site;
    }


    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(final String objectType) {
        this.objectType = objectType;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(final String movie) {
        this.movie = movie;
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

    public int getRate() {
        return rate;
    }

    public void setRate(final int rate) {
        this.rate = rate;
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
        return "Rate{"
                + "feature='" + feature + '\''
                + ", movie='" + movie + '\''
                + ", rate=" + rate
                + '}';
    }
}
