package ru.igorcodes.mathgame

import android.app.Activity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

object LayoutManager {
    fun setOnApplyWindowInsetsListener(activity: Activity) {
        ViewCompat.setOnApplyWindowInsetsListener(activity.findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}