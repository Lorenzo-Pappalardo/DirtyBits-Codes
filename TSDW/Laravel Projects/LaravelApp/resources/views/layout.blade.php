<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>@yield('title', 'Insert a title in the respective blade page')</title>
</head>

<body>
  <em><a href="/games">Home</a></em>
  @yield('contents')
  <br>
  <em>Coded by Lorenzo</em>
</body>

</html>