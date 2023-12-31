package com.xk.study.studycomposeapp.ui.comingsoon

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xk.common.lottie.LottieWorkingLoadingView
import com.xk.common.theme.typography

@Composable
fun ComingSoon() {
    Surface {
        Column(modifier = Modifier
                .fillMaxSize()
                .padding(50.dp)) {
            LottieWorkingLoadingView(context = LocalContext.current)
            Text(text = "Coming Soon",
                    style = typography.h5,
                    modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth(),
                    textAlign = TextAlign.Center)
            Text(text = "work in progress",
                    style = typography.subtitle2,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComingSoon()
}