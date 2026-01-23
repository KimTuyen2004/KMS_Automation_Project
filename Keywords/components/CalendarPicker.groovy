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
import org.openqa.selenium.By
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import com.kms.katalon.core.testobject.ConditionType


class CalendarPicker {

    static void selectDatePlusDays(int plusDays) {

        LocalDate targetDate = LocalDate.now().plusDays(plusDays)
        String day = targetDate.format(DateTimeFormatter.ofPattern('d'))
        String monthYear = targetDate.format(DateTimeFormatter.ofPattern('MMMM yyyy'))

      
        WebUI.delay(1)

     
        while (true) {
            String header = WebUI.getText(
                new com.kms.katalon.core.testobject.TestObject()
                    .addProperty("xpath", com.kms.katalon.core.testobject.ConditionType.EQUALS,
                        "//div[@class='datepicker-days']//th[@class='datepicker-switch']")
            )

            if (header == monthYear) {
                break
            }

            WebUI.click(
                new com.kms.katalon.core.testobject.TestObject()
                    .addProperty("xpath", com.kms.katalon.core.testobject.ConditionType.EQUALS,
                        "//div[@class='datepicker-days']//th[@class='next']")
            )
        }

        // Select day
        WebUI.click(
            new com.kms.katalon.core.testobject.TestObject()
                .addProperty("xpath", com.kms.katalon.core.testobject.ConditionType.EQUALS,
                    "//td[not(contains(@class,'old')) and not(contains(@class,'new')) and text()='" + day + "']")
        )
    }
}
