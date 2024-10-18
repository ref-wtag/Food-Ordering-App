package com.example.foodorderingapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.foodorderingapp.databinding.ActivityLoginBinding
import com.example.foodorderingapp.model.User
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.examples.realmmigrationexample.model.Migration

class LoginActivity : AppCompatActivity() {
    private lateinit var realm: Realm

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Realm.init(this)
        val config1 = RealmConfiguration.Builder()
            .name("default1.realm")
            .schemaVersion(3) // Set the schema version
            .migration(Migration()) // Set the migration class
            .build()

        Realm.setDefaultConfiguration(config1)
        realm = Realm.getDefaultInstance()

        binding.loginButton.setOnClickListener {
            val userEmail = binding.emailTextLogin.text.toString()
            val userPassword = binding.passwordTextLogin.text.toString()
            loginUser(userEmail, userPassword)
        }

        binding.dontHaveButton.setOnClickListener {
            binding.emailTextLogin.setText("")
            binding.passwordTextLogin.setText("")
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser(userEmail: String, userPassword: String) {
        realm.executeTransactionAsync { realm ->
            val user = realm.where(User::class.java)
                .equalTo("userEmail", userEmail)
                .equalTo("userPassword", userPassword) // Check username and password
                .findFirst()

            user?.let {
                if (user.userType == "admin") {
                   // startActivity(Intent(this, AdminDashboardActivity::class.java))
                } else {
                        saveLoginInfo(userEmail)
                        binding.emailTextLogin.setText("")
                        binding.passwordTextLogin.setText("")
                    startActivity(Intent(this, MainActivity::class.java))
                }
            } ?: run {
                // if username or password is incorrect
                Handler(Looper.getMainLooper()).post {
                    Toast.makeText(this, "Invalid username or password!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun saveLoginInfo(email: String) {
        val sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("userEmail", email)
        editor.putLong("loginTimestamp", System.currentTimeMillis())
        editor.putBoolean("isLoggedIn", true)
        editor.apply()
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}