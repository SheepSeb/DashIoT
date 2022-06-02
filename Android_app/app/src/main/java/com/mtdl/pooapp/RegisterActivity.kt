package com.mtdl.pooapp

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.mtdl.pooapp.board.Board
import com.mtdl.pooapp.board.BoardFactory
import com.mtdl.pooapp.board.BoardType
import com.mtdl.pooapp.user.User
import com.mtdl.pooapp.user.UserController
import com.mtdl.pooapp.utils.DatabaseRef

class RegisterActivity: AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    private var registerBtn: Button? = null
    private var emailTextInputEditText: TextInputLayout? = null
    private var pwTextInputEditText: TextInputLayout? = null
    private var firstNameEditText : TextInputLayout? = null
    private var lastNameEditText : TextInputLayout? = null
    private var uc : UserController = UserController()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_layout)
        DatabaseRef.database = FirebaseDatabase.getInstance().reference.database.reference
        mAuth = FirebaseAuth.getInstance()

        emailTextInputEditText = findViewById(R.id.register_type)
        pwTextInputEditText = findViewById(R.id.register_pw_text_field)
        firstNameEditText = findViewById(R.id.register_first_name_text_field)
        lastNameEditText = findViewById(R.id.register_last_name_text_field)

        registerBtn = findViewById(R.id.register_btn)
        registerBtn!!.setOnClickListener {
            val emailTxt = emailTextInputEditText!!.editText!!.text.toString()
            val pwText = pwTextInputEditText!!.editText!!.text.toString()
            val firstNameText = firstNameEditText!!.editText!!.text.toString()
            val lastNameText = lastNameEditText!!.editText!!.text.toString()
            Log.d("email", emailTxt)
            Log.d("pw", pwText)
            createAccount(emailTxt, pwText)
            addUserToFirebase(firstNameText, lastNameText, emailTxt)
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

    fun addUserToFirebase(firstName : String, lastName : String, email : String) {
        var u = User()
        u.firstName = firstName
        u.lastName = lastName
        u.email = email
        User.id++
        uc.user = u
        uc.addUserToDb()
        var bf = BoardFactory()
        var b = bf.createBoard(BoardType.Microbit.name)
        uc.addBoard(2, b)
    }
}