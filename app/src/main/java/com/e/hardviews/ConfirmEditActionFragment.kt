package com.e.hardviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.e.hardviews.CreateActionFragment.AMOUNT_OF_DAY

class ConfirmEditActionFragment: BaseConfirmActionFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_confirm_action, container, false)
        initViews(view)
        super.onCreateView(inflater, container, savedInstanceState)
        countTimes = requireArguments().getInt(AMOUNT_OF_DAY)
        tvCountTimes.text = countTimes.toString()
        btnSaveTask.setOnClickListener {
            val icon = requireArguments().getInt(CreateActionFragment.ICON_ACTION)
            val iconRev = requireArguments().getInt(CreateActionFragment.ICON_ACTION_REVERSE)
            viewModel.editAction(MyAction(actionName.text.toString(), icon, iconRev, countTimes))
            navController.navigate(R.id.mainFragment)
        }
        return view
    }
}