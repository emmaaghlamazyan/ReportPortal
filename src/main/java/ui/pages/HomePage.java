package ui.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[text()='Filters']/parent::span")
    private WebElement filters;

    public void openFiltersPage() {
        filters.click();
    }

    public void acceptAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
}