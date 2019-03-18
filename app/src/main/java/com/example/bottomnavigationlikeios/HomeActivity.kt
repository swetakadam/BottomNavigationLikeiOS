package com.example.bottomnavigationlikeios

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button

class HomeActivity : BaseActivity() {

    override fun getContentViewId(): Int = R.layout.activity_home
    override fun getNavigationMenuItemId(): Int = navigationTaskRootId

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val edit = pref.edit()
        navigationTaskRootId = R.id.navigation_home
        edit.putInt(CURRENT_TAB, R.id.navigation_home)
        edit.commit()

        val b = findViewById(R.id.button) as Button
        b.setOnClickListener { v ->
            val a = Intent(this, Home1Activity::class.java).putExtra(NAVIGATION_TASK_ROOT, navigationTaskRootId)
            startActivity(a)

        }


    }
}
