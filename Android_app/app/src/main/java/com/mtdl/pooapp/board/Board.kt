package com.mtdl.pooapp.board

import com.mtdl.pooapp.sensor.Sensor

open class Board() {
    companion object {
        var id: Int = 0
    }
    open lateinit var alias : String
    open lateinit var sensorList : ArrayList<Sensor>
    open lateinit var connexionType: ConnexionType
     open var batteryLevel : Int = 100
     open fun addSensor(s: Sensor) {}
     open fun getReadingForSensor(s: Sensor):Double {return 0.0}
     open fun createSensorList() : Unit {}
}