if ($args.length -lt 1) {
  Throw "You must pass the file's name that you want to run"
}

$arguments = [System.Collections.Generic.List[string]]::new()
for ($i = 1; $i -lt $args.Count; $i++) {
  $arguments.Add($args[$i])
}

Remove-Item -Recurse .\build -ErrorAction SilentlyContinue

$folder = Split-Path -Path $args[0] -Parent
$filename = Split-Path -Path $args[0] -Leaf
$filename = $filename.Split('.')[0]

if ($folder -eq "" -or $folder -eq ".") {
  javac "$filename.java" -d .\build
}
else {
  javac "$folder\$filename.java" -d .\build
}

Set-Location .\build
if ($folder -eq "" -or $folder -eq ".") {
  java $filename $arguments
}
else {
  $folder = $folder.Replace('\', '.')
  if ($folder.Substring(0, 2) -eq "..") {
    $folder = $folder.Substring(2)
  }
  java "$folder.$filename" $arguments
}
Set-Location ..