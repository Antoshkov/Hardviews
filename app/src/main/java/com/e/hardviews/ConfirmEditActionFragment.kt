package com.e.hardviews

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

class ConfirmEditActionFragment : BaseConfirmActionFragment() {

    private lateinit var btnDelete: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_confirm_action, container, false)
        initViews(view)
        super.onCreateView(inflater, container, savedInstanceState)
        createDeleteButton()
        action?.let { progressMain.progress = it.progress }
        action?.let { countTimes = it.amountPerDay }
        action?.let { piecesView.setAmountTimes(it.amountPerDay) }
        tvCountTimes.text = countTimes.toString()
        btnSaveTask.setOnClickListener(this)
        btnDelete.setOnClickListener(this)
        return view
    }

    @SuppressLint("ResourceType", "SetTextI18n")
    private fun createDeleteButton() {
        val set = ConstraintSet()
        btnDelete = Button(context)
        constButton.addView(btnDelete)
        btnDelete.layoutParams = (ConstraintLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT))
        btnDelete.setBackgroundResource(R.drawable.button_save_task_selector)
        btnDelete.textSize = 22F
        btnDelete.setPadding(80, 45, 80, 45)
        btnDelete.text = "DELETE TASK"
        btnDelete.id = 1001
        set.clone(constButton)
        set.connect(btnDelete.id, ConstraintSet.TOP, btnSaveTask.id, ConstraintSet.BOTTOM, 40)
        set.applyTo(constButton)
    }

    override fun onClick(view: View?) {
        super.onClick(view)
        when (view?.id) {
            btnSaveTask.id -> {
                action?.nameAction = actionName.text.toString()
                action?.amountPerDay = countTimes
                viewModel.editAction(action)
                navController.navigate(R.id.mainFragment)
            }
            btnDelete.id -> {
                viewModel.deleteAction(action)
                navController.navigate(R.id.mainFragment)
            }
        }
    }
}