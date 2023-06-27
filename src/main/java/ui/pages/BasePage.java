package ui.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor js;
    protected Actions actions;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    protected void waitUntilClickable(WebElement webElement) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    protected void waitUntilVisible(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    protected void waitUntilSizeIsBiggerThan(String locator, Integer size) {
        List<WebElement> until = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(locator), size));
    }

    public void waitUntil() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}