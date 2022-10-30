package com.example.todolist
//a data class is a class in Kotlin is a classs meant to hold only data and no logic
data class Todo (
    val title:String,
    var isChecked:Boolean=false
)