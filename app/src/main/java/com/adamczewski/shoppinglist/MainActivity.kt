package com.adamczewski.shoppinglist

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    lateinit var listView : ListView;
    lateinit var animation : Animation;
    lateinit var titles : ArrayList<String>;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.list_view);
        val titlesArr = arrayOf("Test1", "Test2", "Test3");
        titles = titlesArr.toMutableList() as ArrayList<String>;

        val adapter = MainAdapter(this, titles);
        animation = AnimationUtils.loadAnimation(this, R.anim.animation1);
        listView.adapter = adapter;

        listView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("title", titles[i])
            startActivity(intent)
        }

        val floatingActionButton = findViewById<FloatingActionButton>(R.id.floating_action_button);

        floatingActionButton.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog(){
        val builder = AlertDialog.Builder(this);
        builder.setTitle("New list")
        val inputText = EditText(this);
        inputText.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(inputText);

        builder.setPositiveButton(
            "OK"
        ) { dialog, which -> titles.add(inputText.getText().toString()) }
        builder.setNegativeButton(
            "Cancel"
        ) { dialog, which -> dialog.cancel() }

        builder.show();
    }
}