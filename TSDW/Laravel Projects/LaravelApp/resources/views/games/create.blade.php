@extends('layout')

@section('title', 'Create a new Game entry')

@section('contents')
<h1>Create a new Game entry</h1>
<form method="POST" action="/games">
  @csrf
  <p>Game's title: <input type="text" name="title" required></p>
  <p>Game's release date: <input type="date" name="release_date" required></p>
  <p>Game's price: <input type="number" step=0.01 name="price" required></p>
  <input type="submit" value="Submit">
</form>
@endsection