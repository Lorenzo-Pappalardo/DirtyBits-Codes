<!-- Simbols: δπσ -->

### [Stanford University](http://openclassroom.stanford.edu/MainFolder/courses/IntroToDatabases/old-site/docs/backup/ra-exercises.html)

##### DB

Person ( name, age, gender ), name is a key<br>
Frequents ( name, pizzeria ), (name, pizzeria) is a key<br>
Eats ( name, pizza ), (name, pizza) is a key<br>
Serves ( pizzeria, pizza, price ), (pizzeria, pizza) is a key<br>

1. <span style="color:red">Question:</span> Find all pizzerias frequented by at least one person under the age of 18<br>
   <span style="color:blue">Answer:</span> π pizzeria ((π name (σ age<18 (Person))) NATURAL JOIN (Frequents))<br>

1. <span style="color:red">Question:</span> Find the names of all females who eat either mushroom or pepperoni pizza (or both)<br>
   <span style="color:blue">Answer:</span> π name ((π name (σ pizza='mushroom' or pizza='pepperoni' (Eats))) NATURAL JOIN (σ gender='female' (Person)))<br>

1. <span style="color:red">Question:</span> Find the names of all females who eat both mushroom and pepperoni pizza<br>
   <span style="color:blue">Answer:</span> (σ gender='female' (Person)) NATURAL JOIN ((π name (σ pizza='mushroom' (Eats))) NATURAL JOIN (π name (σ pizza='pepperoni' (Eats))))<br>

1. <span style="color:red">Question:</span> Find all pizzerias that serve at least one pizza that Amy eats for less than $10.00<br>
   <span style="color:blue">Answer:</span> π pizzeria ((σ price<10 (Serves)) NATURAL JOIN (π pizza (σ name='Amy' (Eats))))<br>

1. <span style="color:red">Question:</span> Find all pizzerias that are frequented by only females or only males<br>
   <span style="color:blue">Answer:</span><br>
   R1 = π pizzeria ((Frequents) JOIN Frequents.name=Person.name AND gender='male' (Person))<br>
   R2 = π pizzeria ((Frequents) JOIN Frequents.name=Person.name AND gender='female' (Person))<br>
   (R1 - R2) U (R2 - R1)<br>

1. <span style="color:red">Question:</span> For each person, find all pizzas the person eats that are not served by any pizzeria the person frequents. Return all such person (name) / pizza pairs<br>
   <span style="color:blue">Answer:</span> (Eats) - (π name,pizza ((Frequents) NATURAL JOIN (Serves)))<br>

1. <span style="color:red">Question:</span> Find the names of all people who frequent only pizzerias serving at least one pizza they eat<br>
   <span style="color:blue">Answer:</span><br>
   R1 = (Frequents) NATURAL JOIN (π name,pizzeria ((Eats) NATURAL JOIN (Serves)))<br>
   R2 = π name ((Frequents) - R1)<br>
   π name (R1 - R2)<br>

1. <span style="color:red">Question:</span> Find the names of all people who frequent every pizzeria serving at least one pizza they eat<br>
   <span style="color:blue">Answer:</span><br>
   R1 = (Frequents) NATURAL JOIN (π name,pizzeria ((Eats) NATURAL JOIN (Serves)))<br>
   IDK

1. <span style="color:red">Question:</span> Find the pizzeria serving the cheapest pepperoni pizza. In the case of ties, return all of the cheapest-pepperoni pizzerias<br>
   <span style="color:blue">Answer:</span><br>
   R1 = σ pizza='pepperoni' (Serves)<br>
   R2 = R1<br>
   (R1) JOIN R1.price<=R2.price (R2)<br>

### [Washington University](https://courses.cs.washington.edu/courses/cse444/02sp/slides/Relational%20Algebra%20Exercises.htm)

##### DB

Product ( pid, name, price, category, maker-cid), pid is a key<br>
Purchase (buyer-ssn, seller-ssn, store, pid)<br>
Company (cid, name, stock-price, country), cid is a key<br>
Person(ssn, name, phone number, city), ssn is a key<br>

1. <span style="color:red">Question:</span> Find people who bought telephony products<br>
   <span style="color:blue">Answer:</span> π buyer-ssn ((Purchase) NATURAL JOIN (π pid (σ category='telephony' (Product))))<br>

1. <span style="color:red">Question:</span> Find names of people who bought American products<br>
   <span style="color:blue">Answer:</span> π name ((Person) JOIN ssn=buyer-ssn (π buyer-ssn ((Purchase) NATURAL JOIN (π pid ((Product) JOIN maker-cid=cid (π cid (σ country='America' (Company))))))))<br>

1. <span style="color:red">Question:</span> Find names of people who bought American products and did not buy French products<br>
   <span style="color:blue">Answer:</span><br>
   R1 = π name ((Person) JOIN ssn=buyer-ssn (π buyer-ssn ((Purchase) NATURAL JOIN (π pid ((Product) NATURAL JOIN (π cid (σ country='America' (Company))))))))<br>
   R2 = π name ((Person) JOIN ssn=buyer-ssn (π buyer-ssn ((Purchase) NATURAL JOIN (π pid ((Product) NATURAL JOIN (π cid (σ country='France' (Company))))))))<br>
   R1 - R2

1. <span style="color:red">Question:</span> Find names of people who bought American products and they live in Seattle<br>
   <span style="color:blue">Answer:</span><br>
   R1 = π name ((Person) JOIN ssn=buyer-ssn (π buyer-ssn ((Purchase) NATURAL JOIN (π pid ((Product) NATURAL JOIN (π cid (σ country='America' (Company))))))))<br>
   R2 = π name (σ city='Seattle' (Person))<br>
   R1 NATURAL JOIN R2<br>

1. <span style="color:red">Question:</span> Find people who bought stuff from Joe or bought products from a company whose stock prices is more than $50<br>
   <span style="color:blue">Answer:</span><br>
   R1 = π buyer-ssn ((σ name='Joe' (Person)) JOIN ssn=seller-ssn (Purchase))<br>
   R2 = π buyer-ssn (Purchase) NATURAL JOIN (π pid ((Product) JOIN maker-cid=cid (π cid (σ stock-price>50 (Company)))))<br>
   R1 UNION R2

### [Bologna University](https://www.cs.unibo.it/~montesi/BD/ES2016-17/01.%20Relational%20Algebra.pdf)

##### DB

Employee(Code, Name Surname), code is a key<br>
BelongsTo(Employee, Office), Employee is a key<br>

1. <span style="color:red">Question:</span> Return codes of employees with no office<br>
   <span style="color:blue">Answer:</span> π Code (Employee) - δ Employee=Code (π Employee (BelongsTo))<br>

##### DB

State(Name, Area), Name is a key<br>
City(Code, Name, Inhabitants), Code is a key<br>
FormedOf(State, City), (State, City) is a key<br>

1. <span style="color:red">Question:</span> Return the names of the USA States having cities with more than 1.000.000 inhabitants<br>
   <span style="color:blue">Answer:</span> π State ((FormedOf) JOIN City=Code (σ inhabitants>1000000 (City)))<br>

1. <span style="color:red">Question:</span> City names belonging to states larger than 10000 squared miles<br>
   <span style="color:blue">Answer:</span> π Name ((City) JOIN Code=City ((FormedOf) JOIN Name = State (σ Area>10000 (State))))<br>

##### DB

Person(ID, Name, Surname, Age), ID is a key<br>
Registration(Person,Lecture), (Person, Lecture) is a key<br>
Lecture(Name,Price), Name is a key<br>

1. <span style="color:red">Question:</span> People’s names which are registred for a lecture that costs more than 10 times their age<br>
   <span style="color:blue">Answer:</span>π Person.name (σ Price>10xAge (Lecture JOIN Lecture.Name = Registration.Lecture ((Person) JOIN ID=Person (Registration))))<br>

##### DB

User(Tax,Surname,Birth), Tax is a key<br>
Field(FCode,IsCovered), FCode is a key<br>
Bookings(FCode,Day,TimeStart,TimeEnd,Tax), FCode is a key<br>

1. <span style="color:red">Question:</span> Tax code of the users that have booked at least two times a “non covered field” and that have never booked a covered field<br>
   <span style="color:blue">Answer:</span><br>
   R1 = (Bookings) NATURAL JOIN (σ IsCovered=False (Field))<br>
   R2 = R1<br>
   R3 = π Tax ((R1) JOIN R1.Tax=R2.Tax AND (R1.TimeEnd<R2.Start OR R1.Day<R2.Day) (R2))<br>
   R4 = π Tax (Bookings) NATURAL JOIN (σ IsCovered=True (Field))<br>
   R3 - R4

### Esame 9/12/2020

##### DB

Studente(matricola,nome,cognome), matricola è una chiave<br>
Materia(id,titolo,descrizione), id è una chiave<br>
Esercizi(id,testo,soluzione, materia,numerosoluzioni), id è una chiave e materia è una chiave esterna<br>
Risolto(idesercizio,idstudente,data), (idesercizio, idstudente) è una chiave, anche esterna<br>

##### Algebra Relazionale

1. <span style="color:red">Domanda:</span> Trovare gli studenti che non hanno risolto esercizi per la materia “basi di dati”<br>
   <span style="color:blue">Risposta:</span> π matricola (Studente) - δ idstudente=matricola (π idstudente ((Risolto) NATURAL JOIN (δ id=idesercizio (Esercizi) NATURAL JOIN (δ id=materia (π id (σ titolo='basi di dati' (Materia)))))))<br>

1. <span style="color:red">Domanda:</span> Trovare le materie per cui sono stati risolti tutti gli esercizi<br>
   <span style="color:blue">Risposta:</span> π materia (π materia, id (Esercizi) - (π materia, id (Esercizi) - (π materia, id (Esercizi) JOIN id=idesercizio (Risolto))))<br>

##### SQL

1. <span style="color:red">Domanda:</span> Per ogni materia contare il numero di esercizi disponibili e quelli risolti<br>
   <span style="color:blue">Risposta:</span><br>
   CREATE VIEW Disponibili (materia, disponibili) AS<br>
   SELECT materia, COUNT(id)<br>
   FROM Esercizi<br>
   GROUP BY materia<br>

   CREATE VIEW Risolti (materia, risolti) AS<br>
   SELECT materia, COUNT(DISTINCT idesercizio)<br>
   FROM Risolto R JOIN Esercizi E<br>
   ON R.idesercizio=E.id<br>
   GROUP BY materia<br>

   (Disponibili) NATURAL JOIN (Risolti)

1. <span style="color:red">Domanda:</span> Trovare gli esercizi che contengono la parola “SQL” che non sono stati risolti<br>
   <span style="color:blue">Risposta:</span><br>
   SELECT id<br>
   FROM Esercizi E<br>
   WHERE E.testo LIKE '%SQL%'<br>
   AND<br>
   E.id NOT IN (<br>
   SELECT idesercizio<br>
   FROM Risolto<br>
   );<br>

1. <span style="color:red">Domanda:</span> Implementare un trigger in SQL che ogni qualvolta viene inserita una soluzione per un esercizio nella relazione Risolto aggiorna il campo della tabella esercizi<br>
   <span style="color:blue">Risposta:</span><br>
   CREATE TRIGGER AggiornamentoEsercizi<br>
   AFTER INSERT ON Risolto<br>
   FOR EACH ROW<br>
   UPDATE Esercizi<br>
   SET numerosoluzioni = numerosoluzioni + 1<br>
   WHERE Esercizi.id = New.idesercizio<br>

### Itinere 1 - 9/12/2021

##### DB

Persona (cf, nome, cognome), cf è una chiave<br>
Libro (id, titolo, descrizione, autore, numerodilettori, datauscita, saga, volume), id è una chiave<br>
Recensione (id, libro, testo, data, persona), id è una chiave<br>
Letto(libro, persona, data), tutti sono chiavi<br>

##### Algebra Relazionale

1. <span style="color:red">Domanda:</span> Trovare i libri che hanno almeno 2 recensioni ma che non sono stati letti<br>
   <span style="color:blue">Risposta:</span> π libro ((Recensioni) JOIN libro=libro1 AND id>id1 (δ id=id1, libro=libro1, testo=testo1, data=data1, persona=persona1 (Recensioni))) - π libro (Letto)<br>

1. <span style="color:red">Domanda:</span> Trovare le persone che hanno letto tutti i libri di “JK Rowling”<br>
   <span style="color:blue">Risposta:</span> π persona (π libro, persona (Letto) / δ id=libro (π id (σ autore='JK Rowling' (Libro))))<br>

##### SQL

1. <span style="color:red">Domanda:</span> Implementare un vincolo che non consenta di inserire in Letto un libro di una saga se non nel corretto ordine cronologico (V1, V2,…)<br>
   <span style="color:blue">Risposta:</span><br>
   CREATE TRIGGER InOrdine<br>
   AFTER INSERT ON Letto<br>
   FOR EACH ROW<br>
   DECLARE @saga INT, @volume INT, @sagaLetto INT;<br>

   SET @saga = (SELECT saga<br>
   FROM Libro<br>
   WHERE Libro.id = NEW.libro);<br>

   SET @volume = (SELECT volume<br>
   FROM Libro<br>
   WHERE Libro.id = NEW.libro);<br>

   IF @volume < (SELECT volume<br>
   FROM Libro, Letto<br>
   WHERE Libro.saga = @saga<br>
   AND Letto.persona = NEW.persona<br>
   ORDER BY volume DESC<br>
   LIMIT 1)<br>
   DELETE FROM Letto<br>
   WHERE Letto.libro = NEW.libro<br>
   AND Letto.persona = NEW.persona<br>

   IDK

1. <span style="color:red">Domanda:</span> Per ogni autore contare il numero di libri e il numero di lettori distinti e il numero di recensioni avute da persone distinte<br>
   <span style="color:blue">Risposta:</span><br>
   CREATE VIEW Libri (Autore, 'Numero di libri') AS<br>
   SELECT autore, COUNT(id)<br>
   FROM Persona P JOIN Libro L<br>
   ON P.cf = L.autore<br>
   GROUP BY autore;<br>

   CREATE VIEW Lettori (Autore, 'Numero di lettori') AS<br>
   SELECT autore, SUM(numerodilettori)<br>
   FROM Persona P JOIN Libro L<br>
   ON P.cf = L.autore<br>
   GROUP BY autore;<br>

   CREATE VIEW Recensioni (Autore, 'Numero di recensioni') AS<br>
   SELECT autore, COUNT(DISTINCT persona)<br>
   FROM Libro L JOIN Recensione R<br>
   ON L.id = R.libro<br>
   GROUP BY autore;<br>

   Libri NATURAL JOIN Lettori NATURAL JOIN Recensioni<br>

### Itinere 2 - 9/12/2021

##### DB

AutoPosseduta(targa,idauto ,costorifornimenti,dataimmatricolazione), targa è una chiave<br>
Auto(id,marca, alimentazione,cilindrata), id è una chiave<br>
Rifornimento(targa,data,prezzolitro,litri), (targa, data) è una chiave<br>
Manutenzione(targa,data,descrizione,costo), (targa, data) è una chiave<br>

##### Algebra Relazionale

1. <span style="color:red">Domanda:</span> Trovare le auto, indicando marca e modello, che non sono state vendute<br>
   <span style="color:blue">Risposta:</span> π marca,id ((Auto) NATURAL JOIN (π id (Auto) - (δ idauto=id (π idauto (AutoPosseduta))))) <br>

1. <span style="color:red">Domanda:</span> Per ogni marca trovare le auto con la cilindrata maggiore<br>
   <span style="color:blue">Risposta:</span><br>
   R1 = Auto<br>
   R2 = R1<br>
   π marca,cilindrata (Auto) - (π R1.marca,R1.cilindrata ((R1) JOIN R1.marca=R2.marca AND R1.cilindrata<R2.cilindrata (R2)))<br>

##### SQL

1. <span style="color:red">Domanda:</span> Trovare le marche di automobili che hanno venduto tutti i modelli<br>
   <span style="color:blue">Risposta:</span><br>
   SELECT DISTINCT marca<br>
   FROM Auto A<br>
   WHERE NOT EXISTS ( SELECT id<br>
   FROM Auto A1<br>
   WHERE A.marca = A1.marca<br>
   AND NOT EXISTS ( SELECT idauto<br>
   FROM AutoPosseduta AP<br>
   WHERE A1.id = AP.idauto<br>
   ));<br>

1. <span style="color:red">Domanda:</span> Per ogni auto posseduta mostrare quelle per il quale il numero di manutenzioni effettuate è maggiore di quello medio. Per queste visualizzare pure il costo totale della manutenzione<br>
   <span style="color:blue">Risposta:</span><br>
   CREATE VIEW NumeroManutenzioni (targa, numManutenzioni) AS<br>
   SELECT targa, COUNT(data), SUM (costo)<br>
   FROM AutoPosseduta AP LEFT JOIN Manutenzione M<br>
   ON AP.targa = M.targa<br>
   GROUP BY AP.targa;<br>

   SELECT \*<br>
   FROM AutoPossedute AP JOIN NumeroManutenzioni NM<br>
   ON AP.targa = NM.targa<br>
   WHERE NM.numManutenzioni ><br>
   (SELECT AVG(numManutenzioni)<br>
   FROM NumeroManutenzioni);<br>

1. <span style="color:red">Domanda:</span> Implementare un trigger che ogni qualvolta viene inserito un rifornimento in Rifornimento aggiorna il costo complessivo in AutoPosseduta<br>
   <span style="color:blue">Risposta:</span><br>
   CREATE TRIGGER InserimentoRifornimento<br>
   AFTER INSERT ON Rifornimento<br>
   FOR EACH ROW<br>
   UPDATE AutoPosseduta<br>
   SET costorifornimenti = costorifornimenti + (NEW.prezzolitro \* NEW.litri)<br>
   WHERE targa = NEW.targa;<br>

### Itinere 3 - 9/12/2021

##### DB

Libro(id,titolo,descrizione,autore ,datauscita,sequeldi ,genere), id è una chiave<br>
CopiaLibro(collocazione,idlibro), collocazione è una chiave<br>
Persona(id,nome,cognome,prestiti), id è una chiave<br>
Prestito(libro,persona,dataprestito,datarestituzione,restitutito), (libro, persona, dataprestito) è una chiave<br>

##### Algebra Relazionale

1. <span style="color:red">Domanda:</span> Trovare le persone che non hanno mai chiesto libri di “Stephen King” ma hanno chiesto almeno un libro di “Joseph Conrad”<br>
   <span style="color:blue">Risposta:</span><br>
   R1 = π persona ((Prestito) NATURAL JOIN (π libro (Prestito) - (δ id=libro (π id (σ autore='Stephen King' (Libro))))))<br>
   R2 = π persona ((Prestito) NATURAL JOIN (δ id=libro (π id (σ autore='Joseph Conrad' (Libro)))))<br>
   R1 NATURAL JOIN R2<br>

1. <span style="color:red">Domanda:</span> Trovare le persone che hanno preso in prestito tutti i libri del genere “Fantasy”<br>
   <span style="color:blue">Risposta:</span> π persona, libro (Prestito) / (δ id=libro (π id (σ genere='Fantasy' (Libro))))<br>

##### SQL

1. <span style="color:red">Domanda:</span><br>
   <span style="color:blue">Risposta:</span><br>
