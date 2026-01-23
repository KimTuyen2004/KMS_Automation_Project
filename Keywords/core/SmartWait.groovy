package core

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.support.ui.ExpectedConditions
import java.time.Duration
import org.openqa.selenium.*
import org.openqa.selenium.support.ui.*
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import org.openqa.selenium.Point


class SmartWait {

    static void waitVisible(TestObject to, int timeout = 10) {
        WebUI.waitForElementVisible(to, timeout, FailureHandling.STOP_ON_FAILURE)
    }

    static void waitClickable(TestObject to, int timeout = 10) {
        long start = System.currentTimeMillis()
        boolean clicked = false

        while ((System.currentTimeMillis() - start) < timeout * 1000) {
            try {
                if (WebUI.waitForElementVisible(to, 1, FailureHandling.OPTIONAL)
                        && WebUI.waitForElementClickable(to, 1, FailureHandling.OPTIONAL)) {

                    WebUI.click(to, FailureHandling.OPTIONAL)
                    clicked = true
                    break
                }
            } catch (Exception e) {
                WebUI.delay(0.3)
            }
        }

        if (!clicked) {
            WebUI.comment("SmartWait FAILED: Element not clickable after ${timeout}s")
            WebUI.click(to) // force fail
        }
    }
}
