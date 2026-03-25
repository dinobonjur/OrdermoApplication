package com.example.ordermoapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ordermoapplication.models.User
import com.example.ordermoapplication.utils.SessionManager

class ChangePasswordActivity : AppCompatActivity() {
    private lateinit var etCurrentPassword: EditText
    private lateinit var etNewPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var btnChangePassword: Button
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        supportActionBar?.title = "Change Password"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        sessionManager = SessionManager(this)
        initViews()
        setupListeners()
    }

    private fun initViews() {
        etCurrentPassword = findViewById(R.id.etCurrentPassword)
        etNewPassword = findViewById(R.id.etNewPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        btnChangePassword = findViewById(R.id.btnChangePassword)
    }

    private fun setupListeners() {
        btnChangePassword.setOnClickListener { changePassword() }
    }

    private fun changePassword() {
        val currentPassword = etCurrentPassword.text.toString().trim()
        val newPassword = etNewPassword.text.toString().trim()
        val confirmPassword = etConfirmPassword.text.toString().trim()

        val user = sessionManager.getUser()

        when {
            currentPassword.isEmpty() -> {
                etCurrentPassword.error = "Current password is required"
                return
            }
            currentPassword != user?.password -> {
                etCurrentPassword.error = "Current password is incorrect"
                return
            }
            newPassword.isEmpty() -> {
                etNewPassword.error = "New password is required"
                return
            }
            newPassword.length < 6 -> {
                etNewPassword.error = "Password must be at least 6 characters"
                return
            }
            confirmPassword.isEmpty() -> {
                etConfirmPassword.error = "Please confirm your new password"
                return
            }
            newPassword != confirmPassword -> {
                etConfirmPassword.error = "Passwords do not match"
                return
            }
            else -> {
                // Update password
                val updatedUser = User(
                    fullName = user?.fullName ?: "",
                    email = user?.email ?: "",
                    phone = user?.phone ?: "",
                    password = newPassword
                )
                sessionManager.updateUser(updatedUser)

                Toast.makeText(this, "Password changed successfully", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}