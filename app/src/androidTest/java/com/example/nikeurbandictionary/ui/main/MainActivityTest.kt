package com.example.nikeurbandictionary.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.nikeurbandictionary.R
import org.hamcrest.CoreMatchers.not
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest{

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun shouldHaveItemsInside(){
        val mainEditText = onView(ViewMatchers
            .withId(R.id.etWordSearch))
        val mainRecyclerView = onView(withId(R.id.rvWordList))

        mainEditText.perform(ViewActions
            .replaceText("house"))

        mainRecyclerView.check { view, noViewFoundException ->
            noViewFoundException?.apply {
                throw this
            }
            assertTrue(view is RecyclerView &&
                    view.adapter != null && view.adapter?.itemCount?:-1 > 0
            )

        }
    }

    @Test
    fun shouldNotBeEmpty(){
        val mainEditText = onView(withId(R.id.etWordSearch))

        mainEditText.perform(ViewActions
            .replaceText("hello"))

        mainEditText.check(matches(not("")))
    }

    @Test
    fun shouldDisplayCustomDialog(){
        val mainButton = onView(withId(R.id.ibSort))
        val dialogButton = onView(withId(R.id.rbLikes))

        mainButton.perform(ViewActions.click())

        dialogButton.check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }
}