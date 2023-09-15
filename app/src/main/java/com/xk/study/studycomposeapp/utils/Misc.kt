package com.xk.study.studycomposeapp.utils

import android.util.Log
import androidx.compose.runtime.Composable

/**
 * @author xiongke
 * @Date 2023/08/25
 */
class ClickRef(var value: Int)

@Composable
fun LogCompositions(tag: String, msg: String) {
    Log.d("AppLogger-$tag", msg)
}

fun AppLog(tag: String, msg: String) {
    Log.d("AppLogger-$tag", msg)
}