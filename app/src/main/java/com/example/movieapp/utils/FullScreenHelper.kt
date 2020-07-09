package com.example.movieapp.utils

import android.app.Activity
import android.view.View

data class FullScreenHelper(
    private var activity: Activity,
    private var views: List<View>

) {
    public fun FullScreenHelper(activity: Activity, views: List<View>) {
        this.activity = activity
        this.views = views
    }

    private fun showSystemUI(decorView: View) {
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE


    }

    private fun hideSystemUI(decorView: View) {
        decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }

    public fun enterFullScreen(){
        val view:View=activity.window.decorView
        hideSystemUI(view)
        for (view1:View  in views ){
            view1.visibility=View.GONE
            view1.invalidate()

        }
    }

    public fun exitFullScreen(){
        val view:View=activity.window.decorView
        hideSystemUI(view)
        for (view1:View  in views ){
            view1.visibility=View.VISIBLE
            view1.invalidate()

        }
    }
}