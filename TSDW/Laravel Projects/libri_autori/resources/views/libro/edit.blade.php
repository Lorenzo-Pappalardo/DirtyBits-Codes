@extends('layout')

@section('title', 'Modifica libro')

@section('body')
<form action="/libro/{{$libro->id}}" method="POST">
  <h1>Modifica libro</h1>
  @csrf
  @method('PUT')
  Autore_id: <input type="number" name="autore_id" value="{{$libro->autore_id}}">
  Titolo: <input type="text" name="titolo" value="{{$libro->titolo}}">
  Prezzo: <input type="number" name="prezzo" value="{{$libro->prezzo}}">
  <input type="submit" value="Modifica">
</form>
<form action="/libro/{{$libro->id}}" method="POST">
  @csrf
  @method('DELETE')
  <input type="submit" value="Cancella">
</form>
@endsection