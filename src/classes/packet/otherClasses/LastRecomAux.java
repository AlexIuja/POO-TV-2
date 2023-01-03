package classes.packet.otherClasses;

public final class LastRecomAux {
    private String genre;
    private int noLikesForGenre;

    public LastRecomAux(final String genre, final int noLikesForGenre) {
        this.genre = genre;
        this.noLikesForGenre = noLikesForGenre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(final String genre) {
        this.genre = genre;
    }

    public int getNoLikesForGenre() {
        return noLikesForGenre;
    }

    public void setNoLikesForGenre(final int noLikesForGenre) {
        this.noLikesForGenre = noLikesForGenre;
    }

    @Override
    public String toString() {
        return "LastRecomAux{"
                + "genre='" + genre + '\''
                + ", noLikesForGenre=" + noLikesForGenre
                + '}';
    }
}
