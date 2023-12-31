package com.xk.common.cascademenu

import androidx.compose.runtime.*

class CascadeMenuState<T : Any>(currentMenuItem: CascadeMenuItem<T>) {
    private var _currentMenu by mutableStateOf(currentMenuItem)

    var currentMenuItem: CascadeMenuItem<T>
        get() = _currentMenu
        set(value) {
            _currentMenu = value
        }
}