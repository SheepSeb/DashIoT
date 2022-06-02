package com.mtdl.pooapp.sensor

class SensorFactory {
    fun createSensor(type: String) : Sensor? {
        return when(type) {
            SensorType.humidity.name -> HumiditySensor()
            SensorType.light.name -> LightSensor()
            SensorType.temperature.name -> TemperatureSensor()
            else -> null
        }
    }
    fun createSensor(type: SensorType) : Sensor? {
        return when(type) {
            SensorType.humidity -> HumiditySensor()
            SensorType.light -> LightSensor()
            SensorType.temperature -> TemperatureSensor()
            else -> null
        }
    }



}
