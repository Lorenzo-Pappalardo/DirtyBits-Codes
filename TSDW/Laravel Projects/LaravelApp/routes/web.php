<?php

use App\Http\Controllers\GameController;
use App\Http\Controllers\GamesController;
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

Route::get('/new_view', function () {
    return view('new_view');
});

Route::get('/json', function () {
    return (['name' => 'Lorenzo', 'email' => 'lorenzopappalardo7@gmail.com']);
});

Route::view('/alt', 'welcome');

Route::redirect('/redirect', '/alt');

Route::get('/parameters/{name}_{email}', function ($username, $email) {
    return '<h1>Username: ' . $username . '<br>Email: ' . $email . '</h1>';
});

Route::get('/parameters/{name?}_{email?}', function ($username = 'john.wick', $email = 'john.wick@behindyou.dead') {
    return '<h1>Username: ' . $username . '<br>Email: ' . $email . '</h1>';
});

Route::get('/greetings/{name}_{age}', function ($name, $age) {
    return '<h1>My name is ' . $name . ', and I\'m ' . $age . '!';
})->where(['name' => '[A-Za-z]+', 'age' => '[0-9]+']);

Route::get('/greetings/alt/{name}_{age}', function ($name, $age) {
    return '<h1>My name is ' . $name . ', and I\'m ' . $age . '!';
})->whereAlpha('name')->whereNumber('age');

Route::get('/this/is/too/big/to/write/please/help', function () {
    return view('paramToView');
})->name('paramToView');

Route::prefix('short')->group(function () {
    Route::get('/paramToView', function () {
        return redirect()->route('paramToView');
    });
});

/* Route::fallback(function () {
    $bg = '<style>body{background-color: darkblue}</style><br>';
    return $bg . '<h1 style="color: red;text-align: center";>ROUTE NOT FOUND</h1>';
}); */


Route::get('/variable/{passedVariable}', function ($passedVariable) {
    return view('passingVariables', ['passedVariable' => $passedVariable]);
});

Route::get('/variables/{name}_{surname}', function ($name, $surname) {
    $variables = ['name' => $name, 'surname' => $surname];
    return view('moreVariables', $variables);
})->whereAlpha('name')->whereAlpha('name');

Route::get('/urlVariables', function () {
    return view('urlVariables', ['urlVariable' => request('num')]);
});

Route::resource('games', GamesController::class);
