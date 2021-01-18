<!DOCTYPE html>
<html lang="en">

<head>
  <title>Fibonacci Calculator</title>
</head>

<body>
  <h1>Fibonacci Calculator</h1>
  <form method="POST" action="fibonacci.php">
    Insert a number:
    <input type="number" name="number" required /><br>
    <input type="radio" name="choice" value="pure" required>
    <label for="choice">Pure Recursion</label>
    <input type="radio" name="choice" value="dynamic" required>
    <label for="choice">Dynamic Programming</label>
    <br>
    <input type="submit" value="Submit" />
  </form>

  <?php
  if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $n = $_POST['number'];
    if ($n <= 0) {
      printError();
    }
    $approach = $_POST['choice'];

    $buffer = [0, 1, 1];
    $s = time();

    if ($approach == 'pure') {
      echo '<h3>[Pure Recursion]</h3>Fibonacci of ' . $n . ' is: <b style="font-size:25px">' . pureFibonacci($n) . '</b></p>';
    } else if ($approach == 'dynamic') {
      echo '<h3>[Dynamic Programming]</h3>Fibonacci of ' . $n . ' is: <b style="font-size:25px">' . dynamicFibonacci($n) . '</b></p>';
    } else {
      printError();
    }

    $s = time() - $s;
    echo 'Seconds: ' . $s;
  }

  function printError()
  {
    echo '<h1 style="color: red">Not Valid!</h1>';
    exit;
  }

  function pureFibonacci($n)
  {
    if ($n <= 2) {
      return 1;
    }
    return pureFibonacci($n - 2) + pureFibonacci($n - 1);
  }

  function dynamicFibonacci($n)
  {
    global $buffer;
    if (array_key_exists($n, $buffer)) {
      return $buffer[$n];
    }
    $buffer[$n] = dynamicFibonacci($n - 2) + dynamicFibonacci($n - 1);
    return $buffer[$n];
  }
  ?>
</body>

</html>