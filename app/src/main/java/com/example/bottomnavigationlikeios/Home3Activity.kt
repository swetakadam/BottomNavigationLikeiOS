package com.example.bottomnavigationlikeios

import android.os.Bundle

public class Home3Activity: BaseActivity(){

    override fun getContentViewId(): Int {
        return R.layout.activity_home3
    }

    override fun getNavigationMenuItemId(): Int {
        return navigationTaskRootId
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        if (intent != null) {
            navigationTaskRootId = intent.getIntExtra(NAVIGATION_TASK_ROOT, -1)
        }

    }
}