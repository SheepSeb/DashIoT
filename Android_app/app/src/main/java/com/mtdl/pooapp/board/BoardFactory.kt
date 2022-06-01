package com.mtdl.pooapp.board

import com.mtdl.pooapp.sensor.Sensor

class BoardFactory {
    fun createBoard(type: String) : Board? {
        return when(type) {
            BoardType.Arduino.name -> ArduinoBoard()
            BoardType.Microbit.name -> MicrobitBoard()
            BoardType.RaspberryPi.name -> RaspberryPiBoard()
            else -> null
        }
    }
}
