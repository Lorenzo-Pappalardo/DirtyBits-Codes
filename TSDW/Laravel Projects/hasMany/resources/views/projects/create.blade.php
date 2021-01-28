<!-- projects/create.blade.php -->

@extends('layout')
@section('content')

<h1>Crea un progetto</h1>

<form method="POST" action="/projects">
    {{ csrf_field() }}
    <div>
        <input type="text" name="title" value="{{ old('title') }}"
               placeholder="Titolo progetto" required>
    </div><br><br>
    <div>
        <textarea name="description" placeholder="Descrizione progetto">{{ old('description') }}</textarea>
    </div><br><br>
    <div><button type="submit">Crea progetto</button></div>
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
