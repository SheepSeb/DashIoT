package com.mtdl.pooapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class AddDeviceActivity: AppCompatActivity() {

    class StandardBoard : BoardFactory {
        override fun createBoardWithName(name: String): Board {
            TODO("Not yet implemented")
        }

        override fun createBoardWithSensors(sensors: MutableList<Sensor>): Board {
            TODO("Not yet implemented")
        }

        override fun createBoardWithType(sensors: MutableList<Sensor>): Board {
            TODO("Not yet implemented")
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    public override fun onStart() {
        super.onStart()

    }


}