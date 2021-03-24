package com.e.hardviews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import java.util.concurrent.Callable

class ChooseDayFragment : BaseFragment() {

    private lateinit var chkMonday: CheckBox
    private lateinit var chkTuesday: CheckBox
    private lateinit var chkWednesday: CheckBox
    private lateinit var chkThursday: CheckBox
    private lateinit var chkFriday: CheckBox
    private lateinit var chkSaturday: CheckBox
    private lateinit var chkSunday: CheckBox
    private lateinit var btnClose: ImageView
    private lateinit var btnApply: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_choose_day, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        chkMonday = view.findViewById(R.id.chkMonday)
        chkTuesday = view.findViewById(R.id.chkTuesday)
        chkWednesday = view.findViewById(R.id.chkWednesday)
        chkThursday = view.findViewById(R.id.chkThursday)
        chkFriday = view.findViewById(R.id.chkFriday)
        chkSaturday = view.findViewById(R.id.chkSaturday)
        chkSunday = view.findViewById(R.id.chkSunday)
        btnApply = view.findViewById(R.id.btnApply)
        btnClose = view.findViewById(R.id.imgClose)

    }
}