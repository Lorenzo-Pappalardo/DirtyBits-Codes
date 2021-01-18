<!DOCTYPE html>
<html lang="en">

<head>
  <title>First Script</title>
</head>

<body>
  <h1>Hello World!</h1>
  <?php
  $cars = ["Tesla" => "100k", "Ferrari" => "1kk", "Dodge" => "80k"];

  $res = "";
  foreach ($cars as $car => $price) {
    $res .= '[' . $car . "]&emsp;&emsp;Price = " . $price . "<br>";
  }

  echo $res;
  ?>

  <br><br>

  <form method="POST" action=<?php echo $_SERVER["PHP_SELF"]; ?>>
    <p>First Name:</p>
    <input type="text" id="fname" name="fname">
    <p>Gender:</p>
    <input type="radio" id="male" name="gender" value="male">
    <label for="male">Male</label>
    <input type="radio" id="female" name="gender" value="female">
    <label for="male">Female</label>
    <p>Interests:</p>
    <input type="checkbox" id="yellow" name="interests[]" value="Yellow">
    <label for="yellow">Yellow</label><br>
    <input type="checkbox" id="memes" name="interests[]" value="Memes">
    <label for="yellow">Memes</label><br>
    <input type="checkbox" id="PC hardware" name="interests[]" value="PC hardware">
    <label for="yellow">PC hardware</label><br>
    <input type="checkbox" id="motorbikes" name="interests[]" value="Motorbikes">
    <label for="yellow">Motorbikes</label><br>
    <input type="checkbox" id="cars" name="interests[]" value="Cars">
    <label for="yellow">Cars</label><br>
    <input type="submit" value="Submit">
  </form>


  <?php
  if ($_SERVER["REQUEST_METHOD"] == "POST") {
    echo "<br><br><b>You sent:</b><br>";
    echo "First name: " . $_POST["fname"] . "<br>";
    echo "Gender: " . $_POST["gender"] . "<br>";
    echo "Interests:<br>";
    foreach ($_POST["interests"] as $value) {
      echo " - " . $value . "<br>";
    }
    echo "<br>";
  }
  ?>
</body>

</html>