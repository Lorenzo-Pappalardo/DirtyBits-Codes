<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use \App\Models\Project;

class ProjectsController extends Controller
{
    public function index()
    {
        $allprojects = Project::all();
        return view(
            'projects.index',
            ['progetti' => $allprojects]
        );
    }

    public function create()
    {
        return view('projects.create');
    }

    public function store(Request $request) // l'argomento $request in
    {                                       // realta` e` inutilizzato
        Project::create([
            'title' => request('title'),
            'description' => request('description')
        ]);
        return redirect('/projects');
    }

    //  public function show($id)
    public function show(Project $project)  // model binding dell'argomento
    {
        //      $project = Project::find($id);
        return view('projects.show', compact('project'));
    }

    public function edit($id)
    {
        $project = Project::findOrFail($id);
        return view('projects.edit', compact('project'));
    }

    public function update($id)
    {
        $project = Project::find($id);
        $project->title = request('title');
        $project->description = request('description');
        $project->save();
        return redirect('/projects');
    }

    public function destroy(Project $project)
    {
        $project->delete();
        return redirect('/projects');
    }
}
