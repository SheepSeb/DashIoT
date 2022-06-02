package com.mtdl.pooapp.board

import android.util.Log
import com.mtdl.pooapp.sensor.Sensor
import com.mtdl.pooapp.sensor.SensorFactory
import com.mtdl.pooapp.sensor.SensorType

class MicrobitBoard() : Board() {
    override var alias : String = "MyMicrobit"
    override var sensorList : ArrayList<Sensor> = ArrayList(2)
    override var connexionType: String = ConnexionType.Bluetooth.name
    override var batteryLevel : Int = 100
    override var latLng: Triple<Double, Double, Double> = Triple(0.0,0.0,0.0);


    override fun addSensor(s: Sensor) {
        sensorList.add(s)
    }

    override fun getReadingForSensor(s: Sensor): Double {
        Log.d("Microbit", "reading value of a sensor from microbit")
        return s.displayReading()
    }

    override fun createSensorList() {
        val sf  = SensorFactory()
        var tempSensor : Sensor? = sf.createSensor(SensorType.temperature.name)
        addSensor(tempSensor!!)
        var lightSensor : Sensor? = sf.createSensor(SensorType.light.name)
        addSensor(lightSensor!!)
    }

    init {
        createSensorList()
    }


}