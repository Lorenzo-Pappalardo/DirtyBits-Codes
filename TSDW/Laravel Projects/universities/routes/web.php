<?php

use App\Http\Controllers\UniversityController;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('welcome');
});

Route::resource('/universities', UniversityController::class);

Route::get('/students/create/{university}', '\App\Http\Controllers\StudentController@create');

Route::post('/students/{student}', '\App\Http\Controllers\StudentController@store');

Route::delete('/students/{student}-{university_id}', '\App\Http\Controllers\StudentController@destroy');
