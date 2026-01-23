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
import internal.GlobalVariable
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.chrome.ChromeDriver


public class WebDriverFactory {
    @Keyword
    def createDriver(String browserType, boolean isHeadless, String deviceName = "") {
        WebDriver driver
        if (browserType.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions()
            if (isHeadless) {
                // Sử dụng headless mode chuẩn cho các bản Chrome mới
                options.addArguments("--headless=new", "--window-size=1920,1080", "--disable-gpu")
            }
            if (deviceName != "") {
                Map<String, String> mobileEmulation = new HashMap<>()
                mobileEmulation.put("deviceName", deviceName)
                options.setExperimentalOption("mobileEmulation", mobileEmulation)
            }
            // Khởi tạo trực tiếp, Katalon 10.x sẽ tự tìm đường dẫn Driver
            driver = new ChromeDriver(options)
            DriverFactory.changeWebDriver(driver)
        }
        return driver
    }
}