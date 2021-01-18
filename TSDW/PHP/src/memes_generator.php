<!DOCTYPE html>
<html lang="en">

<head>
  <title>Memes Generator</title>
</head>

<body>
  <h1>Memes Generator</h1>
  <form method="POST" action="res.php">
    <ol>
      <li>Choose background:<br>
        <input type="radio" name="background" value="../assets/money.jpg">
        <img src="../assets/money.jpg" alt="money" width="200">
        </input>
        <input type="radio" name="background" value="../assets/butterfly.jpg">
        <img src="../assets/butterfly.jpg" alt="money" width="200">
        </input>
      </li><br>
      <li>Insert text over background:
        <input type="text" name="text_over_background">
      </li><br>
      <li>Insert author:
        <input type="text" name="author">
      </li><br>
    </ol>
    <input type="submit" value="Submit">
  </form>
</body>

</html>

<?php //phpinfo() 
?>