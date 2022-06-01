package com.mtdl.pooapp.board

import com.mtdl.pooapp.sensor.Sensor
import com.mtdl.pooapp.sensor.SensorFactory
import com.mtdl.pooapp.sensor.SensorType

class RaspberryPiBoard :  Board {
    override var id : Int = 0
    override var alias : String = "MyRaspberryPi"
    override var sensorList : ArrayList<Sensor> = ArrayList(2)
    override var connexionType: ConnexionType = ConnexionType.Ethernet
    override var batteryLevel : Int = 100
    override fun addSensor(s: Sensor) {
        sensorList.add(s)
    }

    override fun getReadingForSensor(s: Sensor): Double {
        return  s.displayReading()
    }

    override fun createSensorList() {
        val sf = SensorFactory()
        val humiditySensor = sf.createSensor(SensorType.humidity)
        addSensor(humiditySensor!!)
        val temperatureSensor = sf.createSensor(SensorType.temperature)
        addSensor(temperatureSensor!!)
    }
    init {
        createSensorList()
    }
}