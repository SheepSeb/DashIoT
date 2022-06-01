package com.mtdl.pooapp.board

import com.mtdl.pooapp.sensor.Sensor

interface Board {
    var id : Int
    var alias : String
    var sensorList : ArrayList<Sensor>
    var connexionType: ConnexionType
    var batteryLevel : Int
    fun addSensor(s: Sensor)
    fun getReadingForSensor(s: Sensor):Double
    fun createSensorList() : Unit
}