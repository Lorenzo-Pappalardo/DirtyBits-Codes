<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Computer extends Model
{
    use HasFactory;

    public function processor()
    {
        $this->hasOne(Processor::class);
    }

    public function motherboard()
    {
        $this->hasOne(Motherboard::class);
    }

    public function memories()
    {
        $this->hasMany(Memory::class);
    }

    public function video_cards()
    {
        $this->hasMany(VideoCard::class);
    }
}
