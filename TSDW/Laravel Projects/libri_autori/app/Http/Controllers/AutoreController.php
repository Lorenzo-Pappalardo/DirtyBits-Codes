<?php

namespace App\Http\Controllers;

use App\Models\Autore;
use App\Models\Libro;
use Illuminate\Http\Request;

class AutoreController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        return view('autore.index', ['autori' => Autore::all()]);
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        return view('autore.create');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $autore = new Autore();
        $autore->nome = $request->input('nome');
        $autore->eta = $request->input('eta');
        $autore->save();
        return $this->index();
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Models\Autore  $autore
     * @return \Illuminate\Http\Response
     */
    public function show(Autore $autore)
    {
        $libri = Libro::all()->where('autore_id', '=', $autore->id);
        return view('autore.show', compact('autore'), compact('libri'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\Models\Autore  $autore
     * @return \Illuminate\Http\Response
     */
    public function edit(Autore $autore)
    {
        return view('autore.edit', compact('autore'));
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Models\Autore  $autore
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, Autore $autore)
    {
        $autore->nome = $request->input('nome');
        $autore->eta = $request->input('eta');
        $autore->save();
        return $this->show($autore);
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\Autore  $autore
     * @return \Illuminate\Http\Response
     */
    public function destroy(Autore $autore)
    {
        $autore->delete();
        return $this->index();
    }
}
