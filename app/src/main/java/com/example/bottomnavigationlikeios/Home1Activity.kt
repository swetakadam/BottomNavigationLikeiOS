package com.example.bottomnavigationlikeios

import android.content.Intent
import android.os.Bundle
import android.widget.Button

public class Home1Activity : BaseActivity()
{
    override fun getContentViewId(): Int = R.layout.activity_home1
    override fun getNavigationMenuItemId(): Int = navigationTaskRootId

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent
        if (intent != null) {
            navigationTaskRootId = intent.getIntExtra(NAVIGATION_TASK_ROOT, -1)
        }
        val b = findViewById(R.id.button) as Button
        b.setOnClickListener { v ->
            val a = Intent(this, Home2Activity::class.java).putExtra(NAVIGATION_TASK_ROOT, navigationTaskRootId)
            startActivity(a)

        }
    }
}