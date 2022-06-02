package com.mtdl.pooapp.sensor
import kotlin.math.round
import android.util.Log
import kotlin.random.Random

class TemperatureSensor() : Sensor() {
    override var reading : Double = round(Random.nextDouble()*30*100)/100
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