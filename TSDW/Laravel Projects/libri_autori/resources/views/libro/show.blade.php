@extends('layout')

@section('title', 'Mostra libro')

@section('body')
<h3>Autore_id: {{$libro->autore_id}}</h3>
<h3>Titolo: {{$libro->titolo}}</h3>
<h3>Prezzo: {{$libro->prezzo}}</h3>
<a href="/libro/{{$libro->id}}/edit"><input type="button" value="Modifica"></a>
@endsection