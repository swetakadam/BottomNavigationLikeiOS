package com.example.bottomnavigationlikeios

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class DashboardActivity : BaseActivity() {
    override fun getContentViewId(): Int {
        return R.layout.activity_dashboard
    }

    override fun getNavigationMenuItemId(): Int {
        return navigationTaskRootId
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This is one of the root activity
        navigationTaskRootId = R.id.navigation_dashboard

        val b = findViewById(R.id.button) as Button
        b.setOnClickListener { v ->
            val a = Intent(this, Home1Activity::class.java).putExtra(NAVIGATION_TASK_ROOT, navigationTaskRootId)
            startActivity(a)

        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("sweta","sweta dashboard on start called")

    }


    override fun onResume() {
        super.onResume()
        Log.d("sweta","on resume called")
    }
}
