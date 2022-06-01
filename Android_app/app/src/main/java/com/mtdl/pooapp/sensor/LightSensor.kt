package com.mtdl.pooapp.sensor

import android.util.Log

class LightSensor(private var reading  : Double) : Sensor {
    override fun displayReading(): Double {
        Log.d("Light", reading.toString())
        return reading
    }

    override fun updateReading(new_reading: Double) {
        reading = new_reading
    }

}