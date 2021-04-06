package com.e.hardviews

import android.util.Log
import com.e.hardviews.AppDatabase
import com.e.hardviews.App
import com.e.hardviews.MyActionsDBDao
import com.e.hardviews.R
import kotlinx.coroutines.*
import java.util.ArrayList

class MainModel {

    private val ioScope = CoroutineScope(Job() + Dispatchers.IO + CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.e("coroutineTag", throwable.message ?: "")
    })

    private var db = App.getInstance().database
    private var actionsDBDao = db.actionsDBDao()
    private val CREATE_ACTION = "Add a Task"
    private lateinit var actions: MutableList<Action>
    private var defaultActions: List<Action>


    private fun createDefaultActions(): List<Action> {
        val defaultActionList: MutableList<Action> = ArrayList()
        defaultActionList.add(Action("brush teeth", R.drawable.ic_tooth, R.drawable.ic_tooth_reverse, 1, Action.HEALTH))
        defaultActionList.add(Action("walk the dog", R.drawable.ic_dog_side, R.drawable.ic_dog_side_reverse, 1, Action.TIME))
        defaultActionList.add(Action("Running", R.drawable.ic_run_fast_reverse, R.drawable.ic_run_fast, 1, Action.HEALTH))
        defaultActionList.add(Action("Take a vitamins", R.drawable.ic_pill_reverse, R.drawable.ic_pill, 1, Action.HEALTH))
        defaultActionList.add(Action("Homework", R.drawable.ic_lead_pencil_reverse, R.drawable.ic_lead_pencil, 1, Action.TIME))
        defaultActionList.add(Action("Read a book", R.drawable.ic_bookshelf_reverse, R.drawable.ic_bookshelf, 1, Action.TIME))
        defaultActionList.add(Action("Cook a chiken", R.drawable.ic_food_turkey_reverse, R.drawable.ic_food_turkey, 1, Action.FOOD))
        defaultActionList.add(Action("Take a food", R.drawable.ic_pasta_reverse, R.drawable.ic_pasta, 1, Action.FOOD))
        defaultActionList.add(Action("No alcohol", R.drawable.ic_glass_cocktail_reverse, R.drawable.ic_glass_cocktail, 1, Action.BAD_HABITS))
        defaultActionList.add(Action("No smoking", R.drawable.ic_smoking_off_reverse, R.drawable.ic_smoking_off, 1, Action.BAD_HABITS))
        return defaultActionList
    }

    private fun insertIntoDB(someAction: Action) {
        ioScope.launch {
            actionsDBDao.insert(someAction)
        }
    }

    private fun deleteFromDB(chosenAction: Action) {
        ioScope.launch {
            actionsDBDao.delete(chosenAction)
        }
    }

    fun getActions(): List<Action> {
        return actions
    }

    fun getDefaultActions(): List<Action> {
        return defaultActions
    }

    fun addActionForCreate() {
        var checkCreator = false
        for (i in actions.indices) {
            val action = actions[i]
            if (action.isCreator) checkCreator = true
        }
        if (!checkCreator) {
            val creator = Action(CREATE_ACTION, R.drawable.ic_plus_thick, R.drawable.ic_plus_thick, 1, 1)
            creator.isCreator = true
            actions.add(creator)
        }
    }

    fun deleteActionForCreate() {
        for (i in actions.indices) {
            val action = actions[i]
            if (action.nameAction == CREATE_ACTION) {
                actions.remove(action)
            }
        }
    }

    fun addNewAction(newAction: Action) {
        insertIntoDB(newAction)
        ioScope.launch {
            actions = actionsDBDao.all
        }
    }

    fun editAction(chosenAction: Action) {
        insertIntoDB(chosenAction)
        ioScope.launch {
            actions = actionsDBDao.all
        }
    }

    fun deleteAction(action: Action) {
        deleteFromDB(action)
        ioScope.launch {
            actions = actionsDBDao.all
        }
    }

    init {
        ioScope.launch {
            actions = actionsDBDao.all
        }
        defaultActions = createDefaultActions()
    }
}