<!-- Simbols: δπσ -->

### [Stanford University](http://openclassroom.stanford.edu/MainFolder/courses/IntroToDatabases/old-site/docs/backup/ra-exercises.html)

Person ( name, age, gender ), name is a key
Frequents ( name, pizzeria ), (name, pizzeria) is a key
Eats ( name, pizza ), (name, pizza) is a key
Serves ( pizzeria, pizza, price ), (pizzeria, pizza) is a key

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

Product ( pid, name, price, category, maker-cid), pid is a key
Purchase (buyer-ssn, seller-ssn, store, pid)
Company (cid, name, stock-price, country), cid is a key
Person(ssn, name, phone number, city), ssn is a key

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

Employee(Code, Name Surname), code is a key
BelongsTo(Employee, Office), Employee is a key

1. <span style="color:red">Question:</span> Return codes of employees with no office<br>
   <span style="color:blue">Answer:</span> π Code (Employee) - δ Employee=Code (π Employee (BelongsTo))<br>

State(Name, Area), Name is a key
City(Code, Name, Inhabitants), Code is a key
FormedOf(State, City), (State, City) is a key

1. <span style="color:red">Question:</span> Return the names of the USA States having cities with more than 1.000.000 inhabitants<br>
   <span style="color:blue">Answer:</span> π State ((FormedOf) JOIN City=Code (σ inhabitants>1000000 (City)))<br>

1. <span style="color:red">Question:</span> City names belonging to states larger than 10000 squared miles<br>
   <span style="color:blue">Answer:</span> π Name ((City) JOIN Code=City ((FormedOf) JOIN Name = State (σ Area>10000 (State))))<br>

Person(ID, Name, Surname, Age), ID is a key
Registration(Person,Lecture), (Person, Lecture) is a key
Lecture(Name,Price), Name is a key

1. <span style="color:red">Question:</span> People’s names which are registred for a lecture that costs more than 10 times their age<br>
   <span style="color:blue">Answer:</span>π Person.name (σ Price>10xAge (Lecture JOIN Lecture.Name = Registration.Lecture ((Person) JOIN ID=Person (Registration))))<br>

User(Tax,Surname,Birth), Tax is a key
Field(FCode,IsCovered), FCode is a key
Bookings(FCode,Day,TimeStart,TimeEnd,Tax), FCode is a key

1. <span style="color:red">Question:</span> Tax code of the users that have booked at least two times a “non covered field” and that have never booked a covered field<br>
   <span style="color:blue">Answer:</span><br>
   R1 = (Bookings) NATURAL JOIN (σ IsCovered=False (Field))<br>
   R2 = R1<br>
   R3 = π Tax ((R1) JOIN R1.Tax=R2.Tax AND (R1.TimeEnd<R2.Start OR R1.Day<R2.Day) (R2))<br>
   R4 = π Tax (Bookings) NATURAL JOIN (σ IsCovered=True (Field))<br>
   R3 - R4
