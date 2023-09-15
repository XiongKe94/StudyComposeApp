package com.xk.study.studycomposeapp.ui.page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

/**
 * @author xiongke
 * @Date 2023/09/01
 */
@Composable
fun MultiPage(content: String) {
    Surface {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = content, fontSize = 16f.sp)
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun MultiPagePreview() {
    MultiPage("测试")
}