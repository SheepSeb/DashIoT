package com.mtdl.pooapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.mtdl.pooapp.databinding.ActivityAddDeviceBinding
import com.mtdl.pooapp.databinding.ActivityRegisterDeviceBinding


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
           addSensors()
       }




    }

    public override fun onStart() {
        super.onStart()
    }

    fun addSensors(){
        //create new board
        ;
    }
}