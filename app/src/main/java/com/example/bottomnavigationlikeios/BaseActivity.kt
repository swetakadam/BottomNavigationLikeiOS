package com.example.bottomnavigationlikeios

import android.app.ActivityManager
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener
import android.support.v4.content.ContextCompat.getSystemService
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem


abstract class BaseActivity : AppCompatActivity(), OnNavigationItemSelectedListener{


    val CURRENT_TAB = "current_tab"
    val NAVIGATION_TASK_ROOT = "navigation_task_root_id"


    protected lateinit var navigationView: BottomNavigationView
    protected var navigationTaskRootId: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentViewId())

        navigationView = findViewById(R.id.navigation)
        navigationView.setOnNavigationItemSelectedListener(this)
    }

    override fun onStart() {
        super.onStart()
        updateNavigationBarState()
    }


    // Remove inter-activity transition to avoid screen tossing on tapping bottom navigation items
    public override fun onPause() {
        super.onPause()
        //overridePendingTransition(R.anim.fade_in,R.anim.fade_out)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

            val itemId = item.itemId

            val pref = PreferenceManager.getDefaultSharedPreferences(this)
            val currentTabId = pref.getInt(CURRENT_TAB, -1)

            if (currentTabId != -1 && currentTabId != itemId) {
                //we are switching tabs
                val edit = pref.edit()
                edit.putInt(CURRENT_TAB, itemId)
                edit.commit()
            }

            val taskId: Int
            when (itemId) {
                R.id.navigation_home -> {
                    taskId = getNavigationTaskIdIfRunning("com.example.bottomnavigationlikeios.HomeActivity")
                    if (taskId == -1) {
                        startActivity(
                            Intent(this, HomeActivity::class.java).putExtra(
                                NAVIGATION_TASK_ROOT,
                                R.id.navigation_home
                            )
                        )
                        overridePendingTransition(R.anim.fade_in,R.anim.fade_out)

                    } else {
                        moveToFront(taskId)
                    }
                }
                R.id.navigation_dashboard -> {
                    taskId = getNavigationTaskIdIfRunning("com.example.bottomnavigationlikeios.DashboardActivity")
                    if (taskId == -1) {
                        startActivity(
                            Intent(this, DashboardActivity::class.java).putExtra(
                                NAVIGATION_TASK_ROOT,
                                R.id.navigation_dashboard
                            )
                        )
                        overridePendingTransition(R.anim.fade_in,R.anim.fade_out)

                    } else {
                        moveToFront(taskId)
                    }
                }
                R.id.navigation_notifications -> {
                    taskId = getNavigationTaskIdIfRunning("com.example.bottomnavigationlikeios.NotificationsActivity")
                    if (taskId == -1) {
                        startActivity(
                            Intent(this, NotificationsActivity::class.java).putExtra(
                                NAVIGATION_TASK_ROOT,
                                R.id.navigation_notifications
                            )
                        )
                        overridePendingTransition(R.anim.fade_in,R.anim.fade_out)

                    } else {
                        moveToFront(taskId)
                    }
                }
            }

        return true
    }

    /**
     * on API < 23  multiple tasks will be seen in recents/overview app if user clicks on every tab
     * on API >= 23 we only keep the latest task in Recents/Overview app
     *
     * @param tab
     * @return
     */
    protected fun getNavigationTaskIdIfRunning(tab: String): Int {
        var taskId = -1
        if (Build.VERSION.SDK_INT >= 23) {
            val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val recentTasks = activityManager.appTasks

            for (task in recentTasks) {

                if (null != task.taskInfo && null != task.taskInfo.baseActivity && task.taskInfo.baseActivity.toShortString().indexOf(
                        tab
                    ) > -1
                ) {
                    task.setExcludeFromRecents(false)
                    taskId = task.taskInfo.id
                }
                task.setExcludeFromRecents(true)

            }

        } else if (Build.VERSION.SDK_INT in 11..22) {
            val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val recentTasks = activityManager.getRunningTasks(Integer.MAX_VALUE)

            for (i in recentTasks.indices) {
                // bring to front
                if (recentTasks[i].baseActivity.toShortString().indexOf(tab) > -1) {
                    taskId = recentTasks[i].id
                }
            }
        }

        return taskId

    }

    private fun moveToFront(taskId: Int) {
        if (Build.VERSION.SDK_INT >= 16) { // honeycomb
            val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val options = ActivityOptions.makeCustomAnimation(this,R.anim.fade_in, R.anim.fade_out)
            activityManager.moveTaskToFront(taskId, ActivityManager.MOVE_TASK_NO_USER_ACTION,options.toBundle())
        }
    }


    private fun updateNavigationBarState() {
        val actionId = getNavigationMenuItemId()
        selectBottomNavigationBarItem(actionId)
    }

    private fun selectBottomNavigationBarItem(itemId: Int) {
        val item = navigationView.menu.findItem(itemId)
        item.isChecked = true
    }

    private fun unselectBottomNavigationBarItem(itemId: Int) {
        val item = navigationView.menu.findItem(itemId)
        item.isChecked = false
    }

    abstract fun getContentViewId(): Int

    abstract fun getNavigationMenuItemId(): Int

}