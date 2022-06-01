package com.mtdl.pooapp.sensor

interface Sensor {

    fun displayReading():Double

    fun updateReading(new_reading : Double)


}