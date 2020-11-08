if ($args.length -lt 1) {
  throw "You must pass the file's name that you want to run";
}

$path = $args[0]

$folder = "";
$basename = $args[0];
if ($path[0] -eq '.') {
  $tmp = $path.ToString().Split("{\}")
  $i = $tmp.Length
  $folder = $tmp[$i-2]
  $basename = $tmp[$i-1].Split("{.}")[0]
}

Remove-Item .\build\$basename.class

if ($folder -eq "" -or $folder -eq ".") {
  javac "$basename.java" -d .\build
} else {
  javac $path -d .\build
}

Set-Location .\build
if ($folder -eq "" -or $folder -eq ".") {
  java $basename
}
else {
  java "$folder.$basename"
}
Set-Location ..
Remove-Item -Recurse .\build\