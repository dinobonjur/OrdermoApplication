package com.example.ordermoapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ordermoapplication.fragments.DashboardHomeFragment
import com.example.ordermoapplication.utils.SessionManager

class DashboardActivity : AppCompatActivity() {
    private lateinit var tvWelcome: TextView
    private lateinit var btnViewProfile: Button
    private lateinit var btnEditProfile: Button
    private lateinit var btnLogout: Button
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        sessionManager = SessionManager(this)

        // Check if user is logged in
        if (!sessionManager.isLoggedIn()) {
            navigateToLogin()
            return
        }

        initViews()
        loadUserData()
        setupListeners()

        // Load the dashboard fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.contentFrame, DashboardHomeFragment())
            .commit()
    }

    private fun initViews() {
        tvWelcome = findViewById(R.id.tvWelcome)
        btnViewProfile = findViewById(R.id.btnViewProfile)
        btnEditProfile = findViewById(R.id.btnEditProfile)
        btnLogout = findViewById(R.id.btnLogout)
    }

    private fun loadUserData() {
        val user = sessionManager.getUser()
        val firstName = user?.fullName?.split(" ")?.firstOrNull() ?: "User"
        tvWelcome.text = "Welcome, $firstName!"
    }

    private fun setupListeners() {
        btnViewProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        btnEditProfile.setOnClickListener {
            startActivity(Intent(this, EditProfileActivity::class.java))
        }

        btnLogout.setOnClickListener {
            sessionManager.logout()
            Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()
            navigateToLogin()
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}