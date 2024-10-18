package com.example.foodorderingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.foodorderingapp.databinding.ActivityLoginBinding
import com.example.foodorderingapp.databinding.ActivitySignUpBinding
import com.example.foodorderingapp.model.User
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.examples.realmmigrationexample.model.Migration
import java.util.UUID

class SignUpActivity : AppCompatActivity() {
    private lateinit var realm: Realm

    private val binding: ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Realm.init(this)
        realm = Realm.getDefaultInstance()

        binding.alreadyHaveButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.createAccount.setOnClickListener {
            val name = binding.nameRegistration.text.toString()
            val email = binding.emailRegistration.text.toString()
            val password = binding.passwordRegistration.text.toString()

            registerUser(name, email, password)
        }
    }

    private fun registerUser(username: String, email: String, password: String) {
        // Ensure username and password are not empty
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || !isValidEmail(email)) {
            Toast.makeText(this, "Invalid username email or password", Toast.LENGTH_SHORT).show()
            return
        }

        // Check if the user already exists
        val existingUser = realm.where(User::class.java).equalTo("userEmail", email).findFirst()

        if (existingUser != null) {
            Toast.makeText(this, "Username already exists!", Toast.LENGTH_LONG).show()
        } else {
            // Register the user in Realm
            realm.executeTransactionAsync {
                val newUser = it.createObject(User::class.java, UUID.randomUUID().toString())
                newUser.userName = username
                newUser.userEmail = email
                newUser.userPassword = password
                newUser.userType = "customer"

                Handler(Looper.getMainLooper()).post {
                    Toast.makeText(this, "User registered successfully!", Toast.LENGTH_LONG).show()
                     binding.nameRegistration.setText("")
                     binding.emailRegistration.setText("")
                     binding.passwordRegistration.setText("")
                }
                //back to login activity
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }

    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}