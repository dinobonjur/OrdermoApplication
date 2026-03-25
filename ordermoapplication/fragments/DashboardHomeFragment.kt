package com.example.ordermoapplication.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.ordermoapplication.EditProfileActivity
import com.example.ordermoapplication.ProfileActivity
import com.example.ordermoapplication.R
import com.example.ordermoapplication.utils.SessionManager

class DashboardHomeFragment : Fragment() {
    private lateinit var tvWelcome: TextView
    private lateinit var tvStats: TextView
    private lateinit var tvRecentActivity: TextView
    private lateinit var btnViewProfile: Button
    private lateinit var btnEditProfile: Button
    private lateinit var btnRefresh: Button
    private lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard_home, container, false)

        sessionManager = SessionManager(requireContext())

        initViews(view)
        loadUserData()
        loadStatistics()
        loadRecentActivity()
        setupClickListeners()

        return view
    }

    private fun initViews(view: View) {
        tvWelcome = view.findViewById(R.id.tvWelcome)
        tvStats = view.findViewById(R.id.tvStats)
        tvRecentActivity = view.findViewById(R.id.tvRecentActivity)
        btnViewProfile = view.findViewById(R.id.btnViewProfile)
        btnEditProfile = view.findViewById(R.id.btnEditProfile)
        btnRefresh = view.findViewById(R.id.btnRefresh)
    }

    private fun loadUserData() {
        val user = sessionManager.getUser()
        val firstName = user?.fullName?.split(" ")?.firstOrNull() ?: "User"
        tvWelcome.text = "Welcome, $firstName! 👋"
    }

    private fun loadStatistics() {
        tvStats.text = """
            📦 Total Orders: 12
            🚀 Active Orders: 3
            ✅ Completed Orders: 9
            ⭐ Rating: 4.8/5
        """.trimIndent()
    }

    private fun loadRecentActivity() {
        tvRecentActivity.text = """
            • Logged in recently
            • Dashboard viewed
            • Profile updated
        """.trimIndent()
    }

    private fun setupClickListeners() {
        btnViewProfile.setOnClickListener {
            startActivity(Intent(requireContext(), ProfileActivity::class.java))
        }

        btnEditProfile.setOnClickListener {
            startActivity(Intent(requireContext(), EditProfileActivity::class.java))
        }

        btnRefresh.setOnClickListener {
            Toast.makeText(requireContext(), "Refreshing...", Toast.LENGTH_SHORT).show()
            loadStatistics()
            loadRecentActivity()
        }
    }

    override fun onResume() {
        super.onResume()
        loadUserData()
    }
}