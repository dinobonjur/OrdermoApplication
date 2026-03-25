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

class RegisterActivity : AppCompatActivity() {
    private lateinit var etFullName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPhone: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var btnRegister: Button
    private lateinit var tvLoginLink: TextView
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        sessionManager = SessionManager(this)
        initViews()
        setupListeners()
    }

    private fun initViews() {
        etFullName = findViewById(R.id.etFullName)
        etEmail = findViewById(R.id.etEmail)
        etPhone = findViewById(R.id.etPhone)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        btnRegister = findViewById(R.id.btnRegister)
        tvLoginLink = findViewById(R.id.tvLoginLink)
    }

    private fun setupListeners() {
        btnRegister.setOnClickListener { register() }
        tvLoginLink.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun register() {
        val fullName = etFullName.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val phone = etPhone.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val confirmPassword = etConfirmPassword.text.toString().trim()

        when {
            fullName.isEmpty() -> {
                etFullName.error = "Full name is required"
                return
            }
            email.isEmpty() -> {
                etEmail.error = "Email is required"
                return
            }
            phone.isEmpty() -> {
                etPhone.error = "Phone number is required"
                return
            }
            password.isEmpty() -> {
                etPassword.error = "Password is required"
                return
            }
            confirmPassword.isEmpty() -> {
                etConfirmPassword.error = "Please confirm your password"
                return
            }
            password != confirmPassword -> {
                etConfirmPassword.error = "Passwords do not match"
                return
            }
            else -> {
                // Create new user and save
                val user = User(fullName, email, phone, password)
                sessionManager.saveUser(user)

                Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()

                // Navigate to dashboard
                val intent = Intent(this, DashboardActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
        }
    }
}