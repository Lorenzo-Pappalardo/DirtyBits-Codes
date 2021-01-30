@extends('layout')

@section('title', '{{$university->name}}')

@section('body')
<h1>{{$university->name}}</h1>
<h3>Studenti iscritti:</h3>
@if ($university->students != null)
<ul>
  @foreach ($university->students as $student)
  <li>
    <h4>{{$student->full_name}}, è iscritto al {{$student->current_year}}º anno</h4>
  </li>
  @endforeach
</ul>
@else
Questa università non ha studenti
@endif
@endsection