package com.mtdl.pooapp.sensor

import android.util.Log

class LightSensor() : Sensor() {
    override var reading  : Double = 0.0
    override var type : SensorType? = SensorType.light
    override fun displayReading(): Double {
        Log.d("Light", reading.toString())
        return reading
    }

    override fun updateReading(new_reading: Double) {
        reading = new_reading
    }

}