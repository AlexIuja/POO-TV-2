package classes.packet.pages;

import classes.packet.otherClasses.Movie;

public final class SeeDetailsPage extends SitePage {
    private static SeeDetailsPage instance = new SeeDetailsPage();
    private Movie movie;
    private SeeDetailsPage() {
        super("see details");
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(final Movie movie) {
        this.movie = movie;
    }
    public static SeeDetailsPage getInstance() {
        return instance;
    }
}
