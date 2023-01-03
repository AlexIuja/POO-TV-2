package classes.packet.otherClasses;

public final class DatabaseAux {
    private Movie movie;
    private String command;

    public DatabaseAux(final Movie movie, final String command) {
        this.movie = movie;
        this.command = command;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(final Movie movie) {
        this.movie = movie;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(final String command) {
        this.command = command;
    }
}
