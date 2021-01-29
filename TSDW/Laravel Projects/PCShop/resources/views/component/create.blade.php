@extends('layout')

@section('title', 'Add a component')

@section('body')
<h1>Add a component</h1>
<form action="{{$computer_id}}/components" method="POST">
  @csrf
  <p class="text">Name: <input type="text" name="name" required></p>
  <p class="text">Price: <input type="float" name="price" required></p>
  <input type="submit" value="Add">
</form>
@endsection