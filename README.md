# Proiect - README

*Proiectul se gaseste pe [Repo-ul de GitHub](https://github.com/stillscript/POO-TV-2).*

### Functionarea proiectului

Voi explica doar implementarea extra pentru etapa a 2-a.

1. Design Pattern-uri folosite
    - Singleton: paginile de web
    - Visitor: executarea automata a actiunilor
    - Factory: crearea automata a listei de actiuni
    - Observer: trimiterea automata a notificarilor
2. Input
    - am adaugat noi informatii de preluat, cum ar fi deletedMovie sau addedMovie
3. Partea de executie a programului
    - am adaugat noile functii, acestea ruleaza la fel ca in etapa 1
    - in cazul "Add Movie", verificam daca utilizatorul este abonat la un gen al filmului si ii trimitem o notificare
    - in cazul "Delete Movie", la fel ca la Add Movie
    - pentru Subscribe, am adaugat la User un field in care retinem la ce genuri se aboneaza
    - pentru Back, am creat un array in care retin index-ul fiecarei pagini pe care a fost utilizatorul inainte de a da logout, iar apoi aplic Change Page pe pagina respectiva, nu e necesar sa mai verific daca utilizatorul are voie pe pagina respectiva
    - pentru recomandari, am adaugat o noua functie, numita LastRecom, in care verificam daca utilizatorul curent este premium, iar in cazul in care este, aplicam algoritmul explicat in cerinta temei