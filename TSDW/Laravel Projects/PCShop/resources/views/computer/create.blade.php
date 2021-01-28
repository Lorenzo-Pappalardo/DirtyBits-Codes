@extends('layout')

@section('title', 'Create a new Computer')

@section('body')
<h1>Create a new Computer</h1>
<form action="/computers" method="POST">
  @csrf
  <p class="text">Name: <input type="text" name="name" required></p>
  <input type="submit" value="Create">
</form>
@endsection