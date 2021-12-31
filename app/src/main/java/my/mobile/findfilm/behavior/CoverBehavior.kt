package my.mobile.findfilm.behavior

import android.R.attr
import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.cardview.widget.CardView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import java.util.jar.Attributes
import my.mobile.findfilm.R

import android.content.res.TypedArray
import android.R.attr.dependency







class CoverBehavior(context: Context,attrs: AttributeSet): CoordinatorLayout.Behavior<CardView>() {
    // calculated from given layout
//    private var startXPositionImage = 0
//    private var startYPositionImage = 0
    private var startHeight = 0
    private var startWidth = 0
    private var startToolbarHeight = 0

    private var initialised = false

    private var amountOfToolbarToMove = 0f
    private var amountOfImageToReduceHeight = 0f
    private var amountOfImageToReduceWidth = 0f
//    private var amountToMoveXPosition = 0f
//    private var amountToMoveYPosition = 0f

    // user configured params
    private var finalToolbarHeight = 0f  // user configured params
//    private var finalXPosition = 0f  // user configured params
//    private var finalYPosition = 0f  // user configured params
    private var finalHeight = 0f
    private var finalWidth = 0f
//    private var onlyVerticalMove = false

    init {
        val a: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.CoverImageBehavior)
//        finalXPosition = a.getDimension(R.styleable.CoverImageBehavior_finalXPosition, 0f)
//        finalYPosition = a.getDimension(R.styleable.CoverImageBehavior_finalYPosition, 0f)
        finalHeight = a.getDimension(R.styleable.CoverImageBehavior_finalHeight, 0f)
        finalWidth = a.getDimension(R.styleable.CoverImageBehavior_finalWidth, 0f)
        finalToolbarHeight = a.getDimension(R.styleable.CoverImageBehavior_finalToolbarHeight, 0f)
//        onlyVerticalMove = a.getBoolean(R.styleable.CoverImageBehavior_onlyVerticalMove, false)
        a.recycle()
    }

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: CardView,
        dependency: View
    ): Boolean {
        return dependency is AppBarLayout
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: CardView,
        dependency: View
    ): Boolean {
        initProperties(child, dependency)

        // calculate progress of movement of dependency

        // calculate progress of movement of dependency
        var currentToolbarHeight: Float = startToolbarHeight + dependency.y // current expanded height of toolbar

        // don't go below configured min height for calculations (it does go passed the toolbar)
        // don't go below configured min height for calculations (it does go passed the toolbar)
        currentToolbarHeight = Math.max(currentToolbarHeight, finalToolbarHeight)
        val amountAlreadyMoved = startToolbarHeight - currentToolbarHeight
        val progress = 100 * amountAlreadyMoved / amountOfToolbarToMove // how much % of expand we reached


        // update image size

        // update image size
        val heightToSubtract = progress * amountOfImageToReduceHeight / 100
        val widthToSubtract = progress * amountOfImageToReduceWidth / 100
        val lp = child.layoutParams as CoordinatorLayout.LayoutParams
        lp.width = (startWidth - widthToSubtract).toInt()
        lp.height = (startHeight - heightToSubtract).toInt()
        child.layoutParams = lp

        // update image position

        // update image position
//        val distanceXToSubtract = progress * amountToMoveXPosition / 100
//        val distanceYToSubtract = progress * amountToMoveYPosition / 100
//        val newXPosition = startXPositionImage - distanceXToSubtract
        //newXPosition = newXPosition < endXPosition ? endXPosition : newXPosition; // don't go passed end position
        //newXPosition = newXPosition < endXPosition ? endXPosition : newXPosition; // don't go passed end position
//        if (!onlyVerticalMove) {
//            child.x = newXPosition
//        }
//        child.x = child.x
//        child.y = startYPositionImage - distanceYToSubtract

        return true
    }
    private fun initProperties(
        child: CardView,
        dependency: View
    ) {
        if (!initialised) {
            // form initial layout
            startHeight = child.height
            startWidth = child.width
//            startXPositionImage = child.x.toInt()
//            startYPositionImage = child.y.toInt()
            startToolbarHeight = dependency.height
            // some calculated fields
            amountOfToolbarToMove = startToolbarHeight - finalToolbarHeight
            amountOfImageToReduceHeight = startHeight - finalHeight
            amountOfImageToReduceWidth = startWidth - finalWidth
//            amountToMoveXPosition = startXPositionImage - finalXPosition
//            amountToMoveYPosition = startYPositionImage - finalYPosition
            initialised = true
        }
    }
}