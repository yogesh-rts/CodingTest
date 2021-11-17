package com.androidprojects.personal.codingtest


import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.EnumSet.allOf

@LargeTest
@RunWith(AndroidJUnit4::class)
class MedalCaseActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MedalCaseActivity::class.java)

    @Test
    fun medalCaseActivityTest() {

    }

    fun testTitleMatch(){
        onData(withText("Personal Records"))
        onData(withId(R.id.header_title))
    }
}
