@extends('layout')

@section('title', 'Lista università')

@section('body')
<h1>Lista università</h1>
<ul>
  @foreach ($universities as $university)
  <li>
    <h3 class="inline padding">{{$university->name}}</h3>
    <h4 class="inline padding">Numero di studenti iscritti: {{$university->studentsNum}}</h4>
    <a href="/universities/{{$university->id}}/edit"><input type="button" value="Aggiorna"></a>
    <form action="/universities/{{$university->id}}" method="POST" class="inline">
      @csrf
      @method('DELETE')
      <input type="submit" value="Cancella">
    </form>
  </li>
  @endforeach
</ul>
<br><br>
<a href="/universities/create" class="link">Aggiungi nuova università</a>
@endsection