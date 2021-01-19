$path = Get-Location
if ($args.Length -eq 1) {
  $path = $args[0]
}
elseif ($args.Length -gt 1) {
  Write-Warning 'Only the first argument will be taken into consideration'
  $path = $args[0]
}

Write-Host 'Server''s root directory will be' $path

$choice = Read-Host -Prompt 'Press ''y'' to publish it, otherwise it will be started locally'

$address = 'localhost:8080'
if ($choice -eq 'y') {
  $address = '0.0.0.0:8080'
}

Start-Process 'http://localhost:8080'

php -S $address -t $path -c $path