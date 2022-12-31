package classes.packet.actions;

import classes.packet.Output;
import classes.packet.Site;
import classes.fileio.ActionInput;

public final class Like implements Action {
    private String type;
    private String feature;
    private String objectType;
    private String movie;
    private Site site;

    public Like(final ActionInput input, final Site site) {
        type = input.getType();
        feature = input.getFeature();
        objectType = input.getObjectType();
        movie = input.getMovie();
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


    @Override
    public Output accept(final ActionVisitor visitor) {
        return visitor.visit(this, site);
    }

    @Override
    public String toString() {
        return "Like{"
                + "feature='" + feature + '\''
                + ", movie='" + movie + '\''
                + '}';
    }
}
