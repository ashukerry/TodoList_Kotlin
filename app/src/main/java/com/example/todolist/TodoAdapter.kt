package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
// this class will contain the main logic of our todo
private lateinit var tvTodoTiltle : TextView
private lateinit var cbDone: CheckBox


class TodoAdapter (private val todos:MutableList<Todo>): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){
    class TodoViewHolder(itemView:View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        // this function will create our todo view holder
        return  TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,parent,
                false
            )
        )
    }
    // this funct adds todo to our list
    fun addTodo(todo:Todo){
        todos.add(todo)
        notifyItemInserted(todos.size-1)
    }
    // function to delete todo
    fun deleteDoneTodos(){
        todos.removeAll{
            todo ->
            todo.isChecked
        }

        notifyDataSetChanged()
    }

    private  fun toggleStrikeThrough(tvTodoTiltle:TextView,isChecked:Boolean){
        if(isChecked){
            tvTodoTiltle.paintFlags=tvTodoTiltle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }else{
            tvTodoTiltle.paintFlags=tvTodoTiltle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        // it will bind the data from our todo list to  the views of our list , it will just be called when a new view holder is visible
       val curTodo=todos[position]
        holder.itemView.apply {
            tvTodoTiltle.text=curTodo.title
            cbDone.isChecked=curTodo.isChecked
            toggleStrikeThrough(tvTodoTiltle, curTodo.isChecked,)
            // the function bellow is a lambda function
            cbDone.setOnCheckedChangeListener{_, isChecked ->

                toggleStrikeThrough(tvTodoTiltle, isChecked)
                curTodo.isChecked=!curTodo.isChecked
            }


        }
    }

    override fun getItemCount(): Int {
        // it just returns the amount of items we have in our list
        return  todos.size
    }
}