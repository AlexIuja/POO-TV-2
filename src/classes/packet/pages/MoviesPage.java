package classes.packet.pages;

public final class MoviesPage extends SitePage {
    private static MoviesPage instance = new MoviesPage();
    private MoviesPage() {
        super("movies");
    }
    public static MoviesPage getInstance() {
        return instance;
    }
}
