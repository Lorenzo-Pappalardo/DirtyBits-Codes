@extends('layout')

@section('title', 'Modifica autore')

@section('body')
<form action="/autore/{{$autore->id}}" method="POST">
  <h1>Modifica autore</h1>
  @csrf
  @method('PUT')
  Nome: <input type="text" name="nome" value="{{$autore->nome}}">
  Età: <input type="number" name="eta" value="{{$autore->eta}}">
  <input type="submit" value="Modifica">
</form>
@endsection