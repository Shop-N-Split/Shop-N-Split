package com.akshaj02.shopnsplit

import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.Assert.assertEquals

class githubTest {
    fun test() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.akshaj02.shopnsplit", appContext.packageName)
    }
}