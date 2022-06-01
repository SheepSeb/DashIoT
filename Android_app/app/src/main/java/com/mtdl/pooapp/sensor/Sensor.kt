package com.mtdl.pooapp.sensor

open class Sensor {
    open var reading : Double = 0.0
    open fun displayReading():Double {return 0.0}

    open fun updateReading(new_reading : Double) {}


}