@extends('layout')

@section('title', 'Computers')

@section('body')
<ul>
  @foreach ($computers as $computer)
  <li>
    <div class="text bold big inline">{{$computer->name}}</div>
    <div class="price inline indent">Price: {{$computer->price}}</div>
    <a href="/computers/{{$computer->id}}" class="indent"><input type="button" value="Manage Components"></a>
    <form action="/computers/{{$computer->id}}" method="POST" class="inline">
      @csrf
      @method('DELETE')
      <input type="submit" class="indent" value="Delete"></a>
    </form>
    <br><br><br>
  </li>
  @endforeach
</ul>
<br>
<a href="/computers/create">Create a new Computer</a>
@endsection