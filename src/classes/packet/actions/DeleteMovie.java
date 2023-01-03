package classes.packet.actions;

import classes.fileio.ActionInput;
import classes.packet.otherClasses.Output;
import classes.packet.otherClasses.Site;

public class DeleteMovie implements Action{
    private String type;
    private String feature;
    private String deletedMovie;
    private Site site;

    public DeleteMovie(ActionInput input, Site site) {
        type = input.getType();
        feature = input.getFeature();
        deletedMovie = input.getDeletedMovie();
        this.site = site;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getDeletedMovie() {
        return deletedMovie;
    }

    public void setDeletedMovie(String deletedMovie) {
        this.deletedMovie = deletedMovie;
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
}
