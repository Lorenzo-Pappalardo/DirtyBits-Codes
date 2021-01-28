@extends('layout')

@section('content')

<h1>Progetti</h1>
<ul>
@foreach ($progetti as $prog)
<li>
    <a href="/projects/{{$prog->id}}">{{$prog->title}}</a>
</li>
@endforeach
<br>
<li><a href="/projects/create">Crea nuovo progetto</a></li>
</ul>

<a href="/tasks/create">Add a task</a>

@endsection


