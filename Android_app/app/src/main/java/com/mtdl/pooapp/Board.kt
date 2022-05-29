package com.mtdl.pooapp

class Board {
    private var id :  Int = 0;
    private var alias : String = ""
    private var sensors: MutableList<Sensor> = mutableListOf()
    private var batteryLevel: Int = 0;
    private var connexionType:ConnexionType = ConnexionType.WiFi;

    fun addSensor(s: Sensor){
        sensors.add(s);
    }
    fun getReadingForSensor(s:Sensor):Float{
        if(sensors.contains(s)){
            return s.displayReading()
        }else{
            return -1f;
        }
    }

    fun setConnexionType(c : ConnexionType){
        connexionType = c;
    }

    fun getBatteryLevel():Int{
        return batteryLevel;
    }


}