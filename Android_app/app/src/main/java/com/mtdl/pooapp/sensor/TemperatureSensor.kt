package com.mtdl.pooapp.sensor

import android.util.Log

class TemperatureSensor() : Sensor() {
    override var reading : Double = 0.0
    override var type : SensorType? = SensorType.temperature
    override fun displayReading(): Double {
        Log.d("Temperature", reading.toString())
       return reading
    }

    override fun updateReading(new_reading: Double) {
        reading = new_reading
    }

    fun changeCelsiusToFahrenheit() : Double {
        reading = (reading * 1.8) + 32
        return reading
    }
}