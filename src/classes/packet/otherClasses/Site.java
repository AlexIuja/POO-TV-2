package classes.packet.otherClasses;
import classes.packet.actions.Action;
import classes.packet.actions.ActionVisitor;
import classes.packet.actions.ActionVisitorImpl;
import classes.packet.actions.ActionFactory;
import classes.packet.actions.LastRecom;
import classes.packet.pages.HomepageAutentificat;
import classes.packet.pages.HomepageNeautentificat;
import classes.packet.pages.LoginPage;
import classes.packet.pages.LogoutPage;
import classes.packet.pages.MoviesPage;
import classes.packet.pages.RegisterPage;
import classes.packet.pages.SeeDetailsPage;
import classes.packet.pages.SitePage;
import classes.packet.pages.UpgradesPage;
import classes.fileio.ActionInput;
import classes.fileio.MovieInput;
import classes.fileio.UserInput;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public final class Site implements SubjectInterface {

    public static final int HOMEPAGENEAUT_ID = 0;
    public static final int LOGIN_ID = 1;
    public static final int REGISTER_ID = 2;
    public static final int HOMEPAGEAUT_ID = 3;
    public static final int MOVIES_ID = 4;
    public static final int SEEDETAILS_ID = 5;
    public static final int UPGRADES_ID = 6;
    public static final int LOGOUT_ID = 7;
    public static final int PREM_PRICE = 10;
    public static final int NUMBER_FREE_PREMIUM_MOVIES = 15;
    private ArrayList<ObserverInterface> usersIn = new ArrayList<>();
    private ArrayList<Movie> moviesIn = new ArrayList<>();
    private ArrayList<Action> actionsIn = new ArrayList<>();
    private ArrayList<SitePage> availablePages = new ArrayList<>();
    private SitePage currentPage;
    private User currentUser;
    private ArrayList<Integer> prevPageIndexes = new ArrayList<>();

    public Site(final ArrayList<UserInput> usersInput,
                final ArrayList<MovieInput> moviesInput,
                final ArrayList<ActionInput> actionsInput) {
        for (UserInput input : usersInput) {
            usersIn.add(new User(input));
        }
        for (MovieInput input : moviesInput) {
            moviesIn.add(new Movie(input));
        }
        ActionFactory.setSite(this);
        for (ActionInput input : actionsInput) {
            getActionsIn().add(ActionFactory.createAction(input));
        }

        actionsIn.add(new LastRecom(this));
        availablePages.add(HomepageNeautentificat.getInstance());
        availablePages.add(LoginPage.getInstance());
        availablePages.add(RegisterPage.getInstance());
        availablePages.add(HomepageAutentificat.getInstance());
        availablePages.add(MoviesPage.getInstance());
        availablePages.add(SeeDetailsPage.getInstance());
        availablePages.add(UpgradesPage.getInstance());
        availablePages.add(LogoutPage.getInstance());
        availablePages.get(HOMEPAGENEAUT_ID).setAllowedPagesToChange(new ArrayList<>(Arrays
                .asList(availablePages.get(HOMEPAGENEAUT_ID),
                        availablePages.get(LOGIN_ID), availablePages.get(REGISTER_ID))));
        availablePages.get(HOMEPAGEAUT_ID).setAllowedPagesToChange(new ArrayList<>(Arrays
                .asList(availablePages.get(HOMEPAGEAUT_ID), availablePages.get(MOVIES_ID),
                        availablePages.get(UPGRADES_ID), availablePages.get(LOGOUT_ID))));
        availablePages.get(MOVIES_ID).setAllowedPagesToChange(new ArrayList<>(Arrays
                .asList(availablePages.get(HOMEPAGEAUT_ID), availablePages.get(MOVIES_ID),
                        availablePages.get(SEEDETAILS_ID), availablePages.get(LOGOUT_ID))));
        availablePages.get(SEEDETAILS_ID).setAllowedPagesToChange(new ArrayList<>(Arrays
                .asList(availablePages.get(HOMEPAGEAUT_ID), availablePages.get(MOVIES_ID),
                        availablePages.get(SEEDETAILS_ID),
                        availablePages.get(UPGRADES_ID), availablePages.get(LOGOUT_ID))));
        availablePages.get(Site.UPGRADES_ID).setAllowedPagesToChange(new ArrayList<>(Arrays
                .asList(availablePages.get(HOMEPAGEAUT_ID), availablePages.get(MOVIES_ID),
                        availablePages.get(UPGRADES_ID), availablePages.get(LOGOUT_ID))));
        currentPage = availablePages.get(HOMEPAGENEAUT_ID);
    }


    /**
     *
     * @param usersInput
     */
    public void setUsers(final ArrayList<UserInput> usersInput) {
        for (UserInput input : usersInput) {
            usersIn.add(new User(input));
        }
    }

    /**
     *
     * @param moviesInput
     */
    public void setMovies(final ArrayList<MovieInput> moviesInput) {
        for (MovieInput input : moviesInput) {
            moviesIn.add(new Movie(input));
        }
    }

    public ArrayList<Action> getActionsIn() {
        return actionsIn;
    }

    public void setActionsIn(final ArrayList<Action> actionsIn) {
        this.actionsIn = actionsIn;
    }

    public ArrayList<ObserverInterface> getUsersIn() {
        return usersIn;
    }

    public void setUsersIn(final ArrayList<ObserverInterface> usersIn) {
        this.usersIn = usersIn;
    }

    public ArrayList<Movie> getMoviesIn() {
        return moviesIn;
    }

    public void setMoviesIn(final ArrayList<Movie> moviesIn) {
        this.moviesIn = moviesIn;
    }

    public SitePage getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(final SitePage currentPage) {
        this.currentPage = currentPage;
    }

    public ArrayList<SitePage> getAvailablePages() {
        return availablePages;
    }

    public void setAvailablePages(final ArrayList<SitePage> availablePages) {
        this.availablePages = availablePages;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(final User currentUser) {
        this.currentUser = currentUser;
    }

    public ArrayList<Integer> getPrevPageIndexes() {
        return prevPageIndexes;
    }

    public void setPrevPageIndexes(final ArrayList<Integer> prevPageIndex) {
        this.prevPageIndexes = prevPageIndex;
    }

    /**
     *
     * @param actions
     * @param objectWriter
     * @param objectMapper
     * @param output
     * @param args
     * @throws IOException
     */
    public void exec(final ArrayList<Action> actions,
                     final ObjectWriter objectWriter, final ObjectMapper objectMapper,
                     final ArrayNode output, final String[] args) throws IOException {
        ActionVisitor visitor = new ActionVisitorImpl();
        String out;

        int taskCounter = 0;

        for (Action action : actions) {
            Output p = action.accept(visitor);
            taskCounter++;
            if (p != null) {
//                System.out.println(taskCounter + ") " + action + "\n\t  ---> " + p);
                out = objectWriter.writeValueAsString(p);
                JsonNode n = objectMapper.readTree(out);
                output.add(n);
            }
        }
        objectWriter.writeValue(new File(args[1]), output);
    }

    @Override
    public void addUser(final ObserverInterface o) {
        usersIn.add(o);
    }

    @Override
    public void delUser(final ObserverInterface o) {
        usersIn.remove(o);
    }

    @Override
    public void notifyUpdate(final DatabaseAux databaseAux) {
        for (ObserverInterface o : usersIn) {
            o.update(databaseAux);
        }
    }
}
