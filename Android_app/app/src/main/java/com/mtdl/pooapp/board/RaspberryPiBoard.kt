package com.mtdl.pooapp.board

import com.mtdl.pooapp.sensor.Sensor
import com.mtdl.pooapp.sensor.SensorFactory
import com.mtdl.pooapp.sensor.SensorType

class RaspberryPiBoard : GenericBoard(0, "MyRaspberryPi", ArrayList(2), ConnexionType.Ethernet, 100), Board {
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
}