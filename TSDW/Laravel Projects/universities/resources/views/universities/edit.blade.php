@extends('layout')

@section('title', 'Modifica')

@section('body')
<h1>{{$university->name}}</h1>
<form action="/universities/{{$university->id}}" method="POST" class="inline">
  @csrf
  @method('PUT')
  <h3 class="inline indent">Nome:</h3><input type="text" name="name">
  <h3 class="inline indent">Numero di studenti:</h3>
  <input type="text" name="studentsNum" value="{{$studentsNum}}" size="4" disabled>
  <input type="submit" value="Aggiorna">
</form>
<br><br>
@if ($university->students != null)
<ul>
  @foreach ($university->students as $student)
  <li class="inline">
    <h4 class="inline padding">{{$student->full_name}}</h4>
    <a href="/students/{{$student->id}}/edit"><input type="button" value="Aggiorna" class="inline"></a>
    <form action="/students/{{$student->id}}-{{$university->id}}" method="POST" class="inline">
      @csrf
      @method('DELETE')
      <input type="submit" value="Cancella" class="inline">
    </form>
  </li>
  @endforeach
</ul>
@endif
<br><br>
<a href="/students/create/{{$university->id}}" class="link">Aggiungi studenti</a>
@endsection