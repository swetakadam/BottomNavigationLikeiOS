package com.example.bottomnavigationlikeios

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class NotificationsActivity : BaseActivity() {

    override fun getContentViewId(): Int {
        return R.layout.activity_notifications
    }

    override fun getNavigationMenuItemId(): Int {
        return R.id.navigation_notifications
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigationTaskRootId = R.id.navigation_notifications
    }
}
