package com.mtdl.pooapp.sensor

import android.util.Log

class HumiditySensor(private var reading : Double) : Sensor {
    override fun displayReading(): Double {
        Log.d("Humidity", reading.toString())
        return reading
    }

    override fun updateReading(new_reading: Double) {
        reading = new_reading
    }
}