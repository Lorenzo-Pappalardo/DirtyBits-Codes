<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="style.css">
  <title>Upload Result</title>
</head>

<body>
  <?php
  if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    echo '<h1 class="center">Upload Result</h1>';
    $uploadDir = 'upload/';
    if ($tmp_path = $_FILES['uploadedFile']['tmp_name']) {
      $filename = explode('.', basename($tmp_path))[0];

      $typeNum = exif_imagetype($tmp_path);
      $typeString = typeToString($typeNum);
      if ($typeNum) {
        echo '<p>Uploaded image is of type: ' . $typeString . '</p>';
        if (!(move_uploaded_file($tmp_path, './uploads/' . $filename . '.' . strtolower($typeString)))) {
          echo '<h1 class="error center">Upload failed!</h1>';
        } else {
          echo '<h1>Image uploaded successfully!</h1>';
        }
      } else {
        echo '<h1 class="error center">Not a valid image!</h1>';
      }
    } else {
      echo '<h1 class="error center">Image too big! Max 2 MB</h1>';
    }
  }

  if ($_SERVER['REQUEST_METHOD'] == 'GET') {
    echo '<h1 class="error center">GET request not allowed!</h1>'; //Print an error
  }

  echo '<a class="center" href="./MyCloud.php">Go Back</a>';
  ?>
</body>

</html>

<?php
function typeToString($num)
{
  switch ($num) {
    case IMAGETYPE_GIF:
      return 'GIF';
    case IMAGETYPE_JPEG:
      return 'JPEG';
    case IMAGETYPE_PNG:
      return 'PNG';
    case IMAGETYPE_BMP:
      return 'BMP';
    case IMAGETYPE_WEBP:
      return 'WEBP';
    case IMAGETYPE_ICO:
      return 'ICO';
    default:
      return "Unknown file type!";
  }
}
