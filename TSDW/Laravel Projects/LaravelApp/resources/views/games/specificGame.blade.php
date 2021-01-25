@extends('layout')

@section('title', 'Requested Game')

@section('contents')
<p>Game's title: {{$game->title}}</p>
<p>Game's release_date: {{$game->release_date}}</p>
<p>Game's price: {{$game->price}}</p>
@endsection