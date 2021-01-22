<?php
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
}

if ($_SERVER['REQUEST_METHOD'] == 'GET') {
  echo '<h1 style="text-align: center; color: red;">GET request not allowed!</h1>'; //Print an error
}
