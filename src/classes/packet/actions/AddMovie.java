package classes.packet.actions;

import classes.fileio.ActionInput;
import classes.fileio.MovieInput;
import classes.packet.otherClasses.Output;
import classes.packet.otherClasses.Site;

public final class AddMovie implements Action {
    private String type;
    private String feature;
    private MovieInput addedMovie;
    private Site site;

    public AddMovie(final ActionInput input, final Site site) {
        type = input.getType();
        feature = input.getFeature();
        addedMovie = input.getAddedMovie();
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

    public MovieInput getAddedMovie() {
        return addedMovie;
    }

    public void setAddedMovie(final MovieInput addedMovie) {
        this.addedMovie = addedMovie;
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
        return "AddMovie{"
                + "type='" + type + '\''
                + ", feature='" + feature + '\''
                + ", addedMovie=" + addedMovie
                + ", site=" + site
                + '}';
    }
}
