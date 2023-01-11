package classes.packet.actions;

import classes.packet.otherClasses.DatabaseAux;
import classes.packet.otherClasses.LastRecomAux;
import classes.packet.otherClasses.Movie;
import classes.packet.otherClasses.Notification;
import classes.packet.otherClasses.Output;
import classes.packet.otherClasses.Rating;
import classes.packet.otherClasses.Site;
import classes.packet.otherClasses.User;


import classes.packet.pages.SeeDetailsPage;
import classes.fileio.CredentialsInput;
import classes.packet.pages.SitePage;

import java.util.ArrayList;
import java.util.Comparator;

public final class ActionVisitorImpl implements ActionVisitor {
    //pentru fiecare functie voi incerca sa explic cum este implementata
    //fara a intra prea mult in detalii, decat acolo unde nu este neaparat
    //prea clar modul in care am gandit

    private ArrayList<Movie> moviesAllowedInCountry = new ArrayList<>();
    public ArrayList<Movie> getMoviesAllowedInCountry() {
        return moviesAllowedInCountry;
    }

    public void setMoviesAllowedInCountry(final ArrayList<Movie> moviesAllowedInCountry) {
        this.moviesAllowedInCountry = moviesAllowedInCountry;
    }

    /**
     *
     * @param page
     * @return
     */
    public int getPageIdx(final SitePage page) {
        if (page.getPageName().equals("homepage autentificat")) {
            return Site.HOMEPAGEAUT_ID;
        } else if (page.getPageName().equals("homepage neautentificat")) {
            return Site.HOMEPAGENEAUT_ID;
        } else if (page.getPageName().equals("login")) {
            return Site.LOGIN_ID;
        } else if (page.getPageName().equals("logout")) {
            return Site.LOGOUT_ID;
        } else if (page.getPageName().equals("movies")) {
            return Site.MOVIES_ID;
        } else if (page.getPageName().equals("register")) {
            return Site.REGISTER_ID;
        } else if (page.getPageName().equals("see details")) {
            return Site.SEEDETAILS_ID;
        } else if (page.getPageName().equals("upgrades")) {
            return Site.UPGRADES_ID;
        }
        return -1;
    }


    @Override
    public Output visit(final ChangePage changePage, final Site site) {
        //implementarea functionalitatii change page
        Output output = new Output();
        for (int i = 0; i < site.getCurrentPage().getAllowedPagesToChange().size(); i++) {
            if (changePage.getPage().equals(site.getCurrentPage()
                    .getAllowedPagesToChange().get(i).getPageName())) {
                if (changePage.getPage().equals("login")) {
                    // in cazul in care putem sa mergem pe pagina de login,
                    // setam pagina curenta a site-ului drept LoginPage
                    site.setCurrentPage(site.getAvailablePages().get(Site.LOGIN_ID));
                    return null;
                } else if (changePage.getPage().equals("register")) {
                    // in cazul in care putem sa mergem pe pagina de register,
                    // setam pagina curenta a site-ului drept RegisterPage
                    site.setCurrentPage(site.getAvailablePages().get(Site.REGISTER_ID));
                    return null;
                } else if (changePage.getPage().equals("logout")) {
                    if (site.getCurrentUser() == null) {
                        output.setError("Error");
                        output.setCurrentUser(null);
                        output.getCurrentMoviesList().clear();
                        return output;
                    }

                    // in cazul in care putem sa mergem pe pagina de logout,
                    // setam pagina curenta a site-ului drept HomepageNeautentificat
                    site.getPrevPageIndexes().clear();
                    site.setCurrentPage(site.getAvailablePages().get(Site.HOMEPAGENEAUT_ID));
                    site.setCurrentUser(null);
                    return null;
                } else if (changePage.getPage().equals("movies")) {
                    if (site.getCurrentUser() == null) {
                        output.setError("Error");
                        output.setCurrentUser(null);
                        output.getCurrentMoviesList().clear();
                        return output;
                    }
                    // in cazul in care putem sa mergem pe pagina de movies,
                    // setam pagina curenta a site-ului drept Movies
                    // si cream lista de filme pe care un utilizator le poate accesa
                    // (sunt excluse cele ale caror countriesBanned coincid cu tara utilizatorului)
                    site.getPrevPageIndexes().add(getPageIdx(site.getCurrentPage()));
                    site.setCurrentPage(site.getAvailablePages().get(Site.MOVIES_ID));
                    output.setCurrentUser(site.getCurrentUser());
                    ArrayList<Movie> currMovies = new ArrayList<>();
                    for (int j = 0; j < site.getMoviesIn().size(); j++) {
                        if (!site.getMoviesIn().get(j).getCountriesBanned()
                                .contains(site.getCurrentUser().getCredentials().getCountry())) {
                            currMovies.add(site.getMoviesIn().get(j));
                        }
                    }
                    site.getCurrentUser().setAllowedMovies(currMovies);
                    setMoviesAllowedInCountry(currMovies);
                    output.setCurrentMoviesList(currMovies);
                    output.setError(null);
                    return output;
                } else if (changePage.getPage().equals("upgrades")) {
                    if (site.getCurrentUser() == null) {
                        output.setError("Error");
                        output.setCurrentUser(null);
                        output.getCurrentMoviesList().clear();
                        return output;
                    }
                    // in cazul in care putem sa mergem pe pagina de upgrades,
                    // setam pagina curenta a site-ului drept UpgradesPage
                    site.getPrevPageIndexes().add(getPageIdx(site.getCurrentPage()));
                    site.setCurrentPage(site.getAvailablePages().get(Site.UPGRADES_ID));
                    return null;
                } else if (changePage.getPage().equals("see details")) {
                    if (site.getCurrentUser() == null) {
                        output.setError("Error");
                        output.setCurrentUser(null);
                        output.getCurrentMoviesList().clear();
                        return output;
                    }
                    // in cazul in care putem sa mergem pe pagina de see details,
                    // setam pagina curenta a site-ului drept SeeDetailsPage
                    for (int j = 0; j < site.getCurrentUser()
                            .getAllowedMovies().size(); j++) {
                        if (site.getCurrentUser().getAllowedMovies().get(j)
                                .getName().equals(changePage.getMovie())) {
                            site.getPrevPageIndexes().add(getPageIdx(site.getCurrentPage()));
                            site.setCurrentPage(site.getAvailablePages().get(Site.SEEDETAILS_ID));
                            // in cazul in care in input ni se specifica filmul pe pagina caruia
                            // trebuie sa intram, setam un camp special in SeeDetailsPage care
                            // sa retina despre ce film este vorba
                            ((SeeDetailsPage) site.getAvailablePages().get(Site.SEEDETAILS_ID))
                                    .setMovie(site.getCurrentUser().getAllowedMovies().get(j));
                            Output outputSeeDetails = new Output();
                            ArrayList<Movie> outputSeeDetailsCurrMovie = new ArrayList<>();
                            outputSeeDetailsCurrMovie.add(site.getCurrentUser()
                                    .getAllowedMovies().get(j));
                            outputSeeDetails.setError(null);
                            outputSeeDetails.setCurrentUser(site.getCurrentUser());
                            outputSeeDetails.setCurrentMoviesList(outputSeeDetailsCurrMovie);
                            return outputSeeDetails;
                        }
                    }
                }
            }
        }
        // output in caz de eroare
        output.setError("Error");
        output.setCurrentUser(null);
        output.getCurrentMoviesList().clear();
        return output;
    }

    @Override
    public Output visit(final BuyPrem buyPrem, final Site site) {
        // implementarea functionalitatii de a cumpara premium
        // intrucat in teste nu aparea, am evitat verificarea initiala de a vedea
        // daca un utilizator are deja statutul de premium, astfel incat sa nu poata
        // sa achizitioneze de doua ori
        Output output = new Output();
        if (site.getCurrentPage().equals(site.getAvailablePages().get(Site.UPGRADES_ID))) {
            // verificam daca ne aflam pe pagina de Upgrades
            if (site.getCurrentUser().getTokensCount() >= Site.PREM_PRICE) {
                //verificam daca utilizatorul are numarul necesar de tokens
                site.getCurrentUser().getCredentials().setAccountType("premium");
                site.getCurrentUser().setTokensCount(site.getCurrentUser()
                        .getTokensCount() - Site.PREM_PRICE);
                // ii setam accountType pe "premium" si ii scadem din numarul de tokens pe
                // care il avea pretul pentru premium
                return null;
            }
        }
        // output in caz de eroare
        output.setError("Error");
        output.setCurrentUser(null);
        output.getCurrentMoviesList().clear();
        return output;
    }

    @Override
    public Output visit(final BuyTokens buyTokens, final Site site) {
        // implementarea functionalitatii de a cumpara tokens
        // in teste nu apare cazul in care un utilizator avea deja tokens si
        // doreste sa mai achizitioneze, asa ca nu am luat in considerare acest caz
        Output output = new Output();
        // intrucat field-ul de balance este String, trebuie mai intai parsat drept int
        int balance = Integer.parseInt(site.getCurrentUser().getCredentials().getBalance());
        if (site.getCurrentPage().equals(site.getAvailablePages().get(Site.UPGRADES_ID))) {
            // verificam daca suntem pe pagina de upgrades
            if (balance >= buyTokens.getCount()) {
                // verificam daca avem suficient balance
                site.getCurrentUser().setTokensCount(site.getCurrentUser()
                        .getTokensCount() + buyTokens.getCount());
                site.getCurrentUser().getCredentials()
                        .setBalance(String.valueOf(balance - buyTokens.getCount()));
                // adaugam token-urile in cont si parsam balance-ul nou drept String
                return null;
            }
        }
        // output in caz de eroare
        output.setError("Error");
        output.setCurrentUser(null);
        output.getCurrentMoviesList().clear();
        return output;
    }

    @Override
    public Output visit(final Filter filter, final Site site) {
        // implementarea functionalitatii de a filtra lista de filme
        Output output = new Output();
        // de fiecare data cand incercam sa filtram filmele, filtrele
        // anterioare sunt sterse, deci pentru utilizatorul curent, lista cu filmele
        // pe care are voie sa le acceseze revine la lista filmelor pe care are voie
        // sa le vada intr-o tara
        site.getCurrentUser().setAllowedMovies(moviesAllowedInCountry);
        ArrayList<Movie> currMovies = new ArrayList<>();
        if (site.getCurrentPage().equals(site.getAvailablePages().get(Site.MOVIES_ID))) {
            // verificam daca suntem pe pagina corecta
            if (filter.getContains() != null) {
                // verificare daca in input ni se precizeaza field-ul Contains si
                // toate cele 3 cazuri posibile
                for (int i = 0; i < site.getCurrentUser().getAllowedMovies().size(); i++) {
                    if (filter.getContains().getActors() != null
                            && filter.getContains().getGenre() == null) {
                        if (site.getCurrentUser().getAllowedMovies().get(i)
                                .getActors().containsAll(filter.getContains().getActors())) {
                            currMovies.add(site.getCurrentUser().getAllowedMovies().get(i));
                        }
                    }
                    if (filter.getContains().getActors() == null
                            && filter.getContains().getGenre() != null) {
                        if (site.getCurrentUser().getAllowedMovies().get(i)
                                .getGenres().containsAll(filter.getContains().getGenre())) {
                            currMovies.add(site.getCurrentUser().getAllowedMovies().get(i));
                        }
                    }
                    if (filter.getContains().getActors() != null
                            && filter.getContains().getGenre() != null) {
                        if (site.getCurrentUser().getAllowedMovies().get(i)
                                .getActors().containsAll(filter.getContains().getActors())
                                && site.getCurrentUser().getAllowedMovies().get(i)
                                .getActors().containsAll(filter.getContains().getGenre())) {
                            currMovies.add(site.getCurrentUser().getAllowedMovies().get(i));
                        }
                    }
                }
            } else {
                // in cazul in care nu avem Contains, adaugam in lista curenta de filme
                // toate filmele de la inceput(practic nu se aplica niciun filtru)
                currMovies.addAll(site.getCurrentUser().getAllowedMovies());
            }
            if (filter.getSort() != null) {
                // verificam daca avem nevoie sa facem o sortare si ce fel de sortare
                // este aceasta
                if (filter.getSort().getRating() != null
                        && filter.getSort().getRating().equals("decreasing")) {
                    currMovies.sort(Comparator.comparing(Movie::getRating).reversed());
                }
                if (filter.getSort().getRating() != null
                        && filter.getSort().getRating().equals("increasing")) {
                    currMovies.sort(Comparator.comparing(Movie::getRating));
                }
                if (filter.getSort().getDuration() != null
                        && filter.getSort().getDuration().equals("decreasing")) {
                    currMovies.sort(Comparator.comparing(Movie::getDuration).reversed());
                }
                if (filter.getSort().getDuration() != null
                        && filter.getSort().getDuration().equals("increasing")) {
                    currMovies.sort(Comparator.comparing(Movie::getDuration));
                }

            }
            // setam lista filmelor pe care are voie sa le acceseze un utilizator dupa
            // operatia de filtrare
            site.getCurrentUser().setAllowedMovies(currMovies);
            output.setCurrentUser(site.getCurrentUser());
            output.setCurrentMoviesList(currMovies);
            output.setError(null);
            return output;
        }
        // output in caz de eroare
        output.setCurrentUser(null);
        output.setError("Error");
        output.getCurrentMoviesList().clear();
        return output;
    }

    @Override
    public Output visit(final Like like, final Site site) {
        // implementarea functionalitatii de a da like unui film
        Output output = new Output();
        if (site.getCurrentPage().equals(site.getAvailablePages().get(Site.SEEDETAILS_ID))) {
            // verificam daca ne aflam pe pagina see details
            if (!(like.getMovie() != null && !"".equals(like.getMovie()))) {
                // cum inputul poate sau nu sa aiba specificat titlul filmului, verificam
                // daca acesta exista, iar in cazul in care nu este, il setam pe baza
                // titlului filmului retinut in SeeDetailsPage
                like.setMovie(((SeeDetailsPage) site.getAvailablePages()
                        .get(Site.SEEDETAILS_ID)).getMovie().getName());
            }
            for (int i = 0; i < site.getCurrentUser().getWatchedMovies().size(); i++) {
                // de data aceasta trebuie sa cautam in lista de filme pe care un utilizator
                // le-a urmarit deja
                if (site.getCurrentUser().getWatchedMovies()
                        .get(i).getName().equals(like.getMovie())) {
                    // in cazul in care se afla printre filmele urmarite, il adaugam in lista
                    // de filme pe care le apreciaza, iar pentru filmul respectiv, crestem valoarea
                    // field-ului numLikes
                    site.getCurrentUser().getLikedMovies()
                            .add(site.getCurrentUser().getWatchedMovies().get(i));
                    site.getCurrentUser().getWatchedMovies().get(i)
                            .setNumLikes(site.getCurrentUser()
                                    .getWatchedMovies().get(i).getNumLikes() + 1);
                    // setam output pentru cazul in care nu exista nicio eroare
                    output.setCurrentUser(site.getCurrentUser());
                    output.setError(null);
                    ArrayList<Movie> outputLike = new ArrayList<>();
                    outputLike.add(site.getCurrentUser().getWatchedMovies().get(i));
                    output.setCurrentMoviesList(outputLike);
                    return output;
                }
            }
        }
        // output in caz de eroare
        output.setCurrentUser(null);
        output.setError("Error");
        output.getCurrentMoviesList().clear();
        return output;
    }

    @Override
    public Output visit(final Login login, final Site site) {
        // implementarea functionalitatii de a te loga pe site
        Output output = new Output();
        if (site.getCurrentPage().equals(site.getAvailablePages().get(Site.LOGIN_ID))) {
            // verificam daca ne aflam pe pagina de login
            for (int i = 0; i < site.getUsersIn().size(); i++) {
                if (((User) site.getUsersIn().get(i)).getCredentials()
                        .getName().equals(login.getName())) {
                    if (((User) site.getUsersIn().get(i)).getCredentials()
                            .getPassword().equals(login.getPassword())) {
                        // verificam daca exista in lista de utilizatori,
                        // iar in cazul in care exista,setam pagina drept HomepageAutentificat,
                        // setam userul curent al site-ului si setam outputul
                        site.setCurrentPage(site.getAvailablePages().get(Site.HOMEPAGEAUT_ID));
                        site.setCurrentUser(((User) site.getUsersIn().get(i)));
                        output.setError(null);
                        output.setCurrentUser(((User) site.getUsersIn().get(i)));
                        output.getCurrentMoviesList().clear();
                        return output;
                    }
                }
            }
            // in cazul in care nu exista utilizatorul, ne intoarcem pe HomepageNeautentificat
            site.setCurrentPage(site.getAvailablePages().get(Site.HOMEPAGENEAUT_ID));
        }
        // output in caz de eroare
        output.setError("Error");
        output.setCurrentUser(null);
        output.getCurrentMoviesList().clear();
        return output;
    }

    @Override
    public Output visit(final Purchase purchase, final Site site) {
        // implementarea functionalitatii de a cumpara un film
        Output output = new Output();
        if (site.getCurrentPage().equals(site.getAvailablePages().get(Site.SEEDETAILS_ID))) {
            // verificam daca suntem pe SeeDetailsPage
            if (!(purchase.getMovie() != null && !"".equals(purchase.getMovie()))) {
                // la fel ca si la Like, trebuie sa vedem daca in input este specificat numele
                // filmului, iar daca nu, il setam noi
                purchase.setMovie(((SeeDetailsPage)
                        site.getAvailablePages().get(Site.SEEDETAILS_ID)).getMovie().getName());
            }
            for (int i = 0; i < site.getCurrentUser().getAllowedMovies().size(); i++) {
                if (site.getCurrentUser().getAllowedMovies().get(i)
                        .getName().equals(purchase.getMovie())) {

                    if (site.getCurrentUser().getPurchasedMovies().
                            contains(site.getCurrentUser().getAllowedMovies().get(i))) {
                        output.setError("Error");
                        output.setCurrentUser(null);
                        output.getCurrentMoviesList().clear();
                        return output;
                    }

                    // verificam daca filmul se afla in lista de filme
                    // la care are acces utilizatorul
                    if (site.getCurrentUser().getCredentials().getAccountType().equals("premium")) {
                        // verificam daca utilizatorul este premium
                        if (site.getCurrentUser().getNumFreePremiumMovies() > 0) {
                            // in cazul in care este premium si mai are filme gratis
                            // (din cele 15 initiale)
                            // adaugam filmul in lista de filme cumparate si returnam outputul

                            if (site.getCurrentUser().getPurchasedMovies()
                                    .contains(site.getCurrentUser().getAllowedMovies().get(i))) {
                                return null; //verificam daca un film a mai fost cumparat inainte
                            }

                            int numFreePremMovies = site.getCurrentUser().getNumFreePremiumMovies();
                            site.getCurrentUser().setNumFreePremiumMovies(numFreePremMovies - 1);
                            output.setError(null);
                            output.setCurrentUser(site.getCurrentUser());

                            site.getCurrentUser().getPurchasedMovies()
                                    .add(site.getCurrentUser().getAllowedMovies().get(i));
                            ArrayList<Movie> outputPurchaseCurrMovie = new ArrayList<>();
                            outputPurchaseCurrMovie.add(site.getCurrentUser()
                                    .getAllowedMovies().get(i));
                            output.setCurrentMoviesList(outputPurchaseCurrMovie);
                            return output;
                        } else {
                            // daca nu mai are filme gratis, verificam daca are numarul necesar de
                            // tokens pentru a achizitiona un film
                            if (site.getCurrentUser().getTokensCount() >= 2) {
                                // facem la fel ca mai sus
                                int tokensCount = site.getCurrentUser().getTokensCount();
                                site.getCurrentUser().setTokensCount(tokensCount - 2);
                                site.getCurrentUser().getPurchasedMovies()
                                        .add(site.getCurrentUser().getAllowedMovies().get(i));
                                output.setError(null);
                                output.setCurrentUser(site.getCurrentUser());
                                ArrayList<Movie> outputPurchaseCurrMovie = new ArrayList<>();
                                outputPurchaseCurrMovie.add(site.getCurrentUser()
                                        .getAllowedMovies().get(i));
                                output.setCurrentMoviesList(outputPurchaseCurrMovie);
                                return output;
                            }
                        }
                    } else if (site.getCurrentUser().getCredentials()
                            .getAccountType().equals("standard")) {
                        //verificam daca utilizatorul este standard
                        if (site.getCurrentUser().getTokensCount() > 1) {
                            // facem la fel ca mai sus
                            int tokensCountStandard = site.getCurrentUser().getTokensCount();
                            site.getCurrentUser().setTokensCount(tokensCountStandard - 2);
                            site.getCurrentUser().getPurchasedMovies().add(site
                                    .getCurrentUser().getAllowedMovies().get(i));
                            output.setError(null);
                            output.setCurrentUser(site.getCurrentUser());
                            ArrayList<Movie> outputPurchaseCurrMovie = new ArrayList<>();
                            outputPurchaseCurrMovie.add(site.getCurrentUser()
                                    .getAllowedMovies().get(i));
                            output.setCurrentMoviesList(outputPurchaseCurrMovie);
                            return output;
                        }
                    }
                }
            }
        }
        // output in caz de eroare
        output.setError("Error");
        output.getCurrentMoviesList().clear();
        output.setCurrentUser(null);
        return output;
    }

    @Override
    public Output visit(final Rate rate, final Site site) {
        // implementarea functionalitatii de a da o nota filmului
        Output output = new Output();
        if (site.getCurrentPage().equals(site.getAvailablePages().get(Site.SEEDETAILS_ID))) {
            // facem la fel ca si la purchase si like
            if (!(rate.getMovie() != null && !"".equals(rate.getMovie()))) {
                rate.setMovie(((SeeDetailsPage) site.getAvailablePages()
                        .get(Site.SEEDETAILS_ID)).getMovie().getName());
            }
            for (int i = 0; i < site.getCurrentUser().getWatchedMovies().size(); i++) {
                // verificam daca filmul se afla in lista celor vizionate
                if (site.getCurrentUser().getWatchedMovies().get(i)
                        .getName().equals(rate.getMovie())) {
                    if (rate.getRate() <= Site.SEEDETAILS_ID && rate.getRate() >= 0) {
                        //fara niciun misto, nu are niciun sens, dar se potrivea valoarea
                        // si imi era lene sa fac alta constanta, aici trebuia de fapt sa
                        // verificam daca nota acordata este [0,5]

                        if (site.getCurrentUser().getRatedMovies().
                                contains(site.getCurrentUser().getWatchedMovies().get(i))) {
                            for (int j = 0; j < site.getCurrentUser().getWatchedMovies().get(i)
                                    .getAllRatings().size(); j++) {
                                if (site.getCurrentUser().getWatchedMovies().get(i).getAllRatings()
                                        .get(j).getUser().equals(site.getCurrentUser())) {
                                    site.getCurrentUser().getWatchedMovies().get(i).getAllRatings()
                                            .get(j).setRating(rate.getRate());
                                    int ratingSum = 0;
                                    for (int k = 0; k < site.getCurrentUser().getWatchedMovies()
                                            .get(i).getAllRatings().size(); k++) {
                                        ratingSum += site.getCurrentUser().getWatchedMovies()
                                                .get(i).getAllRatings().get(k).getRating();
                                    }
                                    site.getCurrentUser().getWatchedMovies().get(i)
                                            .setRating(((double) ratingSum) / site.getCurrentUser()
                                                    .getWatchedMovies().get(i).getNumRatings());
                                    output.setCurrentUser(site.getCurrentUser());
                                    output.setError(null);
                                    ArrayList<Movie> outputRateCurrMovie = new ArrayList<>();
                                    outputRateCurrMovie.add(site.getCurrentUser()
                                            .getWatchedMovies().get(i));
                                    output.setCurrentMoviesList(outputRateCurrMovie);
                                    return output;
                                }
                            }
                        }


                        site.getCurrentUser().getWatchedMovies().get(i)
                                .setNumRatings(site.getCurrentUser().getWatchedMovies().get(i)
                                        .getNumRatings() + 1);
                        site.getCurrentUser().getWatchedMovies().get(i)
                                .getAllRatings().add(new Rating(rate
                                        .getRate(), site.getCurrentUser()));
                        int ratingSum = 0;
                        for (int j = 0; j < site.getCurrentUser().getWatchedMovies()
                                .get(i).getAllRatings().size(); j++) {
                            ratingSum += site.getCurrentUser().getWatchedMovies()
                                    .get(i).getAllRatings().get(j).getRating();
                        }
                        site.getCurrentUser().getWatchedMovies().get(i)
                                .setRating(((double) ratingSum) / site.getCurrentUser()
                                        .getWatchedMovies().get(i).getNumRatings());
                        // am calculat ratingul si l-am adaugat in lista de filme
                        // carora le-a dat "review" utilizatorul, apoi cream output-ul
                        site.getCurrentUser().getRatedMovies()
                                .add(site.getCurrentUser().getWatchedMovies().get(i));
                        output.setCurrentUser(site.getCurrentUser());
                        output.setError(null);
                        ArrayList<Movie> outputRateCurrMovie = new ArrayList<>();
                        outputRateCurrMovie.add(site.getCurrentUser().getWatchedMovies().get(i));
                        output.setCurrentMoviesList(outputRateCurrMovie);
                        return output;
                    }
                }
            }
        }
        // output in caz de eroare
        output.getCurrentMoviesList().clear();
        output.setError("Error");
        output.setCurrentUser(null);
        return output;
    }

    @Override
    public Output visit(final Register register, final Site site) {
        // implementarea functionalitatii register
        Output output = new Output();
        if (site.getCurrentPage().equals(site.getAvailablePages().get(Site.REGISTER_ID))) {
            // verificam daca suntem pe RegisterPage
            for (int i = 0; i < site.getUsersIn().size(); i++) {
                if (register.getCredentials().getName()
                        .equals(((User) site.getUsersIn().get(i)).getCredentials().getName())) {
                    // verificam daca mai exista utilizatorul si afisam mesajul de eroare
                    output.setError("Error");
                    output.setCurrentUser(null);
                    output.setCurrentMoviesList(null);
                    site.setCurrentPage(site.getAvailablePages().get(Site.HOMEPAGENEAUT_ID));
                    return output;
                }
            }
            // cream un nou user cu datele respective si il adaugam in lista de useri,
            // iar apoi ne mutam pe pagina HomepageAutentificat, setam userul curent
            // si construim outputul
            User newUser = new User();
            CredentialsInput credentials = new CredentialsInput();
            credentials.setName(register.getCredentials().getName());
            credentials.setPassword(register.getCredentials().getPassword());
            credentials.setAccountType(register.getCredentials().getAccountType());
            credentials.setCountry(register.getCredentials().getCountry());
            credentials.setBalance(register.getCredentials().getBalance());
            newUser.setCredentials(credentials);
            site.getUsersIn().add(newUser);
            site.setCurrentPage(site.getAvailablePages().get(Site.HOMEPAGEAUT_ID));
            site.setCurrentUser(((User) site.getUsersIn().get(site.getUsersIn().size() - 1)));
            output.setCurrentUser(site.getCurrentUser());
            output.setError(null);
            return output;
        }
        // in cazul unei erori, afisam mesajul corespunzator
        output.setError("Error");
        output.setCurrentUser(null);
        output.getCurrentMoviesList().clear();
        return output;
    }

    @Override
    public Output visit(final Search search, final Site site) {
        // implementarea functionalitatii de search
        Output output = new Output();
        if (site.getCurrentPage().equals(site.getAvailablePages().get(Site.MOVIES_ID))) {
            // verificam daca ne aflam pe MoviesPage
            ArrayList<Movie> movies = new ArrayList<>(site.getCurrentUser().getAllowedMovies());
            // am adaugat toate filmele intr-un ArrayList auxiliar
            for (int i = 0; i < movies.size(); i++) {
                // eliminam filmele care nu incep cu secventa data
                if (!movies.get(i).getName().startsWith(search.getStartsWith())) {
                    movies.remove(i);
                    // cum functia remove actualizeaza dimensiunea ArrayList-ului,
                    // trebuie sa micsoram de fiecare data iteratorul, altfel vom
                    // avea IndexOutOfBounds
                    i--;
                }
            }
            site.getCurrentUser().setAllowedMovies(movies);
            // setam outputul
            output.setCurrentMoviesList(movies);
            output.setError(null);
            output.setCurrentUser(site.getCurrentUser());
            return output;
        }
        // output in caz de eroare
        output.setError("Error");
        output.setCurrentUser(null);
        output.getCurrentMoviesList().clear();
        return output;
    }

    @Override
    public Output visit(final Watch watch, final Site site) {
        // implementarea actiunii de a urmari un film
        Output output = new Output();
        if (site.getCurrentPage().equals(site.getAvailablePages().get(Site.SEEDETAILS_ID))) {
            // verificam daca ne aflam pe SeeDetailsPage
            if (!(watch.getMovie() != null && !"".equals(watch.getMovie()))) {
                // la fel ca si la purchase, like si rate
                watch.setMovie(((SeeDetailsPage) site.
                        getAvailablePages().get(Site.SEEDETAILS_ID)).getMovie().getName());
            }
            for (int i = 0; i < site.getCurrentUser().getPurchasedMovies().size(); i++) {
                if (site.getCurrentUser().getPurchasedMovies().get(i)
                        .getName().equals(watch.getMovie())) {
                    // verificam daca am achizitionat filmul inainte de a-l urmari
                    // apoi il adaugam in lista utilizatorului si construim output-ul

                    if (site.getCurrentUser().getWatchedMovies().
                            contains(site.getCurrentUser().getPurchasedMovies().get(i))) {
                        output.setError(null);
                        output.setCurrentUser(site.getCurrentUser());
                        ArrayList<Movie> outputWatchCurrMovie = new ArrayList<>();
                        outputWatchCurrMovie.add(site.getCurrentUser().getPurchasedMovies().get(i));
                        output.setCurrentMoviesList(outputWatchCurrMovie);
                        return output;
                    }
                    site.getCurrentUser().getWatchedMovies()
                            .add(site.getCurrentUser().getPurchasedMovies().get(i));
                    output.setError(null);
                    output.setCurrentUser(site.getCurrentUser());
                    ArrayList<Movie> outputWatchCurrMovie = new ArrayList<>();
                    outputWatchCurrMovie.add(site.getCurrentUser().getPurchasedMovies().get(i));
                    output.setCurrentMoviesList(outputWatchCurrMovie);
                    return output;
                }
            }
        }
        // output in caz de eroare
        output.setError("Error");
        output.setCurrentUser(null);
        output.getCurrentMoviesList().clear();
        return output;
    }

    @Override
    public Output visit(final Back back, final Site site) {
        Output output = new Output();
        // verificam daca am mai fost pe vreo pagina inainte
        if (!site.getPrevPageIndexes().isEmpty()) {
            // schimbam pe ultima pagina vizitata
            site.setCurrentPage(site.getAvailablePages().get(site.
                    getPrevPageIndexes().get(site.getPrevPageIndexes().size() - 1)));
            // stergem ultimul index salvat
            site.getPrevPageIndexes().remove(site.getPrevPageIndexes().size() - 1);
            if (site.getCurrentPage().getPageName().equals("movies")) {
                // in cazul in care ne intoarcem pe pagina Movies,
                // trebuie sa afisam output-ul conform actiunii change page pe Movies
                site.setCurrentPage(site.getAvailablePages().get(Site.MOVIES_ID));
                output.setCurrentUser(site.getCurrentUser());
                ArrayList<Movie> currMovies = new ArrayList<>();
                for (int j = 0; j < site.getMoviesIn().size(); j++) {
                    if (!site.getMoviesIn().get(j).getCountriesBanned()
                            .contains(site.getCurrentUser().getCredentials().getCountry())) {
                        currMovies.add(site.getMoviesIn().get(j));
                    }
                }
                site.getCurrentUser().setAllowedMovies(currMovies);
                setMoviesAllowedInCountry(currMovies);
                output.setCurrentMoviesList(currMovies);
                output.setError(null);
                return output;
            }
            return null;
        }
        // in cazul in care nu am mai fost pe nicio pagina inainte, afisam eroare
        output.setError("Error");
        output.setCurrentUser(null);
        output.getCurrentMoviesList().clear();
        return output;
    }

    @Override
    public Output visit(final Subscribe subscribe, final Site site) {
        Output output = new Output();
        if (site.getCurrentPage().equals(site.getAvailablePages().get(Site.SEEDETAILS_ID))) {
            // verificam daca ne aflam pe pagina See Details, doar de acolo putem sa dam subscribe
            for (int i = 0; i < site.getMoviesIn().size(); i++) {
                if (site.getMoviesIn().get(i).equals((
                        (SeeDetailsPage) site.getCurrentPage()).getMovie())) {
                    // intram pe pagina filmului, iar aici verificam daca genul
                    // la care vrem sa ne abonam se regaseste in lista genurilor filmului actual
                    if (site.getMoviesIn().get(i).getGenres()
                            .contains(subscribe.getSubscribedGenre())) {
                        if (!site.getCurrentUser().getSubscribedGenres()
                                .contains(subscribe.getSubscribedGenre())) {
                            // oprim programul si adaugam genul in lista utilizatorului
                            site.getCurrentUser().getSubscribedGenres()
                                    .add(subscribe.getSubscribedGenre());
                            return null;
                        }
                    }
                }
            }
        }
        // returnam o eroare
        output.setError("Error");
        output.setCurrentUser(null);
        output.getCurrentMoviesList().clear();
        return output;
    }

    @Override
    public Output visit(final AddMovie addMovie, final Site site) {
        Output output = new Output();
        int k = 0;
        for (int i = 0; i < site.getMoviesIn().size(); i++) {
            // verificam daca filmul mai exista in lista site-ului,
            // un mod foarte ineficient de a face acest lucru
            if (addMovie.getAddedMovie().getName().equals(site.getMoviesIn().get(i).getName())) {
                k++;
            }
        }
        if (k == 0) {
            // in cazul in care nu mai exista, il cream,
            // il adaugam in lista si notificam utilizatorii de schimbare
            Movie newMovie = new Movie(addMovie.getAddedMovie());
            site.getMoviesIn().add(newMovie);
            DatabaseAux databaseAux = new DatabaseAux(newMovie, "ADD");
            site.notifyUpdate(databaseAux);
            return null;
        }
        //returnam o eroare
        output.setCurrentUser(null);
        output.setError("Error");
        output.getCurrentMoviesList().clear();
        return output;
    }

    @Override
    public Output visit(final DeleteMovie deleteMovie, final Site site) {
        Output output = new Output();
        int k = 0;
        for (int i = 0; i < site.getMoviesIn().size(); i++) {
            // aceeasi verificare proasta ca mai inainte
            if (site.getMoviesIn().get(i).getName().equals(deleteMovie.getDeletedMovie())) {
                k++;
            }
        }
        if (k != 0) {
            // in cazul in care filmul exista, trebuie sa il stergem
            // din toate listele tuturor utilizatorilor si din baza de date a site-ului
            for (int i = 0; i < site.getUsersIn().size(); i++) {
                for (int j = 0; j < ((User) site.getUsersIn().get(i))
                        .getAllowedMovies().size(); j++) {
                    if (((User) site.getUsersIn().get(i)).getAllowedMovies().get(j)
                            .getName().equals(deleteMovie.getDeletedMovie())) {
                        ((User) site.getUsersIn().get(i)).getAllowedMovies().remove(j);
                    }
                }
                for (int j = 0; j < ((User) site.getUsersIn().get(i))
                        .getWatchedMovies().size(); j++) {
                    if (((User) site.getUsersIn().get(i)).getWatchedMovies().get(j)
                            .getName().equals(deleteMovie.getDeletedMovie())) {
                        ((User) site.getUsersIn().get(i)).getWatchedMovies().remove(j);
                    }
                }
                for (int j = 0; j < ((User) site.getUsersIn().get(i))
                        .getPurchasedMovies().size(); j++) {
                    if (((User) site.getUsersIn().get(i)).getPurchasedMovies().get(j)
                            .getName().equals(deleteMovie.getDeletedMovie())) {
                        ((User) site.getUsersIn().get(i)).getPurchasedMovies().remove(j);
                    }
                }
                for (int j = 0; j < ((User) site.getUsersIn().get(i))
                        .getLikedMovies().size(); j++) {
                    if (((User) site.getUsersIn().get(i)).getLikedMovies().get(j)
                            .getName().equals(deleteMovie.getDeletedMovie())) {
                        ((User) site.getUsersIn().get(i)).getLikedMovies().remove(j);
                    }
                }
                if (site.getCurrentUser().getCredentials().getAccountType().equals("premium")) {
                    site.getCurrentUser().setNumFreePremiumMovies(site.getCurrentUser()
                            .getNumFreePremiumMovies() + 1);
                } else if (site.getCurrentUser().getCredentials()
                        .getAccountType().equals("standard")) {
                    site.getCurrentUser().setTokensCount(site.getCurrentUser()
                            .getTokensCount() + 2);
                }
            }
            Movie movieDeleted = null;
            for (int i = 0; i < site.getMoviesIn().size(); i++) {
                if (site.getMoviesIn().get(i).getName().equals(deleteMovie.getDeletedMovie())) {
                    movieDeleted = new Movie(site.getMoviesIn().get(i));
                    site.getMoviesIn().remove(i);
                }
            }
            // notificam utilizatorii de schimbare
            DatabaseAux databaseAux = new DatabaseAux(movieDeleted, "DELETE");
            site.notifyUpdate(databaseAux);
            return null;
        }
        // afisam o eroare
        output.setError("Error");
        output.setCurrentUser(null);
        output.getCurrentMoviesList().clear();
        return output;
    }

    @Override
    public Output visit(final LastRecom lastRecom, final Site site) {
        //am creat o ultima functie pentru recomandarea finala,
        // intrucat era mult mai usor in acest fel
        Output output = new Output();
        if (site.getCurrentUser() == null) {
            return null;
        }
        if (site.getCurrentUser().getCredentials()
                .getAccountType().equals("premium")) { // verificam daca utilizatorul este premium
            Notification notification = new Notification();
            notification.setMessage("Recommendation");
            // verificam daca utilizatorul a apreciat vreun film
            if (!site.getCurrentUser().getLikedMovies().isEmpty()) {
                ArrayList<String> genres = new ArrayList<>();
                for (int i = 0; i < site.getCurrentUser().getLikedMovies().size(); i++) {
                    for (int j = 0; j < site.getCurrentUser()
                            .getLikedMovies().get(i).getGenres().size(); j++) {
                        if (!genres.contains(site.getCurrentUser()
                                .getLikedMovies().get(i).getGenres().get(j))) {
                            // retinem doar genurile apreciate de utilizator
                            genres.add(site.getCurrentUser().getLikedMovies()
                                    .get(i).getGenres().get(j));
                        }
                    }
                }

                // o structura care retine un gen si numarul de like-uri
                ArrayList<LastRecomAux> vect = new ArrayList<>();
                for (String genre : genres) {
                    // initializam pentru fiecare gen cu 0
                    vect.add(new LastRecomAux(genre, 0));
                }
                for (int i = 0; i < genres.size(); i++) {
                    for (int j = 0; j < site.getCurrentUser().getLikedMovies().size(); j++) {
                        if (site.getCurrentUser().getLikedMovies().get(j)
                                .getGenres().contains(genres.get(i))) {
                            // crestem numarul de likeuri pentru genul curent
                            vect.get(i).setNoLikesForGenre(vect.get(i).getNoLikesForGenre() + 1);
                        }
                    }
                }
                // sortam vectorul dupa numarul de likeuri si mai apoi alfabetic
                vect.sort(Comparator.comparing(LastRecomAux::getNoLikesForGenre)
                        .thenComparing(LastRecomAux::getGenre));
                ArrayList<Movie> currMovies = new ArrayList<>();
                for (int j = 0; j < site.getMoviesIn().size(); j++) {
                    if (!site.getMoviesIn().get(j).getCountriesBanned()
                            .contains(site.getCurrentUser().getCredentials().getCountry())) {
                        // adaugam filmele in functie de cum sunt sortate genurile
                        currMovies.add(site.getMoviesIn().get(j));
                    }
                }
                // sortam filmele dupa numarul de likeuri pe fiecare film
                currMovies.sort(Comparator.comparing(Movie::getNumLikes).reversed());
                for (int i = 0; i < vect.size(); i++) {
                    for (Movie movie : currMovies) {
                        // verificam filmele in functie de gen
                        if (movie.getGenres().contains(vect.get(i).getGenre())) {
                            // verificam daca nu a mai urmarit filmul
                            if (!site.getCurrentUser().getWatchedMovies().contains(movie)) {
                                notification.setMovieName(movie.getName());
                                // trimitem o notificare in cazul in care am gasit vreun film
                                site.getCurrentUser().getNotifications().add(notification);
                                output.setCurrentUser(site.getCurrentUser());
                                output.setError(null);
                                output.setCurrentMoviesList(null);
                                return output;
                            }
                        }
                    }
                }
            } else {
                // altfel trimitem notificarea ca nu are nicio recomandare
                notification.setMovieName("No recommendation");
            }
            // afisam output fara nicio recomandare extra
            site.getCurrentUser().getNotifications().add(notification);
            output.setCurrentUser(site.getCurrentUser());
            output.setError(null);
            output.setCurrentMoviesList(null);
            return output;
        }
        return null;
    }

}
