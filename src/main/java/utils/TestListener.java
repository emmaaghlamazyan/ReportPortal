package utils;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ui.driver.DriverCreator;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        saveScreenshot();
    }

    private void saveScreenshot() {
        try {
            TakesScreenshot ts = (TakesScreenshot) DriverCreator.getDriver();
            File srcFile = ts.getScreenshotAs(OutputType.FILE);
            java.util.Date d = new java.util.Date();
            org.apache.commons.io.FileUtils.copyFile(srcFile, new File("./ScreenShots/" + d.toString().replace(":", "_") + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}