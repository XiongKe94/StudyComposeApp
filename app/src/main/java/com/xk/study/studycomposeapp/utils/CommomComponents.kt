package com.xk.study.studycomposeapp.utils

import FaIcons
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guru.fontawesomecomposelib.FaIcon
import com.xk.common.theme.typography


@Composable
fun HeadingSection(modifier: Modifier = Modifier, title: String = "", subtitle: String = "") {
    Column(modifier = modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        if (title.isNotEmpty()) {
            Text(text = title, style = typography.h6.copy(fontSize = 14.sp))
        }
        if (title.isNotEmpty()) {
            Text(text = subtitle, style = typography.subtitle2)
        }
        Divider()
    }
}

@Composable
fun TitleText(modifier: Modifier = Modifier, title: String) {
    androidx.compose.material3.Text(
            text = title,
            style = typography.h6.copy(fontSize = 14.sp),
            modifier = modifier.padding(8.dp)
    )
}

@Composable
fun SubtitleText(subtitle: String, modifier: Modifier = Modifier) {
    Text(text = subtitle, style = typography.subtitle2, modifier = modifier.padding(8.dp))
}

@Composable
fun RotatePlayIcon(
    state: Boolean,
    tint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
    angle: Float,
    duration: Int,
    size : Dp = 20.dp,
    modifier: Modifier = Modifier
) {
    FaIcon(
        faIcon = FaIcons.Play,
        size = size,
        tint = tint,
        modifier = modifier
            .padding(2.dp)
            .graphicsLayer(rotationZ = animateFloatAsState(if (state) 0f else angle, tween(duration)).value))
}