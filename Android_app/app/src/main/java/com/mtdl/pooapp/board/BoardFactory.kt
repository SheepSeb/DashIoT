package com.mtdl.pooapp.board

import com.mtdl.pooapp.sensor.Sensor

class BoardFactory {
    fun createBoard(type: BoardType) : Board? {
        return when(type) {
            BoardType.Arduino -> ArduinoBoard()
            BoardType.Microbit -> MicrobitBoard()
            BoardType.RaspberryPi -> RaspberryPiBoard()
        }
    }
}
