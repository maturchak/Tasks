package com.example.tasks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var editTextTask: EditText
    private lateinit var buttonAdd: Button
    private lateinit var buttonDeleteAll: Button // Добавляем переменную для новой кнопки
    private lateinit var listViewTasks: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private val taskList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextTask = findViewById(R.id.editTextTask)
        buttonAdd = findViewById(R.id.buttonAdd)
        buttonDeleteAll = findViewById(R.id.buttonDeleteAll) // Получаем ссылку на кнопку
        listViewTasks = findViewById(R.id.listViewTasks)


        adapter = ArrayAdapter(this, R.layout.my_list_item, taskList)
        listViewTasks.adapter = adapter


        buttonAdd.setOnClickListener {
            val task = editTextTask.text.toString()
            if (task.isNotEmpty()) {
                taskList.add(task)
                adapter.notifyDataSetChanged()
                editTextTask.text.clear()
                Toast.makeText(this, "Задача добавлена", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Введите текст задачи", Toast.LENGTH_SHORT).show()
            }
        }


        buttonDeleteAll.setOnClickListener {
            if (taskList.isNotEmpty()) {
                taskList.clear()
                adapter.notifyDataSetChanged()
                Toast.makeText(this, "Все задачи удалены", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Список задач пуст", Toast.LENGTH_SHORT).show()
            }
        }


        listViewTasks.setOnItemLongClickListener { parent, view, position, id ->
            taskList.removeAt(position)
            adapter.notifyDataSetChanged()
            Toast.makeText(this, "Задача удалена", Toast.LENGTH_SHORT).show()
            true
        }
    }
}