package com.example.ordermoapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ordermoapplication.models.User
import com.example.ordermoapplication.utils.SessionManager

class EditProfileActivity : AppCompatActivity() {
    private lateinit var etFullName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPhone: EditText
    private lateinit var btnSave: Button
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        supportActionBar?.title = "Edit Profile"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        sessionManager = SessionManager(this)
        initViews()
        loadUserData()
        setupListeners()
    }

    private fun initViews() {
        etFullName = findViewById(R.id.etFullName)
        etEmail = findViewById(R.id.etEmail)
        etPhone = findViewById(R.id.etPhone)
        btnSave = findViewById(R.id.btnSave)
    }

    private fun loadUserData() {
        val user = sessionManager.getUser()
        user?.let {
            etFullName.setText(it.fullName)
            etEmail.setText(it.email)
            etPhone.setText(it.phone)
        }
    }

    private fun setupListeners() {
        btnSave.setOnClickListener { saveProfile() }
    }

    private fun saveProfile() {
        val fullName = etFullName.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val phone = etPhone.text.toString().trim()

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
            else -> {
                val currentUser = sessionManager.getUser()
                val updatedUser = User(
                    fullName = fullName,
                    email = email,
                    phone = phone,
                    password = currentUser?.password ?: ""
                )
                sessionManager.updateUser(updatedUser)

                Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show()
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