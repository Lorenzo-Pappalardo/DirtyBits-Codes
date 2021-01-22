<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="style.css">
  <title>MyCloud</title>
</head>

<body>
  <h1 class="center">MyCloud</h1>
  <p class="center">Free - Unlimited - Uncompromising - Consistent - Immutable - 2fast - xD</p>
  <form action="upload.php" method="POST" enctype="multipart/form-data">
    <input type="file" name="uploadedFile">
    <input type="submit" value="Upload">
  </form>
  <div class="gallery">
    <?php
    $dir = opendir('./uploads');
    $files = [];
    while (true) {
      $filename = readdir($dir);
      if ($filename !== false) {
        if ($filename !== '.' && $filename !== '..') {
          $files[$filename] = $filename;
        }
      } else {
        break;
      }
    }
    // print_r($files); // Just for debugging purposes

    if (!(empty($files))) {
      echo '<h2 class="center">Photo Gallery</h2>';
      echo '<ul class="gallery-ul">';
      foreach ($files as $image) {
        echo '<li><img src="./uploads/' . $image . '"></li>';
      }
      echo '</ul>';
    }
    ?>
    </ul>
  </div>
</body>

</html>