package com.e.dailyTasks

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProviders
import com.e.dailyTasks.CreateActionFragment.*
import com.e.dailyTasks.MainActivity.THEME_COLOR
import com.e.dailyTasks.MainFragment.CHOSEN_ACTION

abstract class BaseConfirmActionFragment : BaseFragment(), View.OnClickListener {
    protected lateinit var viewModel: MainViewModel
    protected lateinit var editText: EditText
    protected lateinit var iconAction: ImageView
    private lateinit var imgClose: ImageView
    private lateinit var imgChooseDay: ImageView
    protected lateinit var actionName: TextView
    protected lateinit var tvCountSymbol: TextView
    protected lateinit var tvCountTimes: TextView
    protected lateinit var btnSaveTask: Button
    private lateinit var btnPlus: ImageButton
    private lateinit var btnMinus: ImageButton
    private lateinit var sharedPreferences: SharedPreferences
    protected lateinit var constButton: ConstraintLayout
    protected lateinit var piecesView: CreatePiecesView
    protected lateinit var progressMain: ProgressBar
    protected var action: Action? = null
    protected var countTimes: Int = 1

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        action = requireArguments().getParcelable(CHOSEN_ACTION)
        val textName = action?.nameAction
        viewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
        action?.let { iconAction.setBackgroundResource(it.iconAction) }
        progressMain.resetProgress()
        actionName.text = textName
        piecesView.setPaintColor(sharedPreferences
                .getInt(THEME_COLOR, requireContext().getColor(R.color.sportDesert)))
        editText.setText(textName)
        tvCountSymbol.text = "${textName?.length}/28"
        editText.addTextChangedListener(mTextEditorWatcher)
        editText.setOnKeyListener { view, i, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                actionName.text = editText.text
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
        imgChooseDay.setOnClickListener(this)
        imgClose.setOnClickListener(this)
        btnPlus.setOnClickListener(this)
        btnMinus.setOnClickListener(this)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    protected fun initViews(view: View) {
        piecesView = view.findViewById(R.id.createPieces)
        tvCountTimes = view.findViewById(R.id.tvTimePerDay)
        editText = view.findViewById(R.id.etActionName)
        imgClose = view.findViewById(R.id.imgClose)
        iconAction = view.findViewById(R.id.iconAction)
        actionName = view.findViewById(R.id.tvActionName)
        btnSaveTask = view.findViewById(R.id.btnSaveTask)
        tvCountSymbol = view.findViewById(R.id.tvCountSymbol)
        btnMinus = view.findViewById(R.id.btnMinus)
        btnPlus = view.findViewById(R.id.btnPlus)
        imgChooseDay = view.findViewById(R.id.imgChooseDay)
        constButton = view.findViewById(R.id.constButton)
        progressMain = view.findViewById(R.id.progressMain)
    }

    private val mTextEditorWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            tvCountSymbol.text = "${s.length}/28"
        }
        override fun afterTextChanged(s: Editable) {}
    }

    private fun setCurrentProgress() {
        action?.let { progressMain.progress = (100 / countTimes) * it.countPressedTimes }
    }

    private fun timesUp() {
        if (countTimes < 10) {
            countTimes++
            setCurrentProgress()
            piecesView.setAmountTimes(countTimes)
            tvCountTimes.text = countTimes.toString()
        }
    }

    private fun timesDown() {
        if (countTimes > 1) {
            countTimes--
            setCurrentProgress()
            piecesView.setAmountTimes(countTimes)
            tvCountTimes.text = countTimes.toString()
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btnPlus -> timesUp()
            R.id.btnMinus -> timesDown()
            R.id.imgClose -> {
                navController.popBackStack()
                hideKeyboard()
            }
            R.id.imgChooseDay -> navController.navigate(R.id.chooseDayFragment)
        }
    }
}