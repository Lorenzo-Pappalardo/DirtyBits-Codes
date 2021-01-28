@extends('layout')

@section('title', 'Create a new Computer')

@section('body')
<br>
<div class="text bold big">{{$computer->name}}</div>
<div class="price">Price: {{$computer->price}}</div>
@endsection