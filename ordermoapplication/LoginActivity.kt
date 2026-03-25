package com.example.ordermoapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ordermoapplication.models.User
import com.example.ordermoapplication.utils.SessionManager

class LoginActivity : AppCompatActivity() {
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvRegisterLink: TextView
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sessionManager = SessionManager(this)

        // Check if already logged in
        if (sessionManager.isLoggedIn()) {
            navigateToDashboard()
            return
        }

        initViews()
        setupListeners()
    }

    private fun initViews() {
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvRegisterLink = findViewById(R.id.tvRegisterLink)
    }

    private fun setupListeners() {
        btnLogin.setOnClickListener { login() }
        tvRegisterLink.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun login() {
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()

        when {
            email.isEmpty() -> {
                etEmail.error = "Email is required"
                return
            }
            password.isEmpty() -> {
                etPassword.error = "Password is required"
                return
            }
            else -> {
                // For demo purposes, accept demo@ordermorph.com / password
                if (email == "demo@ordermorph.com" && password == "password") {
                    val user = User(
                        fullName = "Demo User",
                        email = email,
                        phone = "1234567890",
                        password = password
                    )
                    sessionManager.saveUser(user)
                    navigateToDashboard()
                } else {
                    Toast.makeText(this, "Invalid credentials\nUse: demo@ordermorph.com / password", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun navigateToDashboard() {
        val intent = Intent(this, DashboardActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}