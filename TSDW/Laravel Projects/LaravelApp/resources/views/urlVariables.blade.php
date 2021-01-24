@extends('layout')

@section('title', 'URL Variable')

@section('contents')
<h1>You sent: {{$urlVariable}}</h1>
@endsection