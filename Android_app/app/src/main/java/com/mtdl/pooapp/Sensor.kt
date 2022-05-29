package com.mtdl.pooapp

class Sensor {
    private var reading: Float = 0f;
    private var maxValue : Float = 0f;
    private var minValue : Float = 100f;

    constructor(maxValue: Float, minValue: Float) {
        this.maxValue = maxValue
        this.minValue = minValue
    }

    fun displayReading():Float{
        return reading;
    }

    fun updateReading(new_reading : Float){
        reading = new_reading
    }


}