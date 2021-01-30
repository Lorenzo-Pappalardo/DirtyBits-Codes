@extends('layout')

@section('title', 'Aggiungi università')

@section('body')
<h1>Aggiungi università</h1>
<form action="/universities" method="POST" class="inline">
  @csrf
  <h3 class="inline padding">Nome:</h3><input type="text" name="name">
  <input type="submit" value="Aggiungi">
</form>
@endsection