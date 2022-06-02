package com.mtdl.pooapp.board

import com.mtdl.pooapp.sensor.Sensor
import com.mtdl.pooapp.sensor.SensorFactory
import com.mtdl.pooapp.sensor.SensorType

open class Board() {
    companion object {
        var id: Int = 0
    }
    open  var alias : String = ""
    open  var sensorList : ArrayList<Sensor> = ArrayList()
    open  var connexionType: String = ConnexionType.WiFi.name
     open var batteryLevel : Int = 100
     open  var latLng: Triple<Double, Double, Double> = Triple(0.0,0.0,0.0)
     open fun addSensor(s: Sensor) {}
     open fun addSensors() {
        sensorList.add(SensorFactory().createSensor(SensorType.humidity)!!);
        sensorList.add(SensorFactory().createSensor(SensorType.temperature)!!);
        sensorList.add(SensorFactory().createSensor(SensorType.light)!!);
    }
     open fun getReadingForSensor(s: Sensor):Double {return 0.0}
    open fun getReadingForSensorType(s: SensorType):Double {
        when(s){
            SensorType.humidity -> return sensorList[0].displayReading()
            SensorType.temperature -> return sensorList[1].displayReading()
            SensorType.light -> return sensorList[2].displayReading()
        }
    }
     open fun createSensorList() : Unit {}

}