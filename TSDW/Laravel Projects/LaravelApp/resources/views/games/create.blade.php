<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Create a new Game entry</title>
  <style>
    h1 {
      text-align: center;
      color: darkred;
    }
  </style>
</head>

<body>
  <h1>Create a new Game entry</h1>
  <form method="POST" action="/games">
    @csrf
    <p>Game's title: <input type="text" name="title" required></p>
    <p>Game's release date: <input type="date" name="release_date" required></p>
    <p>Game's price: <input type="number" name="price" required></p>
    <input type="submit" value="Submit">
  </form>
</body>

</html>