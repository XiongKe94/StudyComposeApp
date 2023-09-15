package com.xk.study.studycomposeapp.ui

import FaIcons
import android.annotation.SuppressLint
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.guru.fontawesomecomposelib.FaIcon
import com.xk.common.theme.ColorPallet
import com.xk.common.theme.ComposeCookBookMaterial3Theme
import com.xk.common.theme.utils.AppThemeState
import com.xk.common.theme.utils.SystemUiController
import com.xk.study.studycomposeapp.R
import com.xk.study.studycomposeapp.bean.BottomNavType
import com.xk.study.studycomposeapp.ui.base.BaseActivity
import com.xk.study.studycomposeapp.ui.comingsoon.ComingSoon
import com.xk.study.studycomposeapp.ui.page.HomePage
import com.xk.study.studycomposeapp.ui.page.MultiPage
import com.xk.study.studycomposeapp.utils.AppLog
import com.xk.study.studycomposeapp.utils.ClickRef
import com.xk.study.studycomposeapp.utils.RotatePlayIcon

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val appThemeState = remember { mutableStateOf(AppThemeState(false, ColorPallet.BLUE)) }
    rootRootView(appThemeState.value, null) {
        mainAppContent(appThemeState.value)
    }
}

class MainActivity : BaseActivity() {
    override fun nextStep() {
        super.nextStep()
        setContent {
            val systemUiController = remember { SystemUiController(window) }
            val appThemeState = remember { mutableStateOf(AppThemeState()) }
            rootRootView(appThemeState = appThemeState.value, systemUiController = systemUiController) {
                mainAppContent(appThemeState.value)
            }
        }
    }
}

@Composable
private fun rootRootView(appThemeState: AppThemeState, systemUiController: SystemUiController?, content: @Composable () -> Unit) {
    ComposeCookBookMaterial3Theme(darkTheme = appThemeState.darkTheme, colorPallet = appThemeState.pallet) {
        systemUiController?.setStatusBarColor(color = MaterialTheme.colorScheme.onPrimaryContainer, darkIcons = appThemeState.darkTheme)
        content.invoke()
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun mainAppContent(appThemeState: AppThemeState) {
    val defaultBottomNavType = BottomNavType.Home
    var clickRef by remember { mutableStateOf(ClickRef(0)) }
    var selectNavType by remember { mutableStateOf(defaultBottomNavType) }
    val navController = rememberNavController()
    val pageDataList = getMainPageData(selectNavType, clickRef)

    Scaffold(bottomBar = {
        BottomNavigation(
            backgroundColor = MaterialTheme.colorScheme.background, elevation = 8f.dp,
            contentColor = MaterialTheme.colorScheme.onBackground
        ) {
            pageDataList.forEach { pageParam: PageParam ->
                val isSelected = navController.currentDestination?.route == pageParam.bottomNavType.name
                BottomNavigationItem(selected = isSelected,
                    label = { selectNavType.getTextModifier(pageParam) },
                    icon = pageParam.iconBlock, onClick = {
                        navController.navigate(pageParam.bottomNavType.name) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                        val currentRoute = navController.currentDestination?.route ?: return@BottomNavigationItem
                        selectNavType = BottomNavType.valueOf(currentRoute)
                        clickRef = ClickRef(clickRef.value + 1)
                        AppLog(tag = "MainActivity", msg = "currentRoute ${selectNavType.name} ${clickRef.value}")
                    })
            }
        }
    }) {
        NavHost(navController = navController, startDestination = BottomNavType.Home.name,
            modifier = Modifier.padding(bottom = 56f.dp)) {
            composable(BottomNavType.Home.name) { HomePage() }
            composable(BottomNavType.Widgets.name) { MultiPage("Widgets") }
            composable(BottomNavType.Animation.name) { MultiPage("Animation") }
            composable(BottomNavType.DemoUi.name) { MultiPage("DemoUi") }
            composable(BottomNavType.Template.name) { ComingSoon() }
        }
    }
}

private class PageParam(
    val bottomNavType: BottomNavType,
    val nameRes: Int,
    val iconBlock: @Composable () -> Unit
)

@Composable
private fun BottomNavType.getTextModifier(pageParam: PageParam) = if (this == pageParam.bottomNavType) {
    Text(
        stringResource(pageParam.nameRes), style = TextStyle(
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.primary, textAlign = TextAlign.Center
        ), maxLines = 1
    )
} else {
    Text(
        stringResource(pageParam.nameRes), style = TextStyle(
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurface, textAlign = TextAlign.Center
        ), maxLines = 1
    )
}

@Composable
private fun BottomNavType.getIconTintColor(selectNavType: BottomNavType) = if (this == selectNavType) {
    MaterialTheme.colorScheme.primary
} else {
    MaterialTheme.colorScheme.onSurface
}

@Composable
private fun BottomNavType.getIconSize(selectNavType: BottomNavType) = if (this == selectNavType) {
    24f.dp
} else {
    22f.dp
}

@Composable
private fun getMainPageData(selectNavType: BottomNavType, clickRef: ClickRef): MutableList<PageParam> {
    val homeFragment = PageParam(BottomNavType.Home, R.string.navigation_item_home, iconBlock = {
        FaIcon(
            faIcon = FaIcons.Home, tint = selectNavType.getIconTintColor(BottomNavType.Home),
            size = selectNavType.getIconSize(BottomNavType.Home)
        )
    })
    val widgetsFragment = PageParam(BottomNavType.Widgets, R.string.navigation_item_widgets, iconBlock = {
        FaIcon(
            faIcon = FaIcons.Tools, tint = selectNavType.getIconTintColor(BottomNavType.Widgets),
            size = selectNavType.getIconSize(BottomNavType.Widgets)
        )
    })
    val animFragment = PageParam(BottomNavType.Animation, R.string.navigation_item_animation, iconBlock = {
        RotatePlayIcon(
            state = selectNavType == BottomNavType.Animation,
            angle = 720f, duration = 2000,
            tint = selectNavType.getIconTintColor(BottomNavType.Animation),
            size = selectNavType.getIconSize(BottomNavType.Animation))
    })
    val demoFragment = PageParam(BottomNavType.DemoUi, R.string.navigation_item_demoui, iconBlock = {
        FaIcon(
            faIcon = FaIcons.LaptopCode, tint = selectNavType.getIconTintColor(BottomNavType.DemoUi),
            size = selectNavType.getIconSize(BottomNavType.DemoUi)
        )
    })
    val templateFragment = PageParam(BottomNavType.Template, R.string.navigation_item_profile, iconBlock = {
        FaIcon(
            faIcon = FaIcons.LayerGroup, tint = selectNavType.getIconTintColor(BottomNavType.Template),
            size = selectNavType.getIconSize(BottomNavType.Template)
        )
    })
    return mutableListOf(homeFragment, widgetsFragment, animFragment, demoFragment, templateFragment)
}

