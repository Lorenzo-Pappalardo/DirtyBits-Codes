<?php

namespace App\Http\Controllers;

use App\Models\Computer;
use Illuminate\Http\Request;

class ComputerController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        return view('computer.index', ['computers' => Computer::all()]);
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        return view('computer.create');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $newComputer = new Computer();
        $newComputer->name = $request->input('name');
        $newComputer->save();
        return $this->index();
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Models\Computer  $computer
     * @return \Illuminate\Http\Response
     */
    public function show(Computer $computer)
    {
        return view('computer.show', ['computer' => $computer]);
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\Models\Computer  $computer
     * @return \Illuminate\Http\Response
     */
    public function edit(Computer $computer)
    {
        return view('computer.edit', ['computer' => $computer]);
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Models\Computer  $computer
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, Computer $computer)
    {
        $computer->name = $request->input('name');
        $computer->save();
        return $this->index();
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\Computer  $computer
     * @return \Illuminate\Http\Response
     */
    public function destroy(Computer $computer)
    {
        $computer->delete();
        return $this->index();
    }
}
