package classes.packet.actions;

import classes.packet.Output;
import classes.packet.Site;
import classes.fileio.ActionInput;

public final class ChangePage implements Action {
    private String page;
    private String type;
    private String movie;
    private Site site;

    public ChangePage(final ActionInput input, final Site site) {
        this.page = input.getPage();
        this.type = input.getType();
        if (input.getMovie() != null) {
            movie = input.getMovie();
        }
        this.site = site;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(final String movie) {
        this.movie = movie;
    }

    public String getPage() {
        return page;
    }

    public void setPage(final String page) {
        this.page = page;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }


    @Override
    public Output accept(final ActionVisitor visitor) {
        return visitor.visit(this, site);
    }

    @Override
    public String toString() {
        return "ChangePage{"
                + "page='" + page + '\''
                + ", type='" + type + '\''
                + ", movie='" + movie + '\''
                + '}';
    }
}
