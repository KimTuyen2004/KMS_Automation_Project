import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.model.FailureHandling
import core.SmartWait
import visual.Visual



// ================================
// 1. VERIFY SUMMARY PAGE
// ================================
SmartWait.waitVisible(
    findTestObject('Cura/Summary/lbl_Confirm_Title'),
    20
)

WebUI.verifyElementText(
    findTestObject('Cura/Summary/lbl_Confirm_Title'),
    'Appointment Confirmation'
)

// ================================
// 2. GET RAW TEXT
// ================================
String facilityRaw = WebUI.getText(findTestObject('Cura/Summary/lbl_Facility_Value'))
String readmissionRaw = WebUI.getText(findTestObject('Cura/Summary/lbl_Readmission_Value'))
String programRaw = WebUI.getText(findTestObject('Cura/Summary/lbl_Program_Value'))
String visitDateRaw = WebUI.getText(findTestObject('Cura/Summary/lbl_VisitDate_Value'))
String commentRaw = WebUI.getText(findTestObject('Cura/Summary/lbl_Comment_Value'))

// ================================
// 3. CLEAN VALUE
// ================================
String facility = facilityRaw.replace('Facility', '').trim()
String readmission = readmissionRaw.replace('Apply for hospital readmission', '').trim()
String program = programRaw.replace('Healthcare Program', '').trim()
String visitDate = visitDateRaw.replace('Visit Date', '').trim()
String comment = commentRaw.replace('Comment', '').trim()

// ================================
// 4. VERIFY DATA
// ================================
WebUI.verifyEqual(facility, 'Hongkong CURA Healthcare Center')
WebUI.verifyEqual(readmission, 'Yes')
WebUI.verifyEqual(program, 'Medicaid')
WebUI.verifyMatch(visitDate, '\\d{2}/\\d{2}/\\d{4}', true)
WebUI.verifyEqual(comment, 'Katalon Advanced Automation')

// UI BASELINE CHECK
Visual.compareSummaryPage()

// ================================
// 5. BACK TO HOMEPAGE (animation)
// ================================
SmartWait.waitClickable(
    findTestObject('Cura/Summary/btn_GoToHomepage'),
    15
)
