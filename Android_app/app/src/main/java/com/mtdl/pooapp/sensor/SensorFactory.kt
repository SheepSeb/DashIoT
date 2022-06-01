package com.mtdl.pooapp.sensor

class SensorFactory {
    fun createSensor(type: SensorType) : Sensor? {
        return when(type) {
            SensorType.humidity -> HumiditySensor(0.0)
            SensorType.light -> LightSensor(0.0)
            SensorType.temperature -> TemperatureSensor(0.0)
        }
    }



}
