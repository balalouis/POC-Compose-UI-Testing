package com.basic.template.myapplication

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.basic.template.myapplication.screen.SampleScreen
import com.basic.template.myapplication.ui.theme.MyApplicationTheme
import leakcanary.DetectLeaksAfterTestSuccess
import leakcanary.LeakAssertions
import org.junit.Rule
import org.junit.Test

class SampleScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    val rule = DetectLeaksAfterTestSuccess()

    @Test
    fun testLoginFields() {
        composeTestRule.setContent {
            MyApplicationTheme {
                SampleScreen()
            }
        }
        composeTestRule.onNodeWithText("Email").assertIsDisplayed()
        composeTestRule.onNodeWithText("Password").assertIsDisplayed()
        composeTestRule.onNodeWithText("Age").assertIsDisplayed()
        composeTestRule.onNodeWithText("Male").assertIsDisplayed()
        composeTestRule.onNodeWithText("Female").assertIsDisplayed()
        composeTestRule.onNodeWithText("Registration").assertIsDisplayed()
        LeakAssertions.assertNoLeaks()
    }

}

