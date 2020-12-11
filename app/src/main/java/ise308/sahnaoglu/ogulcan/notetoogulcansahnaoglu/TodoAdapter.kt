package ise308.sahnaoglu.ogulcan.notetoogulcansahnaoglu

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.typeaddto.view.*

//Todo adapter send data to Todo which datas showed in typeaddto.xml
// (tek tek eklenen veriyi gostermek için Todo classına atıyorlar.)

class TodoAdapter (
    private val todos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.typeaddto,
                parent,
                false
            )
        )
    }

    // insert the words
    fun addTodo(todo: Todo){
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }
    //delete checked notes
    fun deleteDoneItems(){
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    //ticked with mouse button
    private fun toggleStrikeThrough(content: TextView, isChecked: Boolean){
        if (isChecked){
            content.paintFlags = content.paintFlags or STRIKE_THRU_TEXT_FLAG
        }else{
            content.paintFlags = content.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }

    }


    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.itemView.apply {
            content.text = curTodo.title
            checked.isChecked = curTodo.isChecked
            toggleStrikeThrough(content, curTodo.isChecked)
            checked.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(content, isChecked)
                curTodo.isChecked = !curTodo.isChecked
            }
        }
    }

    //calculate data size like array for showing you.
    override fun getItemCount(): Int {
        return todos.size
    }
}