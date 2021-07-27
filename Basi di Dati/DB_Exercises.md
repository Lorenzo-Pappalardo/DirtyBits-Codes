# [Stanford University](http://openclassroom.stanford.edu/MainFolder/courses/IntroToDatabases/old-site/docs/backup/ra-exercises.html)

<!-- Simbols: δπσ -->

1. <span style="color:red">Question:</span> Find all pizzerias frequented by at least one person under the age of 18.
   <span style="color:blue">Answer:</span> π pizzeria ((π name (σ age<18 (Person))) NATURAL JOIN (Frequents))

1. <span style="color:red">Question:</span> Find the names of all females who eat either mushroom or pepperoni pizza (or both).
   <span style="color:blue">Answer:</span> π name ((π name (σ pizza='mushroom' or pizza='pepperoni' (Eats))) NATURAL JOIN (σ gender='female' (Person)))

1. <span style="color:red">Question:</span> Find the names of all females who eat both mushroom and pepperoni pizza.
   <span style="color:blue">Answer:</span> (σ gender='female' (Person)) NATURAL JOIN ((π name (σ pizza='mushroom' (Eats))) NATURAL JOIN (π name (σ pizza='pepperoni' (Eats))))

1. <span style="color:red">Question:</span> Find all pizzerias that serve at least one pizza that Amy eats for less than $10.00
   <span style="color:blue">Answer:</span> π pizzeria ((σ price<10 (Serves)) NATURAL JOIN (π pizza (σ name='Amy' (Eats))))

1. <span style="color:red">Question:</span>
   <span style="color:blue">Answer:</span>
