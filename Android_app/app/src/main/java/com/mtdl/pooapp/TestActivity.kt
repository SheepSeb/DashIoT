package com.mtdl.pooapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mtdl.pooapp.board.BoardFactory
import com.mtdl.pooapp.board.BoardType
import com.mtdl.pooapp.board.MicrobitBoard
import com.mtdl.pooapp.sensor.LightSensor
import com.mtdl.pooapp.sensor.Sensor
import com.mtdl.pooapp.sensor.SensorFactory
import com.mtdl.pooapp.sensor.SensorType

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        var sf : SensorFactory = SensorFactory()
        var lightSensor : Sensor? = sf.createSensor(SensorType.light.name)
        /*lightSensor?.displayReading()
        lightSensor?.updateReading(12.5)
        lightSensor?.displayReading()*/

        var bf = BoardFactory()
       var microbit = bf.createBoard(BoardType.Microbit.name)
        //microbit?.createSensorList()
        microbit!!.sensorList.get(0).updateReading(20.0)
        microbit?.getReadingForSensor(microbit!!.sensorList.get(0))
    }
}