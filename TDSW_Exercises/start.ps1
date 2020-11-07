if ($args.length -lt 1) {
  throw "You must pass the file's name that you want to run";
}

$filename = $args[0]

Remove-Item .\build\$filename.class

javac "$filename.java" -d .\build

Set-Location .\build
java $args[0]
Set-Location ..