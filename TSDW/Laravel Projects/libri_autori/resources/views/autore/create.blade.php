@extends('layout')

@section('title', 'Aggiungi nuovo Autore')

@section('body')
<form action="/autore" method="POST">
  <h1>Aggiungi autore</h1>
  @csrf
  Nome: <input type="text" name="nome">
  Età: <input type="number" name="eta">
  <input type="submit" value="Aggiungi">
</form>
@endsection