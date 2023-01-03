package classes.packet.otherClasses;

import classes.fileio.MovieInput;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public final class Movie {
    private String name;
    private String year;
    private int duration;
    private ArrayList<String> genres = new ArrayList<>();
    private ArrayList<String> actors = new ArrayList<>();
    private ArrayList<String> countriesBanned = new ArrayList<>();
    private int numLikes;
    private double rating;
    private int numRatings;
    @JsonIgnore
    private ArrayList<Rating> allRatings = new ArrayList<>();

    public Movie(final MovieInput input) {
        name = input.getName();
        year = Integer.toString(input.getYear());
        duration = input.getDuration();
        genres.addAll(input.getGenres());
        actors.addAll(input.getActors());
        countriesBanned.addAll(input.getCountriesBanned());
        numLikes = 0;
        numRatings = 0;
    }

    public Movie(final Movie input) {
        name = input.getName();
        year = input.getYear();
        duration = input.getDuration();
        genres.addAll(input.getGenres());
        actors.addAll(input.getActors());
        countriesBanned.addAll(input.getCountriesBanned());
    }

    public Movie() {
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(final int duration) {
        this.duration = duration;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(final ArrayList<String> genres) {
        this.genres = genres;
    }

    public ArrayList<String> getActors() {
        return actors;
    }

    public void setActors(final ArrayList<String> actors) {
        this.actors = actors;
    }

    public ArrayList<String> getCountriesBanned() {
        return countriesBanned;
    }

    public void setCountriesBanned(final ArrayList<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(final int numLikes) {
        this.numLikes = numLikes;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(final double rating) {
        this.rating = rating;
    }

    public int getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(final int numRatings) {
        this.numRatings = numRatings;
    }

    public ArrayList<Rating> getAllRatings() {
        return allRatings;
    }

    public void setAllRatings(ArrayList<Rating> allRatings) {
        this.allRatings = allRatings;
    }

    @Override
    public String toString() {
        return "Movie{"
                + "name='" + name + '\''
                + ", year=" + year
                + ", duration=" + duration
                + ", genres=" + genres
                + ", actors=" + actors
                + ", countriesBanned=" + countriesBanned
                + ", numLikes=" + numLikes
                + ", rating=" + rating
                + ", numRatings=" + numRatings
                + '}';
    }
}
