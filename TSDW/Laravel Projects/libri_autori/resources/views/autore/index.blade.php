@extends('layout')

@section('title', 'Autori')

@section('body')
<h1>Lista Autori</h1>
<ul>
  @foreach ($autori as $autore)
  <li><a href="/autore/{{$autore->id}}">{{$autore->nome}}</a></li>
  <a href="/autore/{{$autore->id}}/edit"><input type="button" value="Modifica"></a>
  <form action="/autore/{{$autore->id}}" method="POST">
    @csrf
    @method('DELETE')
    <input type="submit" value="Cancella">
  </form>
  @endforeach
</ul>
@endsection