package com.xk.study.studycomposeapp.ui.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity

/**
 * @author xiongke
 * @date 2018/5/18
 */
abstract class BaseActivity : ComponentActivity() {
    protected val logTag: String by lazy { javaClass.simpleName }
    protected val mContext by lazy { this }
    protected val mActivity: BaseActivity by lazy { this }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nextStep()
    }

    protected open fun initPageParams() = Unit
    protected open fun initView() = Unit
    protected open fun nextStep() = Unit
}