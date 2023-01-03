package classes.packet.otherClasses;

public class Rating {
    private int rating;
    private User user;

    public Rating(int rating, User user) {
        this.rating = rating;
        this.user = user;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
