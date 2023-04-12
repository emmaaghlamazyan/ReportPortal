package uitests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;
import ui.driver.DriverSingleton;
import ui.pages.HomePage;
import utils.TestListener;

@Listeners(TestListener.class)
public class BaseUITest {
    protected WebDriver driver;
    protected SoftAssert softAssert;
    protected Logger log = LogManager.getRootLogger();
    protected HomePage homePage;

    @BeforeClass(alwaysRun = true)
    public void setUpAndLogin() {
        driver = DriverSingleton.getDriver();
    }
}
