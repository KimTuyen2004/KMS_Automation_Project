package components

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
import internal.GlobalVariable
import core.SmartWait
import org.openqa.selenium.By



class SideMenu {

    @Keyword
    def open() {
        def menuBtn = findTestObject('Cura/SideMenu/btnMenu')
        assert menuBtn != null : '❌ btnMenu TestObject NOT FOUND'

        WebUI.waitForElementClickable(menuBtn, 10)
        WebUI.click(menuBtn)
    }

    @Keyword
    def logout() {
        open()

        def logoutBtn = findTestObject('Cura/SideMenu/linkLogout')
        assert logoutBtn != null : '❌ linkLogout TestObject NOT FOUND'

        WebUI.waitForElementClickable(logoutBtn, 10)
        WebUI.click(logoutBtn)
    }
}
