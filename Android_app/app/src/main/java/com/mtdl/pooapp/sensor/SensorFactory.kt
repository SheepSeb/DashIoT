package com.mtdl.pooapp.sensor

class SensorFactory {
    fun createSensor(type: SensorType) : Sensor? {
        return when(type) {
            SensorType.humidity -> HumiditySensor()
            SensorType.light -> LightSensor()
            SensorType.temperature -> TemperatureSensor()
        }
    }



}
