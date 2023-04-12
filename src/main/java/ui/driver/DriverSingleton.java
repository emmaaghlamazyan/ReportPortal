package ui.driver;

import org.openqa.selenium.WebDriver;

public class DriverSingleton {

    private static WebDriver driver;

    private DriverSingleton() {
    }

    public static WebDriver getDriver() {
        DriverDecorator decorator;
        if (null == driver) {
            switch (System.getProperty("browser", "chrome")) {
                case "firefox": {
                    decorator = new FirefoxDriverCreator();
                    break;
                }
                default: {
                    decorator = new ChromeDriverCreator();
                }
            }
            driver = decorator.createWebDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }
}