@extends('layout')

@section('title', 'Libri')

@section('body')
<h1>Lista libri</h1>
<ul>
  @foreach ($libri as $libro)
  <li><a href="/libro/{{$libro->id}}">{{$libro->titolo}}</a></li>
  <a href="/autore/{{$libro->id}}/edit"><input type="button" value="Modifica"></a>
  <form action="/libro/{{$libro->id}}" method="POST">
    @csrf
    @method('DELETE')
    <input type="submit" value="Cancella">
  </form>
  @endforeach
</ul>
<br><br>
<a href="/libro/create">Aggiungi libro</a>
@endsection