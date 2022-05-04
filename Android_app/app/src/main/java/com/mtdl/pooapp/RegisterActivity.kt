package com.mtdl.pooapp

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity: AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    private var registerBtn: Button? = null;
    private var emailTextInputEditText: TextInputLayout? = null;
    private var pwTextInputEditText: TextInputLayout? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_layout)
        mAuth = FirebaseAuth.getInstance()

        emailTextInputEditText = findViewById(R.id.register_email_text_field)
        pwTextInputEditText = findViewById(R.id.register_pw_text_field)

        registerBtn = findViewById(R.id.register_btn)
        registerBtn!!.setOnClickListener {
            val emailTxt = emailTextInputEditText!!.editText!!.text.toString()
            val pwText = pwTextInputEditText!!.editText!!.text.toString()
            Log.d("email", emailTxt)
            Log.d("pw", pwText)
            createAccount(emailTxt, pwText)
        }


    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth!!.currentUser
        if (currentUser != null) {
            // reload();
        }
    }

    fun createAccount(email: String, pw: String) {
        mAuth!!.createUserWithEmailAndPassword(email, pw)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(ContentValues.TAG, "createAccount:success")
                    val user = mAuth!!.currentUser
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(ContentValues.TAG, "createAccount:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    // updateUI(null)
                }
            }
    }
}