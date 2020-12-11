package ise308.sahnaoglu.ogulcan.notetoogulcansahnaoglu


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
//this program can be input some of your note and show and delete their notes if you clicked. but not connect with database
    //maın of program start adapters etc.
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(mutableListOf())
        notelist.adapter = todoAdapter
        notelist.layoutManager = LinearLayoutManager(this)

        //add button for input the words which are you write and clean text area
        addtolist.setOnClickListener {
            val todoTitle: String = content.text.toString()
            if (todoTitle.isNotEmpty()) {

                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                content.text.clear()
            }
        }
        //delete ıtem button calls adapter for delete ıtem
        deleteitems.setOnClickListener {
            todoAdapter.deleteDoneItems()
        }

        }
    }

