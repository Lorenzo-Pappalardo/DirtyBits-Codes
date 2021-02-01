@extends('layout')

@section('title', 'Aggiungi libro')

@section('body')
<form action="/libro" method="POST">
  <h1>Aggiungi libro</h1>
  @csrf
  Autore_id: <input type="number" name="autore_id">
  Titolo: <input type="text" name="titolo">
  Prezzo: <input type="number" name="prezzo">
  <input type="submit" value="Aggiungi">
</form>
@endsection