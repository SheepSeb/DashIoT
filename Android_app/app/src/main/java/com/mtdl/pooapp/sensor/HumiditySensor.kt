package com.mtdl.pooapp.sensor

import android.util.Log
import kotlin.math.round
import kotlin.random.Random

class HumiditySensor() : Sensor() {
     override var reading : Double = round(Random.nextDouble()*50*100)/100
    override var type : SensorType? = SensorType.humidity
    override fun displayReading(): Double {
        Log.d("Humidity", reading.toString())
        return reading
    }

    override fun updateReading(new_reading: Double) {
        reading = new_reading
    }
}