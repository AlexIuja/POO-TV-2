package classes.packet;


import java.util.ArrayList;

public final class Output {
    private String error;
    private ArrayList<Movie> currentMoviesList = new ArrayList<>();
    private User currentUser;

    public Output() {
        error = null;
        currentUser = null;
    }

    public String getError() {
        return error;
    }

    public void setError(final String error) {
        this.error = error;
    }

    public ArrayList<Movie> getCurrentMoviesList() {
        return currentMoviesList;
    }

    public void setCurrentMoviesList(final ArrayList<Movie> currentMoviesList) {
        this.currentMoviesList = currentMoviesList;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(final User currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public String toString() {
        return "Output{"
                + "error='" + error + '\''
                + ", currentMoviesList=" + currentMoviesList
                + ", currentUser=" + currentUser
                + '}';
    }
}
