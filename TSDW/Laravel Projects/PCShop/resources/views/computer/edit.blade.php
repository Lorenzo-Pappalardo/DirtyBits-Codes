@extends('layout')

@section('title', 'Update {{$computer->name}}')

@section('body')
<h1>Update {{$computer->name}}</h1>
<form action="/computers/{{$computer->id}}" method="POST">
  @csrf
  @method('PATCH')
  <p class="text">Name: <input type="text" name="name" value="{{$computer->name}}" required></p>
  <input type="submit" value="Create">
</form>
@endsection