package com.example.moody

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginFragmentTest: TestCase() {

    private lateinit var scenario: FragmentScenario<LoginFragment>

    @Before

    fun setup(){
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_Moody)
        scenario.moveToState(Lifecycle.State.STARTED )
    }

    @Test
    fun testLoginValid(){
        val username  = "username1"
        val password = "12345678"
        onView(withId(R.id.editusernameText)).perform(typeText(username))
        onView(withId(R.id.editpasswordText)).perform(typeText(password))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.loginbutton)).perform(click())
        //assertThat(onView(withId()))
    }

}