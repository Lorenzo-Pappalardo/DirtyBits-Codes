<?php

namespace App\Http\Controllers;

use App\Models\Student;
use App\Models\University;
use Brick\Math\BigInteger;
use Illuminate\Http\Request;

class StudentController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        //
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create($university)
    {
        return view('students.create', compact('university'));
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request, $university_id)
    {
        $student = new Student();
        $student->university_id = $university_id;
        $student->full_name = $request->input('full_name');
        $student->current_year = $request->input('current_year');
        $student->save();
        $university = University::find($university_id);
        $university->studentsNum += 1;
        $university->save();
        return view('universities.show', compact('university'));
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Models\Student  $student
     * @return \Illuminate\Http\Response
     */
    public function show(Student $student)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\Models\Student  $student
     * @return \Illuminate\Http\Response
     */
    public function edit(Student $student)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Models\Student  $student
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, Student $student)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\Student  $student
     * @return \Illuminate\Http\Response
     */
    public function destroy(Student $student, $university_id)
    {
        $student->delete();
        $university = University::find($university_id);
        $university->studentsNum -= 1;
        $university->save();
        return view('universities.show', compact('university'));
    }
}
