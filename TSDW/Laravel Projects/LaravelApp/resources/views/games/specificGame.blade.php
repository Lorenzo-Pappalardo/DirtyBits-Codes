@extends('layout')

@section('title', 'Requested Game')

@section('contents')
<h2>Game's title: {{$game->title}}</h2>
<h2>Game's release_date: {{$game->release_date}}</h2>
<h2>Game's price: {{$game->price}}</h2>
@endsection