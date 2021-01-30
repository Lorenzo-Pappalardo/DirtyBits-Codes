@extends('layout')

@section('title', 'Aggiungi studente')

@section('body')
<h1>Aggiungi studente</h1>
<form action="/students/{{$university}}" method="POST" class="inline">
  @csrf
  <h3>Stai aggiungendo a: {{\App\Models\University::find($university)->name}}</h3>
  <h3 class="inline padding">Nome e Cognome:</h3><input type="text" name="full_name">
  <br>
  <h3 class="inline padding">Anno in corso:</h3>
  <input type="radio" name="current_year" value="1">
  <label for="1">1</label>
  <input type="radio" name="current_year" value="2">
  <label for="2">2</label>
  <input type="radio" name="current_year" value="3">
  <label for="3">3</label>
  <br>
  <input type="submit" value="Aggiungi">
</form>
@endsection