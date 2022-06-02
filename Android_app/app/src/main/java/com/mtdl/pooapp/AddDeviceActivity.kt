package com.mtdl.pooapp

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.google.android.gms.location.*
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.mtdl.pooapp.board.Board
import com.mtdl.pooapp.board.BoardFactory
import com.mtdl.pooapp.board.BoardType
import com.mtdl.pooapp.sensor.Sensor
import com.mtdl.pooapp.user.User
import com.mtdl.pooapp.user.UserController
import com.mtdl.pooapp.utils.DatabaseRef
class AddDeviceActivity: AppCompatActivity() {
    val boardFactory = BoardFactory()
    val userController = UserController()
    val currentUser = FirebaseAuth.getInstance().currentUser
    var currentUserEmail = ""
    var user = User()
    var key = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_board_layout)
        var boardList : ArrayList<String> = ArrayList()
        boardList.add(BoardType.Microbit.name)
        boardList.add(BoardType.RaspberryPi.name)
        boardList.add(BoardType.Arduino.name)

        val adapter  = ArrayAdapter(this, R.layout.dropdown_list_item, boardList)
        var textField = findViewById<TextInputLayout>(R.id.board_menu)
        (textField.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        currentUserEmail = currentUser?.email.toString()
        var dbRef = FirebaseDatabase.getInstance().reference.database.reference
        dbRef.child("users").child("1").get()
        val addBoardButton = findViewById<Button>(R.id.add_board_btn)
        addBoardButton.setOnClickListener{
            userController.user = user

            var boardFactory = BoardFactory()
            var board = boardFactory.createBoard(textField.editText!!.text.toString())
            userController.addBoard(1, board)
            val message = "Add"
            val intent = Intent(this, MapsActivity::class.java).apply {
                putExtra("EXTRA_MESSAGE", message)
            }
            startActivity(intent)
        }
    }

    public override fun onStart() {
        super.onStart()

    }

}