package com.mtdl.pooapp.sensor

import android.util.Log

class HumiditySensor() : Sensor() {
     override var reading : Double = 0.0
    override fun displayReading(): Double {
        Log.d("Humidity", reading.toString())
        return reading
    }

    override fun updateReading(new_reading: Double) {
        reading = new_reading
    }
}