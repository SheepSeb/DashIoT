package com.mtdl.pooapp

import android.hardware.Sensor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mtdl.pooapp.board.Board

class BoardAdapter(private val mList: ArrayList<Board>) : RecyclerView.Adapter<BoardAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = mList[position]

        // sets the text to the textview from our itemHolder class
        holder.aliasTextView.text = ItemsViewModel.alias
        holder.batteryLevelTextView.text = "Battery level: " + ItemsViewModel.batteryLevel.toString()
        holder.connexionTypeTextView.text = "Connexion type: " + ItemsViewModel.connexionType
        var sensorsText : String = ""
        for (sensor in ItemsViewModel.sensorList) {
            sensorsText += sensor.type!!.name + ": " + sensor.reading + "\n"
        }
        holder.sensorsTextView.text = sensorsText

    }

    override fun getItemCount(): Int {
        return mList.size
    }
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val aliasTextView: TextView = itemView.findViewById(R.id.alias_tv)
        val connexionTypeTextView : TextView = itemView.findViewById(R.id.connexion_type)
        val batteryLevelTextView : TextView = itemView.findViewById(R.id.battery_level)
        val sensorsTextView : TextView = itemView.findViewById(R.id.sensor_tv)
    }

}



