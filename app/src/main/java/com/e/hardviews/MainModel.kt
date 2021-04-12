package com.e.hardviews

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.*
import java.util.*

class MainModel {

    private var db = App.getInstance().database
    private var actionsDBDao = db.actionsDBDao()
    private var defaultActions: List<Action>
    private val ioScope = CoroutineScope(Job() + Dispatchers.IO
            + CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.e("coroutineTag", throwable.message ?: "")
    })

    init { defaultActions = createDefaultActions() }

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

    fun getAllFromDB(): LiveData<List<Action>> = actionsDBDao.all

    fun getDefaultActions(): List<Action> = defaultActions

    fun addNewAction(newAction: Action) {
        ioScope.launch { actionsDBDao.insert(newAction) }
    }

    fun editAction(chosenAction: Action) {
        ioScope.launch { actionsDBDao.insert(chosenAction) }
    }

    fun deleteAction(action: Action) {
        ioScope.launch { actionsDBDao.delete(action) }
    }
}