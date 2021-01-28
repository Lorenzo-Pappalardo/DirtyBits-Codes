<!-- projects/create.blade.php -->

@extends('layout')
@section('content')

<h1>Crea un task</h1>

<form method="POST" action="/tasks">
    {{ csrf_field() }}
    <div>
        ID progetto:
        <input type="number" name="project_id">
    </div><br><br>
    <div>
        Descrizione:
        <textarea name="description" placeholder="Descrizione task">{{ old('description') }}</textarea>
    </div><br><br>
    <div>
        Completato?
        <br>
        Si
        <input type="radio" name="completed" value="1">
        No
        <input type="radio" name="completed" value="0">
    </div><br><br>
    <div><button type="submit">Crea Task</button></div>
</form>

@if ($errors->any) <div>
    <ul>
        @foreach ($errors->all() as $err)
        <li>{{ $err }}</li>
        @endforeach
    </ul>
</div>
@endif

@endsection
