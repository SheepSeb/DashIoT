package com.mtdl.pooapp

interface SensorFactory {
    fun createSensorWithName(name: String) : Sensor
    fun createSensorWithType(type: SensorType) : Sensor
}
