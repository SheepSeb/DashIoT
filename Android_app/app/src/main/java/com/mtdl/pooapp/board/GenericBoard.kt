package com.mtdl.pooapp.board

import com.mtdl.pooapp.sensor.Sensor

open class GenericBoard(var id : Int, var alias : String, var sensorList : ArrayList<Sensor>, var connexionType: ConnexionType, var batteryLevel : Int) {

}