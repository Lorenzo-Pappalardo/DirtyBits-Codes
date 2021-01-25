@extends('layout')

@section('contents')
<h1>Games in list</h1>
@foreach ($gamesList as $game)
<h2>{{$game->title}} was released on {{$game->release_date}} and it cost {{$game->price}}</h2>
@endforeach
@endsection