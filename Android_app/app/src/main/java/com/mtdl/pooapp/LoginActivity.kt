package com.mtdl.pooapp
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class LoginActivity: AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    private var loginBtn: Button? = null;
    private var emailTextInputEditText: TextInputLayout? = null;
    private var pwTextInputEditText: TextInputLayout? = null;
    private var registerTextView: TextView? = null;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)
        FirebaseApp.initializeApp(applicationContext)
        mAuth = FirebaseAuth.getInstance()

        emailTextInputEditText = findViewById(R.id.email_text_field)
        pwTextInputEditText = findViewById(R.id.pw_text_field)


        loginBtn = findViewById(R.id.logint_btn)
        loginBtn!!.setOnClickListener {
            val emailTxt = emailTextInputEditText!!.editText!!.text.toString()
            val pwText = pwTextInputEditText!!.editText!!.text.toString()
            Log.d("email", emailTxt)
            Log.d("pw", pwText)
            signIn(emailTxt, pwText)
        }

        registerTextView = findViewById(R.id.register)
        registerTextView!!.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
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

    private fun signIn(email: String, pw: String) {
        mAuth!!.signInWithEmailAndPassword(email, pw)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInUserWithEmail:success")
                    val user = mAuth!!.currentUser

                    if (user != null) {
                        User.userInstance().setFirebaseInstance(user)

                    };

                    val intent = Intent(this@LoginActivity, MapsActivity::class.java)
                    startActivity(intent)

                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signIneUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Login failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    // updateUI(null)
                }
            }

    }


//
//        fun onclick(view: View) {
//            login(
//                findViewById<TextInputEditText>(R.id.edtUserName).text.toString(),
//                findViewById<TextInputEditText>(R.id.edtPassword).text.toString()
//            )
//        }
//
//        fun login(userName: String, password: String) {
//            if (userName == "name" && password == "1234") {
//                startActivity(Intent(this, HomeActivity::class.java))
//                Toast.makeText(this, "Login Success!", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show()
//            }
//        }
    }
