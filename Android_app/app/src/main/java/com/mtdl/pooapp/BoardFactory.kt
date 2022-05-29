package com.mtdl.pooapp

interface BoardFactory {
    fun createBoardWithName(name: String) : Board
    fun createBoardWithSensors(sensors:  MutableList<Sensor>) : Board
    fun createBoardWithType(sensors:  MutableList<Sensor>) : Board
}
