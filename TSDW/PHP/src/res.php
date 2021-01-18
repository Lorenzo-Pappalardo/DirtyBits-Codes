<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
  $im = imagecreatefromjpeg($_POST["background"]);

  $imageInfo = getimagesize($_POST["background"]);

  $color = imagecolorallocate($im, 255, 0, 90);

  imagettftext($im, $imageInfo[1] / 10, 0, $imageInfo[0] / 25, $imageInfo[1] - $imageInfo[0] / 10, $color, "../assets/font.ttf", $_POST["text_over_background"]);
  imagettftext($im, $imageInfo[1] / 25, 0, 3 * $imageInfo[0] / 5, 29 * $imageInfo[1] / 30, $color, "../assets/font.ttf", "Made by " . $_POST["author"]);

  header('Content-type: image/jpeg');
  imagejpeg($im);
  imagedestroy($im);
}
