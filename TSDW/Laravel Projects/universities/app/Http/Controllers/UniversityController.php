<?php

namespace App\Http\Controllers;

use App\Models\University;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class UniversityController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        return view('universities.index', ['universities' => University::all()]);
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        return view('universities.create');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $university = new University();
        $university->name = $request->input('name');
        $university->save();
        return $this->index();
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Models\University  $university
     * @return \Illuminate\Http\Response
     */
    public function show(University $university)
    {
        $studentsNum = DB::table('students')->where('university_id', '=', $university->id)->count();
        return view('universities.show', compact('university'), compact('studentsNum'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\Models\University  $university
     * @return \Illuminate\Http\Response
     */
    public function edit(University $university)
    {
        $studentsNum = DB::table('students')->where('university_id', '=', $university->id)->count();
        return view('universities.edit', compact('university'), compact('studentsNum'));
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Models\University  $university
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, University $university)
    {
        $university->name = $request->input('name');
        if ($request->input('studentsNum') != null) {
            $university->studentsNum = $request->input('studentsNum');
        } else {
            $university->studentsNum = 0;
        }
        $university->save();
        return $this->index();
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\University  $university
     * @return \Illuminate\Http\Response
     */
    public function destroy(University $university)
    {
        $university->students()->each(function ($student) {
            $student->delete();
        });
        $university->delete();
        return $this->index();
    }
}
