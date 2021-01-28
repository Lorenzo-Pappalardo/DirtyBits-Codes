<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>@yield('title')</title>
  <style>
    body {
      background-color: #0f0f15
    }

    h1,
    .text,
    .price {
      color: #ffd500
    }

    .text.bold {
      font-weight: bolder
    }

    .text.big {
      font-size: 1.5rem
    }

    .price {
      color: lightblue
    }

    .inline {
      display: inline-block;
    }

    .indent {
      margin-left: 1rem;
    }

    a {
      text-decoration: none;
      color: white
    }
  </style>
</head>

<body>
  <a href="/computers">Home</a>
  <br>
  @yield('body')
  <br>
  <em><a href="https://github.com/Lorenzo-Pappalardo">Coded by Lorenzo Pappalardo</a></em>
</body>

</html>