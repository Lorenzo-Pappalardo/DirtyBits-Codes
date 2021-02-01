@extends('layout')

@section('title', 'Mostra autore')

@section('body')
<h3>Nome: {{$autore->nome}}</h3>
<h3>Eta: {{$autore->eta}}</h3>
<ul>
  @foreach ($libri as $libro)
  <li>Titolo libro: {{$libro->titolo}}, Prezzo: {{$libro->prezzo}}</li>
  <a href="/libro/{{$libro->id}}"><input type="button" value="Mostra"></a>
  @endforeach
</ul>
<br><br>
<a href="/libro/create">Aggiungi libro</a>
@endsection