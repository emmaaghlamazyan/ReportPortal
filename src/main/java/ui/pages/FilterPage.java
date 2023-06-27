package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FilterPage extends BasePage {
    public FilterPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".filter-button")
    private WebElement filterButton;

    @FindBy(css = "input[name='filter-name']")
    private WebElement filterNameInput;

    @FindBy(css = "select[name='condition']")
    private WebElement conditionDropdown;

    @FindBy(css = "select[name='condition'] option[value='equals']")
    private WebElement equalsOption;

    @FindBy(css = "input[name='filter-value']")
    private WebElement filterValueInput;

    @FindBy(css = ".save-filter-button")
    private WebElement saveFilterButton;

    @FindBy(css = ".delete-filter-button")
    private WebElement deleteFilterButton;

    @FindBy(css = ".confirm-delete-button")
    private WebElement confirmDeleteButton;

    public FilterPage clickOnFilterButton() {
        filterButton.click();
        return this;
    }

    public FilterPage fillInFilterName(String filterName) {
        filterNameInput.sendKeys(filterName);
        return this;
    }

    public FilterPage clickOnConditionDropDown() {
        conditionDropdown.click();
        return this;
    }

    public FilterPage clickOnEqualsOption() {
        equalsOption.click();
        return this;
    }

    public FilterPage fillInFilterValue(String filterValue) {
        filterValueInput.sendKeys(filterValue);
        return this;
    }

    public FilterPage clickOnSaveFilterButton() {
        saveFilterButton.click();
        return this;
    }

    public boolean verifyFilterIsDisplayed(String filterName) {
        return driver.findElement(By.xpath(String.format("//span[text()='%s']", filterName))).isDisplayed();
    }

    public FilterPage clickOnFilterElement(String filterName) {
        driver.findElement(By.xpath(String.format("//span[text()='%s']", filterName))).click();
        return this;
    }

    public FilterPage deleteFilter() {
        deleteFilterButton.click();
        confirmDeleteButton.click();
        return this;
    }
}
