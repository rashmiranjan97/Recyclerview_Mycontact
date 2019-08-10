package com.example.attempt1

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import java.util.ArrayList
import kotlinx.android.synthetic.main.activity_main.*

import kotlinx.android.synthetic.main.cutsom_layout.*
import kotlinx.android.synthetic.main.cutsom_layout.view.*



class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<View>(R.id.recycleView) as RecyclerView





        var myAdapter: Myadapter
        var namelist: ArrayList<String>
        var numlist: ArrayList<String>





        fab.setOnClickListener {


            val DialogView = LayoutInflater.from(this).inflate(R.layout.cutsom_layout, null)
            val Builder = AlertDialog.Builder(this)
                .setView(DialogView)
                .setTitle("ADD CONTACT")
            val AlertDialog = Builder.show()

            val name = findViewById<EditText>(R.id.name1)
            val number = findViewById<EditText>(R.id.number1)



            namelist = ArrayList(10)
            numlist = ArrayList(10)



            DialogView.save.setOnClickListener {




                myAdapter = Myadapter(namelist, numlist)
                recyclerView.setHasFixedSize(true)
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = myAdapter
                namelist.add(name?.text.toString())
                numlist.add(number?.text.toString())

                AlertDialog.dismiss()

                name?.setText("")
                number?.setText("")



            }

        }
    }

}

private class Myadapter(internal var namelist: ArrayList<String>, internal var numlist: ArrayList<String>) :
    RecyclerView.Adapter<Myadapter.NameHolder>() {

    override fun getItemCount(): Int {
        return namelist.size
        return numlist.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.contacts, null)
        return NameHolder(v)
    }

    override fun onBindViewHolder(holder: NameHolder, position: Int) {
        val nm = namelist[position]
        val nmb = numlist[position]
        holder.nmt.text = nm
        holder.nmbt.text = nmb
    }

    internal inner class NameHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nmt: TextView = itemView.findViewById<View>(R.id.myname) as TextView
        var nmbt: TextView = itemView.findViewById<View>(R.id.mynumber) as TextView

    }
}

