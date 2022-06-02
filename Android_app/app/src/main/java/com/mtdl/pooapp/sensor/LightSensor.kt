package com.mtdl.pooapp.sensor

import android.util.Log
import kotlin.math.round
import kotlin.random.Random

class LightSensor() : Sensor() {
    override var reading  : Double = round(Random.nextDouble()*200*100)/100
    override var type : SensorType? = SensorType.light
    override fun displayReading(): Double {
        Log.d("Light", reading.toString())
        return reading
    }

    override fun updateReading(new_reading: Double) {
        reading = new_reading
    }

}