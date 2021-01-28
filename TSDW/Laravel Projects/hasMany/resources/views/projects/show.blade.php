@extends('layout')

@section('content')

<h2>Progetto: &ldquo;{{$project->title}}&rdquo;</h2>

<div><b>Descrizione</b>: {{$project->description}}
</div>

@if ($project->tasks->count())
<div>
    <h3>Tasks</h3>
    <ul>
        @foreach($project->tasks as $task)
        <li>{{ $task->description }} ------------------------------------- Completato?
            @if ($task->completed == 1)
            {{$completed = 'Si'}}
            @else
            {{$completed = 'No'}}
            @endif
        </li>
        @endforeach
    </ul>
</div>
@endif

<br>
<a href="/projects/{{ $project->id }}/edit">
    Modifica o cancella questo progetto </a>

@endsection

{{-- file projects/show.blade.php --}}