@extends('layout')

@section('title', 'Games List')

@section('contents')
<h1>Games in list</h1>
<ul>
  @foreach ($gamesList as $game)
  <li>
    <div style="font-size: 1.5rem">
      <p style="display: inline-block">{{$game->title}} was released on {{$game->release_date}} and it cost
        {{$game->price}}</p>
      <a href="/games/{{$game->id}}/edit" style="display: inline-block"><input type="button" value="Edit"></a>
      <form method="POST" action="/games/{{$game->id}}" style="display: inline-block">
        @csrf
        @method('DELETE')
        <input type="submit" value="Delete">
      </form>
    </div>
  </li>
  @endforeach
</ul>
<em><a href="/games/create">Add a new Game</a></em>
<br>
@endsection