package my.mobile.findfilm.behavior

import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.abs
import kotlin.math.roundToInt


class AppBarScrollWatcher(private val listener: OffsetListener) :
    OnOffsetChangedListener {
    private var scrollRange = -1
    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        if (scrollRange == -1) {
            scrollRange = appBarLayout.totalScrollRange
        }
        val appbarHeight = scrollRange + verticalOffset
        var alpha = appbarHeight.toFloat() / scrollRange
        if (alpha < 0) {
            alpha = 0f
        }
        val alphaZeroOnCollapsed = shrinkAlpha(alpha)
        val alphaZeroOnExpanded = abs(alphaZeroOnCollapsed - 1)
        val argbZeroOnExpanded = abs(alphaZeroOnCollapsed * 255 - 255).roundToInt()
        val argbZeroOnCollapsed = abs(alphaZeroOnCollapsed * 255).roundToInt()
        listener.onAppBarExpanding(
            alphaZeroOnExpanded <= 0,
            alphaZeroOnCollapsed <= 0,
            argbZeroOnExpanded,
            argbZeroOnCollapsed,
            alphaZeroOnCollapsed,
            alphaZeroOnExpanded
        )
    }

    private fun shrinkAlpha(alpha: Float): Float {
//        val formatter: NumberFormat = NumberFormat.getInstance(Locale.getDefault())
//        formatter.maximumFractionDigits = 2
//        formatter.minimumFractionDigits = 2
//        formatter.roundingMode = RoundingMode.HALF_DOWN
//        return formatter.format(alpha).toFloat()
        return alpha
    }

    interface OffsetListener {
        fun onAppBarExpanding(
            expanded: Boolean,
            collapsed: Boolean,
            argbZeroOnExpanded: Int,
            argbZeroOnCollapsed: Int,
            alphaZeroOnCollapsed: Float,
            alphaZeroOnExpanded: Float
        )
    }
}