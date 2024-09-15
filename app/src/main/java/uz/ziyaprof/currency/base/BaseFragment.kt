package uz.ziyaprof.currency.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import uz.ziyaprof.currency.R

abstract class BaseFragment(
    @LayoutRes layoutId: Int
) : Fragment(layoutId) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        clicks()
        observe()
    }

    abstract fun setup()
    open fun setup(view: View) {}
    open fun clicks() {}
    open fun observe() {}
}