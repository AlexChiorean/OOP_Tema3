CHIOREAN Alexandru - 322CD

Pentru aceasta rezolvare a etapei 3, am pornit de la solutia oficiala a etapei 2, la care am adaugat metodele wrapped, endProgram, buyMerch, seeMerch, subscribe si getNotifications. Din clasa CommandRunner, acestea apeleaza clasa Admin, iar mai apoi, User. De mentionat ca design pattern-ul Singleton este implementat in clasa Admin.

wrapped - aceasta metoda capata forme diferite pentru user, artist, respectiv host, motiv pentru care am folosit design pattern-ul Visitor pentru a uniformiza apelarea sa. Ideea pe care am mers a fost utilizarea unor hash map-uri pentru user, host si artist, cu scopul de a retine datele cerute in formatul (nume, ascultari). In momentul in care un user da comanda wrapped, se verifica playerul sau, ca sa se vada daca asculta sau nu un fisier/colectie audio, si in functie de momentul la care a inceput, actualizeaza lista de ascultari conform fisierelor audio ascultate. In momentul in care un artist da comanda wrapped, se realizeaza pasii de actualizare a hash map-urilor fiecarui user, pentru a tine cont si de cei care asculta piesele sale la momentul curent. La host, doar se afiseaza statisticile.

endProgram - se itereaza prin toti artistii, iar cei care au avut vanzari sau revenue de orife cel, sunt adaugati intr-un map in clasa Admin, care urmeaza a fi sortat dupa castiguri si mai apoi lexicografic, urmand afisarea ca in cerinta.

buyMerch - se verifica daca exista merch-ul, iar daca da, se adauga in lista de achizitii, iar castigurile artistului sunt actualizate.

seeMerch - se afiseaza lista de achizitii ale userului.

subscribe - se verifica daca s-a dat subscribe deja la creatorul de continut selectat. Daca da, se da unsubscribe, iar daca nu, se da subscribe.

getNotifications - la adaugarea unui album, merch sau event de catre un artist, se itereaza prin toti userii si se verifica care a dat subscribe la respectivul artist. Daca s-a dat subscribe, se adauga in lista de notificari mesajul corespunzator. In metoda getNotifications, se utilizeaza design pattern-ul Strategy pentru a urma pasii corespunzatori fiecarui tip de notificare, iar odata ce se apeleaza functia, acestea sunt adaugate unui array care va fi afisat, urmand la lista de notificari a userului sa fie golita.


Am modificat clasele Main, CommandRunner, Admin, User, Artist, Host, Player, SearchBar pentru a suporta modificarile realizate.

Am adaugat clasele MapManagement pentru a facilita folosirea map-urilor mentionate mai sus, respectiv pachetul notification, clasele SongBuilder, ConcreteVisitor si interfetele Visitor si Visitable pentru implementarea design pattern-urilor Strategy, Builder si Visitor.


Rezultat local:

test00_etapa3_wrapped_one_user_one_artist.json --------------------------------------------- PASSED (+4)
test01_etapa3_wrapped_one_user_n_artist.json ---------------------------------------------- PASSED (+4)
test02_etapa3_wrapped_n_user_one_artist.json ---------------------------------------------- PASSED (+4)
test03_etapa3_wrapped_n_user_n_artist.json ------------------------------------------------ FAILED (+0)
test04_etapa3_monetization_premium.json --------------------------------------------------- FAILED (+0)
test05_etapa3_monetization_free.json ------------------------------------------------------ FAILED (+0)
test06_etapa3_monetization_all.json ------------------------------------------------------- FAILED (+0)
test07_etapa3_notifications_simple.json --------------------------------------------------- PASSED (+4)
test08_etapa3_notifications_more.json ----------------------------------------------------- PASSED (+4)
test09_etapa3_merch_buy.json -------------------------------------------------------------- PASSED (+4)
test10_etapa3_wrapped_host.json ----------------------------------------------------------- PASSED (+4)
test11_etapa3_basicPageNavigation.json ---------------------------------------------------- FAILED (+0)
test12_etapa3_recommendations.json -------------------------------------------------------- FAILED (+0)
test13_etapa3_basic_recommendation.json --------------------------------------------------- FAILED (+0)
test14_etapa3_page_navigation.json -------------------------------------------------------- FAILED (+0)
test15_etapa3_complex.json ---------------------------------------------------------------- FAILED (+0)
test16_etapa3_complex.json ---------------------------------------------------------------- FAILED (+0)
-----------------------------------------------------
TESTS = 28/80
-----------------------------------------------------
Checkstyle: Ok
Checkstyle errors: 0
-----------------------------------------------------
CHECKSTYLE = 10/10
-----------------------------------------------------
.GIT score = 5/5
-----------------------------------------------------
README score = 5/5
-----------------------------------------------------
FINAL SCORE = 48/100