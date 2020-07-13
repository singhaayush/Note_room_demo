package com.example.roomdemo.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(var title:String,var body:String )
{

        @PrimaryKey(autoGenerate = true)
        var id:Int=0


}