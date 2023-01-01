package classes.fileio;


public final class ActionInput {

    private String type;
    private String page;
    private String feature;
    private CredentialsInput credentials;
    private FilterInput filters;
    private String startsWith;
    private String movie;
    private String objectType;
    private int count;
    private int rate;
    private String subscribedGenre;
    private MovieInput addedMovie;
    private String deletedMovie;

    public ActionInput() {
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getPage() {
        return page;
    }

    public void setPage(final String page) {
        this.page = page;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(final String feature) {
        this.feature = feature;
    }

    public CredentialsInput getCredentials() {
        return credentials;
    }

    public void setCredentials(final CredentialsInput credentials) {
        this.credentials = credentials;
    }

    public FilterInput getFilters() {
        return filters;
    }

    public void setFilters(final FilterInput filters) {
        this.filters = filters;
    }

    public String getStartsWith() {
        return startsWith;
    }

    public void setStartsWith(final String startsWith) {
        this.startsWith = startsWith;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(final String movie) {
        this.movie = movie;
    }

    public int getCount() {
        return count;
    }

    public void setCount(final int count) {
        this.count = count;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(final int rate) {
        this.rate = rate;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(final String objectType) {
        this.objectType = objectType;
    }

    public String getSubscribedGenre() {
        return subscribedGenre;
    }

    public void setSubscribedGenre(String subscribedGenre) {
        this.subscribedGenre = subscribedGenre;
    }

    public MovieInput getAddedMovie() {
        return addedMovie;
    }

    public void setAddedMovie(MovieInput addedMovie) {
        this.addedMovie = addedMovie;
    }

    public String getDeletedMovie() {
        return deletedMovie;
    }

    public void setDeletedMovie(String deletedMovie) {
        this.deletedMovie = deletedMovie;
    }

    @Override
    public String toString() {
        return "ActionInput{"
                + "type='" + type + '\''
                + ", page='" + page + '\''
                + ", feature='" + feature + '\''
                + ", credentials=" + credentials
                + ", filters=" + filters
                + ", startsWith='" + startsWith + '\''
                + ", movie='" + movie + '\''
                + ", objectType='" + objectType + '\''
                + ", count=" + count
                + ", rate=" + rate
                + '}';
    }
}
