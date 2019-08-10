package com.example.attempt1

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.cutsom_layout.view.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById<View>(R.id.recycleView) as RecyclerView

        var myAdapter: MYAdapter

        val namelist: ArrayList<String> = ArrayList(10)
        val numlist: ArrayList<String> = ArrayList(10)



        fab.setOnClickListener {

//            Log.v("name","fab activyyyyyyyyyyyyyyyyyyyyyy")


            val DialogView = LayoutInflater.from(this).inflate(R.layout.custom_layout, null)
            val Builder = AlertDialog.Builder(this)
                .setView(DialogView)
                .setTitle("ADD CONTACT")
            val AlertDialog = Builder.show()

            val nameet = DialogView.findViewById<EditText>(R.id.nameet)
            val numberet = DialogView.findViewById<EditText>(R.id.numberet)

            myAdapter = MYAdapter(namelist,numlist)
            recyclerView!!.setHasFixedSize(true)
            recyclerView!!.layoutManager = LinearLayoutManager(this)
            recyclerView!!.adapter = myAdapter

            DialogView.save.setOnClickListener {


                namelist.add(nameet?.text.toString())
                numlist.add(numberet?.text.toString())

//                Log.v("EditText","adddeeeeeedddddddddddd"+nameet?.text.toString())

                AlertDialog.dismiss()

                nameet?.setText("")
                numberet?.setText("")



            }

        }
    }

}

private  class MYAdapter(internal var namelist: ArrayList<String>, internal var numlist: ArrayList<String>) :
    RecyclerView.Adapter<MYAdapter.NameHolder>() {
    override fun getItemCount(): Int {
        return namelist.size

    }

    @SuppressLint("InflateParams")
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
//        Log.v("name",""+nm)

    }

    internal inner class NameHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nmt: TextView = itemView.findViewById<View>(R.id.mynametv) as TextView
        var nmbt: TextView = itemView.findViewById<View>(R.id.mynumbertv) as TextView

    }
}

