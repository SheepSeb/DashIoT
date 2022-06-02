package com.mtdl.pooapp

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
//import com.google.gson.Gson
import com.mtdl.pooapp.board.Board
import com.mtdl.pooapp.sensor.Sensor
import com.mtdl.pooapp.sensor.SensorFactory
import com.mtdl.pooapp.sensor.SensorType
import com.mtdl.pooapp.user.User

class ViewMyBoardsActivity : AppCompatActivity() {
    var dbRef = FirebaseDatabase.getInstance().reference.database.reference
    var data : ArrayList<Board> = ArrayList()
    //val gson = Gson()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_my_boards)

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        recyclerview.layoutManager = LinearLayoutManager(this)

        dbRef.child("Users").child("1").child("boardList").get().addOnSuccessListener {
            Log.i("firebase", "Got value ${it.value}")
            for(k in 0..it.childrenCount.toInt()) {
                for (i in 0..it.child(k.toString()).childrenCount.toInt() - 1) {
                    var sensorList = ArrayList<Sensor>()
                    val alias = it.child(k.toString()).child(i.toString()).child("alias").value
                    val batteryLevel = it.child(k.toString()).child(i.toString()).child("batteryLevel").value
                    val connexionType = it.child(k.toString()).child(i.toString()).child("connexionType").value
                    val sensors = it.child(k.toString()).child(i.toString()).child("sensorList")
                    val board = Board()
                    board.alias = alias.toString()
                    board.batteryLevel = batteryLevel.toString().toInt()
                    board.connexionType = connexionType.toString()
                    Log.d("Battery level", batteryLevel.toString())
                    for (j in 0..sensors.childrenCount.toInt() - 1) {
                        val sensorType = sensors.child(j.toString()).child("type").value
                        val sensorValue = sensors.child(j.toString()).child("reading").value
                        val sf = SensorFactory()
                        var sensor = sf.createSensor(sensorType.toString())
                        Log.d("Sesnor type", sensorType.toString())
                        //sensor!!.reading = sensorValue.value.toString().toDouble()
                        sensorList.add(sensor!!)
                    }

                    board.sensorList = sensorList


                    Log.d("Name", alias.toString())
                    data!!.add(board)


                }
            }
            val adapter = BoardAdapter(data!!)
            recyclerview.adapter = adapter

        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }



    }
}