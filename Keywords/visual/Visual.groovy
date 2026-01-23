package visual
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

import com.kms.katalon.core.configuration.RunConfiguration

import java.nio.file.Files
import java.nio.file.Paths
import javax.imageio.ImageIO
import java.awt.image.BufferedImage

class Visual {

    @Keyword
    static void compareSummaryPage() {
		
     
        String projectDir = RunConfiguration.getProjectDir()

    
        String baseFolder = projectDir + "/Data Files/VisualTesting"
        String baselineFolder = baseFolder + "/Baseline"
        String actualFolder   = baseFolder + "/Actual"

      
        Files.createDirectories(Paths.get(baselineFolder))
        Files.createDirectories(Paths.get(actualFolder))

    
        String imageName = "SummaryPage.png"

        // Path
        String baselinePath = baselineFolder + "/" + imageName
        String actualPath   = actualFolder + "/" + imageName

        // ===== LẦN ĐẦU: TẠO BASELINE =====
        if (!new File(baselinePath).exists()) {
            println(">>> Creating BASELINE image")
            WebUI.takeScreenshot(baselinePath)
        }
        // ===== CÁC LẦN SAU: CHỤP ACTUAL =====
        else {
            println(">>> Capturing ACTUAL image")
            WebUI.takeScreenshot(actualPath)
        }

        // ================= IMAGE COMPARISON =================

        BufferedImage baselineImg = ImageIO.read(new File(baselinePath))
        BufferedImage actualImg   = ImageIO.read(new File(actualPath))

        // Check size first
        if (baselineImg.getWidth() != actualImg.getWidth() ||
            baselineImg.getHeight() != actualImg.getHeight()) {
            
            WebUI.comment("❌ UI SIZE CHANGED")
            assert false : "UI size changed → Visual Regression FAILED"
        }

        int width = baselineImg.getWidth()
        int height = baselineImg.getHeight()
        int step = 5;
        long diffPixels = 0

        for (int y = 0; y < height; y+=step) {
            for (int x = 0; x < width; x+=step) {
                if (baselineImg.getRGB(x, y) != actualImg.getRGB(x, y)) {
                    diffPixels++
                }
            }
        }

        double totalPixels = width * height
        double diffPercent = (diffPixels / totalPixels) * 100

        println("Diff pixels: " + diffPixels)
        println("Diff percent: " + diffPercent + "%")

      
        double threshold = 0.5   // 0.5%

        if (diffPercent > threshold) {
            WebUI.comment("❌ UI CHANGED - Visual Regression FAILED")
            assert false : "Visual diff = " + diffPercent + "% > threshold " + threshold + "%"
        } else {
            WebUI.comment(" UI MATCH - Visual Regression PASSED")
        }
    }
}
