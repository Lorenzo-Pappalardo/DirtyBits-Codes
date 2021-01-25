@extends('layout')

@section('title', 'Edit Game')

@section('contents')
<h1>Edit Game</h1>
<form method="POST" action="/games/{{$game->id}}">
  @csrf
  @method('PATCH')
  <p>Game's title: <input type="text" name="title" value="{{$game->title}}" required></p>
  <p>Game's release date: <input type="date" name="release_date" value="{{$game->release_date}}" required></p>
  <p>Game's price: <input type="number" step=0.01 name="price" value="{{$game->price}}" required></p>
  <input type="submit" value="Update">
</form>
<br>
<form method="POST" action="/games/{{$game->id}}">
  @csrf
  @method('DELETE')
  <input type="submit" value="Delete">
</form>
@endsection