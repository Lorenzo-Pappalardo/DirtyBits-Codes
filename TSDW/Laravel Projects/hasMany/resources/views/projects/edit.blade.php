@extends('layout')

@section('content')

<h1>Modifica il progetto</h1>

<form method="POST" action="/projects/{{$project->id}}">
    {{ csrf_field() }}
    <input type="hidden" name="_method" value="PATCH">
    <input type="text" name="title" value="{{$project->title}}"> <br><br>
    <b>Descrizione progetto</b><br>
    <textarea name="description">{{$project->description}}</textarea><br><br>
    <button type="submit">Modifica progetto</button><br>
</form>

<form method="POST" action="/projects/{{ $project->id }}">
    @csrf
    @method('DELETE')
    <button type="submit">Cancella progetto</button>
</form>

@endsection

{{-- view projects/edit.blade.php --}}
