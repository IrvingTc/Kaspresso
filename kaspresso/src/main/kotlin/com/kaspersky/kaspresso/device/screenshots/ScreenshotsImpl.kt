package com.kaspersky.kaspresso.device.screenshots

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.device.Device
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.extensions.spoonext.UiAutomatorSpoon
import com.kaspersky.kaspresso.extensions.other.getStackTraceAsString
import com.squareup.spoon.Spoon

/**
 * Default implementation of Screenshots interface.
 */
class ScreenshotsImpl : Screenshots {

    private val logger: UiTestLogger = Configurator.logger

    /**
     * Makes screenshot if it is possible, otherwise logs the error.
     *
     * @param tag a unique tag to further identify the screenshot. Must match [a-zA-Z0-9_-]+.
     */
    override fun makeIfPossible(tag: String) {
        val resumedActivity = Device.activities.getResumed()

        try {
            resumedActivity?.let { Spoon.screenshot(it, tag) } ?: UiAutomatorSpoon.screenshot(tag)
        } catch (e: Throwable) {
            logger.e("An error while making screenshot occurred: ${e.getStackTraceAsString()}")
        }
    }
}