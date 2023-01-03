package classes.packet.otherClasses;

public class DatabaseAux {
    private Movie movie;
    private String command;

    public DatabaseAux(Movie movie, String command) {
        this.movie = movie;
        this.command = command;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
