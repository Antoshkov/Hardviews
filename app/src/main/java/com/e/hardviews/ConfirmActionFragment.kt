package com.e.hardviews

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProviders
import com.e.hardviews.CreateActionFragment.*

class ConfirmActionFragment : BaseFragment(), View.OnClickListener {
    private lateinit var viewModel: MainViewModel
    private lateinit var editText: EditText
    private lateinit var iconAction: ImageView
    private lateinit var imgClose: ImageView
    private lateinit var actionName: TextView
    private lateinit var tvCountSymbol: TextView
    private lateinit var tvCountTimes: TextView
    private lateinit var btnSaveTask: Button
    private lateinit var btnPlus: ImageButton
    private lateinit var btnMinus: ImageButton
    private var countTimes: Int = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_confirm_action, container, false)
        initViews(view)
        val textName = requireArguments().getString(ACTION_NAME)
        viewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
        iconAction.setBackgroundResource(requireArguments().getInt(ICON_ACTION))
        actionName.text = textName
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
        imgClose.setOnClickListener(this)
        btnPlus.setOnClickListener(this)
        btnMinus.setOnClickListener(this)
        btnSaveTask.setOnClickListener(this)
        return view
    }

    private fun initViews(view: View) {
        tvCountTimes = view.findViewById(R.id.tvTimePerDay)
        editText = view.findViewById(R.id.etActionName)
        imgClose = view.findViewById(R.id.imgClose)
        iconAction = view.findViewById(R.id.iconAction)
        actionName = view.findViewById(R.id.tvActionName)
        btnSaveTask = view.findViewById(R.id.btnSaveTask)
        tvCountSymbol = view.findViewById(R.id.tvCountSymbol)
        btnMinus = view.findViewById(R.id.btnMinus)
        btnPlus = view.findViewById(R.id.btnPlus)
        val progressMain = view.findViewById<CircularSeekBar>(R.id.progressMain)
        progressMain.setIsTouchEnabled(false)

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
            tvCountTimes.text = countTimes.toString()
        }
    }

    private fun timesDown() {
        if (countTimes > 1) {
            countTimes--
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
            R.id.btnSaveTask -> {
                val icon = requireArguments().getInt(ICON_ACTION)
                val iconRev = requireArguments().getInt(ICON_ACTION_REVERSE)
                viewModel.addNewAction(MyAction(actionName.text.toString(), icon, iconRev, countTimes))
                navController.navigate(R.id.mainFragment)
            }
        }
    }
}