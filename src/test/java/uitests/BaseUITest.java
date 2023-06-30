package uitests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;
import ui.driver.DriverCreator;
import ui.pages.FilterPage;
import ui.pages.HomePage;
import ui.pages.LoginPage;
import utils.TestListener;

@Listeners(TestListener.class)
public class BaseUITest {
    protected WebDriver driver;
    protected SoftAssert softAssert;
    protected LoginPage loginPage;
    protected FilterPage filterPage;
    protected HomePage homePage;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        softAssert = new SoftAssert();
        driver = DriverCreator.getDriver();
        driver.get("http://localhost:8080");
        filterPage = new FilterPage(driver);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        loginPage.login();
        homePage.acceptAlert();
    }
}