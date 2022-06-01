package com.mtdl.pooapp.board

import com.mtdl.pooapp.sensor.Sensor

open class Board() {
    companion object {
        var id: Int = 0
    }
    open  var alias : String = ""
    open  var sensorList : ArrayList<Sensor> = ArrayList()
    open  var connexionType: String = ConnexionType.WiFi.name
     open var batteryLevel : Int = 100
     open fun addSensor(s: Sensor) {}
     open fun getReadingForSensor(s: Sensor):Double {return 0.0}
     open fun createSensorList() : Unit {}
}