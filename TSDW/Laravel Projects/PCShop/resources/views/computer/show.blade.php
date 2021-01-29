@extends('layout')

@section('title', 'Create a new Computer')

@section('body')
<br>
<div class="text bold big">{{$computer->name}}</div>
@if ($computer->components != null)
<div class="text">Components:</div>
<ul>
  @foreach ($computer->components as $component)
  <li>
    <div class="text">{{$component->name}} - Price: {{$component->price}}</div>
  </li>
  @endforeach
</ul>
@endif
<div class="price">Price: {{$computer->price}}</div>
@endsection