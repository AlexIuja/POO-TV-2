package classes.packet.actions;

import classes.packet.otherClasses.Output;
import classes.packet.otherClasses.Site;

public interface ActionVisitor {
    /**
     * toate functiile din acest fisier sunt implementate in ActionVisitorImpl
     *
     * actiunea de Change Page pe site-ul site
     * @param changePage implementarea actiunii de change page din lista de actiuni
     * @param site site-ul care isi modifica campurile dupa actiune
     * @return
     */
    Output visit(ChangePage changePage, Site site);

    /**
     * actiunea de a cumpara cont premium
     * @param buyPrem implementarea actiunii de buy premium din lista de actiuni
     * @param site site-ul care isi modifica campurile dupa actiune
     * @return
     */
    Output visit(BuyPrem buyPrem, Site site);

    /**
     * actiunea de a cumpara tokens
     * @param buyTokens implementarea actiunii de buy tokens din lista de actiuni
     * @param site site-ul care isi modifica campurile dupa actiune
     * @return
     */
    Output visit(BuyTokens buyTokens, Site site);

    /**
     * actiunea de a filtra filmele
     * @param filter implementarea actiunii de a filtra elementele din lista de filme
     *               dupa anumite criterii
     * @param site site-ul care isi modifica campurile dupa actiune
     * @return
     */
    Output visit(Filter filter, Site site);

    /**
     * actiunea de a da like unui film achizitionat
     * @param like implementarea actiunii de a aprecia un film
     * @param site site-ul care isi modifica campurile dupa actiune
     * @return
     */
    Output visit(Like like, Site site);

    /**
     * actiunea de a te loga pe site
     * @param login implementarea actiunii de a te loga pe site
     * @param site site-ul care isi modifica campurile dupa actiune
     * @return
     */
    Output visit(Login login, Site site);

    /**
     * actiunea de a cumpara un film
     * @param purchase implementarea actiunii de a cumpara un film
     * @param site site-ul care isi modifica campurile dupa actiune
     * @return
     */
    Output visit(Purchase purchase, Site site);

    /**
     * actiunea de a da o nota unui film achizitionat
     * @param rate implementarea actiunii de a da o nota unui film
     * @param site site-ul care isi modifica campurile dupa actiune
     * @return
     */
    Output visit(Rate rate, Site site);

    /**
     * actiunea de a crea un cont nou pe site
     * @param register implementarea actiunii de a te inregistra pe site
     * @param site site-ul care isi modifica campurile dupa actiune
     * @return
     */
    Output visit(Register register, Site site);

    /**
     * actiunea de a cauta un film un functie de inceputul titlului
     * @param search implementarea actiunii de a cauta un film
     * @param site site-ul care isi modifica campurile dupa actiune
     * @return
     */
    Output visit(Search search, Site site);

    /**
     * actiunea de a urmari un film achizitionat
     * @param watch implementarea actiunii de a urmari un film
     * @param site site-ul care isi modifica campurile dupa actiune
     * @return
     */
    Output visit(Watch watch, Site site);


    /**
     * actiunea de a da inapoi pe site
     * @param back implementarea actiunii de a da inapoi pe site
     * @param site site-ul care isi modifica campurile dupa actiune
     * @return
     */
    Output visit(Back back, Site site);

    /**
     *
     * @param subscribe implementarea actiunii de a te abona la un gen
     * @param site site-ul care isi modifica campurile dupa actiune
     * @return
     */
    Output visit(Subscribe subscribe, Site site);

    /**
     *
     * @param addMovie implementarea actiunii de a adauga un film in baza de date
     * @param site site-ul care isi modifica campurile dupa actiune
     * @return
     */
    Output visit(AddMovie addMovie, Site site);

    /**
     *
     * @param deleteMovie implementarea actiunii de a sterge un film din baza de date
     * @param site site-ul care isi modifica campurile dupa actiune
     * @return
     */
    Output visit(DeleteMovie deleteMovie, Site site);

    /**
     *
     * @param lastRecom implementarea ultimei recomandari pentru utilizatorii premium
     * @param site site-ul care isi modifica campurile dupa actiune
     * @return
     */

    Output visit(LastRecom lastRecom, Site site);
}
