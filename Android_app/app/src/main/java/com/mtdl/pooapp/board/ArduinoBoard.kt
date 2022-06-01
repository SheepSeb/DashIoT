package com.mtdl.pooapp.board

import com.mtdl.pooapp.sensor.Sensor
import com.mtdl.pooapp.sensor.SensorFactory
import com.mtdl.pooapp.sensor.SensorType

class ArduinoBoard : GenericBoard(0, "MyArduino", ArrayList(3), ConnexionType.WiFi, 100), Board {
    override fun addSensor(s: Sensor) {
        sensorList.add(s)
    }

    override fun getReadingForSensor(s: Sensor): Double {
        return s.displayReading()
    }

    override fun createSensorList() {
       val sf = SensorFactory()
        val tempSensor = sf.createSensor(SensorType.temperature)
        addSensor(tempSensor!!)
        val humiditySensor = sf.createSensor(SensorType.humidity)
        addSensor(humiditySensor!!)
        val lightSensor = sf.createSensor(SensorType.light)
        addSensor(lightSensor!!)
    }
}