package com.xk.common.theme.utils

import com.xk.common.theme.ColorPallet

data class AppThemeState(
    var darkTheme: Boolean = false,
    var pallet: ColorPallet = ColorPallet.BLUE
)