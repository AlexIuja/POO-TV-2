package classes.packet.actions;

import classes.fileio.ActionInput;
import classes.packet.otherClasses.Output;
import classes.packet.otherClasses.Site;

public final class DeleteMovie implements Action {
    private String type;
    private String feature;
    private String deletedMovie;
    private Site site;

    public DeleteMovie(final ActionInput input, final Site site) {
        type = input.getType();
        feature = input.getFeature();
        deletedMovie = input.getDeletedMovie();
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

    public String getDeletedMovie() {
        return deletedMovie;
    }

    public void setDeletedMovie(final String deletedMovie) {
        this.deletedMovie = deletedMovie;
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
