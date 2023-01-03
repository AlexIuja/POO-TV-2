package classes.packet.otherClasses;

public final class Rating {
    private int rating;
    private User user;

    public Rating(final int rating, final User user) {
        this.rating = rating;
        this.user = user;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(final int rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }
}
