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
import core.SmartWait
import org.openqa.selenium.By
import com.kms.katalon.core.model.FailureHandling
import core.SmartWait


// ================================
// 1. VERIFY APPOINTMENT PAGE
// ================================
SmartWait.waitVisible(
    findTestObject('Cura/Appointment/lbl_MakeAppointment'),
    20
)

// ================================
// 2. FACILITY (dropdown)
// ================================
SmartWait.waitClickable(
    findTestObject('Cura/Appointment/sel_Facility'),
    15
)

WebUI.selectOptionByLabel(
    findTestObject('Cura/Appointment/sel_Facility'),
    'Hongkong CURA Healthcare Center',
    false
)

// ================================
// 3. HOSPITAL READMISSION
// ================================
SmartWait.waitClickable(
    findTestObject('Cura/Appointment/chk_HospitalReadmission'),
    10
)

// ================================
// 4. HEALTHCARE PROGRAM
// ================================
SmartWait.waitClickable(
    findTestObject('Cura/Appointment/rad_Medicaid'),
    10
)

// ================================
// 5. VISIT DATE (Calendar – animation)
// ================================
SmartWait.waitClickable(
    findTestObject('Cura/Appointment/txt_VisitDate'),
    20
)

CustomKeywords.'components.CalendarPicker.selectDatePlusDays'(30)

// ================================
// 6. COMMENT
// ================================
SmartWait.waitVisible(
    findTestObject('Cura/Appointment/txt_Comment'),
    10
)

WebUI.setText(
    findTestObject('Cura/Appointment/txt_Comment'),
    'Katalon Advanced Automation'
)

// ================================
// 7. BOOK APPOINTMENT
// ================================
SmartWait.waitClickable(
    findTestObject('Cura/Appointment/btn_BookAppointment'),
    20
)
