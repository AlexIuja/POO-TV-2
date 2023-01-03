package classes.packet.otherClasses;

import classes.fileio.CredentialsInput;
import classes.fileio.UserInput;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public final class User implements ObserverInterface {

    private CredentialsInput credentials;
    private int tokensCount;
    private int numFreePremiumMovies;
    private ArrayList<Movie> purchasedMovies = new ArrayList<>();
    private ArrayList<Movie> watchedMovies = new ArrayList<>();
    private ArrayList<Movie> likedMovies = new ArrayList<>();
    private ArrayList<Movie> ratedMovies = new ArrayList<>();
    private ArrayList<Notification> notifications = new ArrayList<>();
    @JsonIgnore
    private ArrayList<Movie> allowedMovies = new ArrayList<>();
    @JsonIgnore
    private ArrayList<String> subscribedGenres = new ArrayList<>();
//    @JsonIgnore
//    private ArrayList<String>

    public User() {
        tokensCount = 0;
        numFreePremiumMovies = Site.NUMBER_FREE_PREMIUM_MOVIES;
    }


    public User(final UserInput input) {
        credentials = input.getCredentials();
        tokensCount = 0;
        numFreePremiumMovies = Site.NUMBER_FREE_PREMIUM_MOVIES;
    }

    public User(final User input) {
        this.credentials = input.getCredentials();
        tokensCount = 0;
        numFreePremiumMovies = Site.NUMBER_FREE_PREMIUM_MOVIES;
    }

    public CredentialsInput getCredentials() {
        return credentials;
    }

    public void setCredentials(final CredentialsInput credentials) {
        this.credentials = credentials;
    }

    public int getTokensCount() {
        return tokensCount;
    }

    public void setTokensCount(final int tokensCount) {
        this.tokensCount = tokensCount;
    }

    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    public void setNumFreePremiumMovies(final int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }

    public ArrayList<Movie> getPurchasedMovies() {
        return purchasedMovies;
    }

    public void setPurchasedMovies(final ArrayList<Movie> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    public ArrayList<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    public void setWatchedMovies(final ArrayList<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public ArrayList<Movie> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(final ArrayList<Movie> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public ArrayList<Movie> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(final ArrayList<Movie> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    public ArrayList<Movie> getAllowedMovies() {
        return allowedMovies;
    }

    public void setAllowedMovies(final ArrayList<Movie> allowedMovies) {
        this.allowedMovies = allowedMovies;
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(final ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    public ArrayList<String> getSubscribedGenres() {
        return subscribedGenres;
    }

    public void setSubscribedGenres(final ArrayList<String> subscribedGenres) {
        this.subscribedGenres = subscribedGenres;
    }

    @Override
    public String toString() {
        return "User{"
                + "credentials=" + credentials
                + ", tokensCount=" + tokensCount
                + ", numFreePremiumMovies=" + numFreePremiumMovies
                + ", purchasedMovies=" + purchasedMovies
                + ", watchedMovies=" + watchedMovies
                + ", likedMovies=" + likedMovies
                + ", ratedMovies=" + ratedMovies
                + ", notifications=" + notifications
                + '}';
    }

    @Override
    public void update(final DatabaseAux databaseAux) {
        if (!databaseAux.getMovie().getCountriesBanned().contains(credentials.getCountry())) {
            if (databaseAux.getCommand().equals("ADD")) {
                for (int i = 0; i < databaseAux.getMovie().getGenres().size(); i++) {
                    if (subscribedGenres.contains(databaseAux.getMovie().getGenres().get(i))) {
                        Notification notification = new Notification();
                        notification.setMessage(databaseAux.getCommand());
                        notification.setMovieName(databaseAux.getMovie().getName());
                        notifications.add(notification);
                        return;
                    }
                }
            } else if (databaseAux.getCommand().equals("DELETE")) {
                for (int i = 0; i < databaseAux.getMovie().getGenres().size(); i++) {
                    if (subscribedGenres.contains(databaseAux.getMovie().getGenres().get(i))) {
                        Notification notification = new Notification();
                        notification.setMessage(databaseAux.getCommand());
                        notification.setMovieName(databaseAux.getMovie().getName());
                        notifications.add(notification);
                        return;
                    }
                }
            }

        }
    }
}
