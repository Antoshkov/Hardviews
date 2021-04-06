package com.e.hardviews

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.coroutines.*

class ActionView : ConstraintLayout {
    constructor (context: Context) : super(context) {}
    constructor (context: Context, attrs: AttributeSet?) : super(context, attrs) {}
    constructor (context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    val container: ConstraintLayout
    val piecesView: PiecesView
    val btnEdit: ImageButton
    val progressMain: ProgressBar
    val tvNameAction: TextView
    val iconAction: ImageView
    val iconActionReverse: ImageView
    private val checkedWhite: ImageView
    private val checkedBlack: ImageView
    private val imgWellDone: ImageView
    private val progressOneTime: ProgressBar
    private val scope = CoroutineScope(Job() + Dispatchers.Main)

    init {
        val view = View.inflate(context, R.layout.item_action, this)
        container = view.findViewById(R.id.constContainer)
        checkedWhite = view.findViewById(R.id.checkedWhite)
        checkedBlack = view.findViewById(R.id.checkedBlack)
        iconAction = view.findViewById(R.id.iconAction)
        iconActionReverse = view.findViewById(R.id.iconActionReverse)
        imgWellDone = view.findViewById(R.id.wellDone)
        progressOneTime = view.findViewById(R.id.progressOneTime)
        progressMain = view.findViewById(R.id.progressMain)
        btnEdit = view.findViewById(R.id.btnEdit)
        tvNameAction = view.findViewById(R.id.nameAction)
        piecesView = view.findViewById(R.id.createPieces)
    }

    private fun startProgressOneTime(){
        val animatorRun = ValueAnimator.ofInt(0, 100)
        animatorRun.duration = 1500
        animatorRun.addUpdateListener { valueAnimator -> progressOneTime.progress = valueAnimator.animatedValue.toString().toInt() }
        animatorRun.start()
    }
    private fun startProgressOneTimeReverse() {
        val animatorRun = ValueAnimator.ofInt(100, 0)
        animatorRun.duration = 1500
        animatorRun.addUpdateListener { valueAnimator -> progressOneTime.progress = valueAnimator.animatedValue.toString().toInt() }
        animatorRun.start()
    }

    fun startAnimationProgress(lastProgress: Int, partProgress: Int) {
        progressOneTime.visibility = VISIBLE
        scope.launch {
            startProgressOneTime()
            delay(1500)
            startProgressOneTimeReverse()
            val animator = ValueAnimator.ofInt(lastProgress, partProgress)
            animator.duration = 1500
            animator.addUpdateListener { valueAnimator -> progressMain.progress = valueAnimator.animatedValue.toString().toInt() }
            animator.start()
            delay(1500)

        }
    }
}