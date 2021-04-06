package com.e.hardviews

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProviders
import com.e.hardviews.CreateActionFragment.*
import com.e.hardviews.MainFragment.CHOSEN_ACTION


abstract class BaseConfirmActionFragment : BaseFragment(), View.OnClickListener {
    lateinit var viewModel: MainViewModel
    lateinit var editText: EditText
    lateinit var iconAction: ImageView
    lateinit var imgClose: ImageView
    lateinit var imgChooseDay: ImageView
    lateinit var actionName: TextView
    lateinit var tvCountSymbol: TextView
    lateinit var tvCountTimes: TextView
    lateinit var btnSaveTask: Button
    lateinit var btnPlus: ImageButton
    lateinit var btnMinus: ImageButton
    lateinit var constButton: ConstraintLayout
    lateinit var piecesView: CreatePiecesView
    var action: Action? = null
    var countTimes: Int = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        action = requireArguments().getParcelable(CHOSEN_ACTION)
        val textName = action?.nameAction
        viewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
        action?.let { iconAction.setBackgroundResource(it.iconAction) }
        actionName.text = textName
        piecesView.setPaintColor(getThemeBackgroundColor())
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
    }



    private fun getThemeBackgroundColor(): Int {
        val typedValue = TypedValue()
        requireActivity().theme.resolveAttribute(android.R.attr.windowBackground, typedValue, true)
        return if (typedValue.type >= TypedValue.TYPE_FIRST_COLOR_INT
                && typedValue.type <= TypedValue.TYPE_LAST_COLOR_INT) typedValue.data else Color.RED
    }

    private val mTextEditorWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            tvCountSymbol.text = "${s.length}/28"
        }
        override fun afterTextChanged(s: Editable) {}
    }

    private fun timesUp() {
        if (countTimes < 10) {
            countTimes++
            piecesView.setAmountTimes(countTimes)
            tvCountTimes.text = countTimes.toString()
        }
    }

    private fun timesDown() {
        if (countTimes > 1) {
            countTimes--
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