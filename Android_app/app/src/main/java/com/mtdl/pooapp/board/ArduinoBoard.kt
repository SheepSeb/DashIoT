package com.mtdl.pooapp.board

import com.mtdl.pooapp.sensor.Sensor
import com.mtdl.pooapp.sensor.SensorFactory
import com.mtdl.pooapp.sensor.SensorType

class ArduinoBoard : Board() {
    override var alias : String = "MyArduino"
    override var sensorList : ArrayList<Sensor> = ArrayList(3)
    override var connexionType: String = ConnexionType.WiFi.name
    override var batteryLevel : Int = 100
    override var latLng: Triple<Double, Double, Double> = Triple(0.0,0.0,0.0);


    override fun addSensor(s: Sensor) {
        sensorList.add(s)
    }

    override fun getReadingForSensor(s: Sensor): Double {
        return s.displayReading()
    }

    override fun createSensorList() {
       val sf = SensorFactory()
        val tempSensor = sf.createSensor(SensorType.temperature.name)
        addSensor(tempSensor!!)
        val humiditySensor = sf.createSensor(SensorType.humidity.name)
        addSensor(humiditySensor!!)
        val lightSensor = sf.createSensor(SensorType.light.name)
        addSensor(lightSensor!!)
    }
    init {
        createSensorList()
    }
}