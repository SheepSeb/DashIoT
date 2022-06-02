package com.mtdl.pooapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.mtdl.pooapp.board.ArduinoBoard
import com.mtdl.pooapp.board.Board
import com.mtdl.pooapp.board.MicrobitBoard
import com.mtdl.pooapp.board.RaspberryPiBoard
import com.mtdl.pooapp.databinding.ActivityRegisterDeviceBinding
import com.mtdl.pooapp.user.User
import com.mtdl.pooapp.user.UserController


class RegisterDeviceActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityRegisterDeviceBinding

    private var loginBtn: Button? = null;
    private var AliasInputEditText: TextInputLayout? = null;
    private var TypeInputEditText: TextInputLayout? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityRegisterDeviceBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)


        // viewBinding.registerAlias
       viewBinding.registerBtn.setOnClickListener {
           var piBoard : Board = RaspberryPiBoard()
           if(User.userInstance().boardList.size == 1){
               piBoard = ArduinoBoard()
           }
           if(User.userInstance().boardList.size == 2){
               piBoard = MicrobitBoard()
           }
           piBoard.latLng = User.getCurentLocation()
           piBoard.addSensors()//Triple(50.0,50.0,50.0);
           User.userInstance().addBoard(piBoard)

           UserController().addBoard(User.getUserId(),piBoard);
           for(b in User.userInstance().getBoards()){
//               Toast.makeText(this,
//                   "board at " + b.sensorList.toString(),
//                   Toast.LENGTH_SHORT).show()
           }
           returnToMaps()
       }




    }

    public override fun onStart() {
        super.onStart()
    }

    fun addSensors(){
        //create new board
        ;
    }

    fun returnToMaps(){

        val intent = Intent(this, MapsActivity::class.java)
        startActivity(intent)
    }
}