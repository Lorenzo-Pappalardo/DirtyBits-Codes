<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>@yield('title')</title>
  <style>
    body {
      background-color: #00132d
    }

    h1 {
      color: red
    }

    h3,
    h4 {
      color: yellow
    }

    .link {
      text-decoration: none;
      color: aquamarine
    }

    .inline {
      display: inline-block;
    }

    .inline.padding {
      padding-right: 1.5rem
    }
  </style>
</head>

<body>
  <a href="/universities" class="link">Home</a>
  <br><br>
  @yield('body')
  <br><br><br>
  <em><a href="https://github.com/Lorenzo-Pappalardo" class="link">Coded by Lorenzo Pappalardo</a></em>
</body>

</html>