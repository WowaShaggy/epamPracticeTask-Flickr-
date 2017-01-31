import factoryMethodPattern.ChromeDriverCreator;
import factoryMethodPattern.FirefoxDriverCreator;
import factoryMethodPattern.WebDriverCreator;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.YahooPage;

public class Conditions {

    protected WebDriver driver;
    protected WebDriverCreator creator;
    protected ConfigReader cr = new ConfigReader();

    @BeforeTest(description = "WebDriver init")
    public void prepare() {

        switch (cr.Data("browzer")) {
            case "Firefox": {
                creator = new FirefoxDriverCreator();
                driver = creator.factoryMethod();break;

            }
            case "Chrome": {
                creator = new ChromeDriverCreator();
                driver = creator.factoryMethod();break;
            }

        }
    }

    @BeforeTest(description = "#1 Flickr login test", dependsOnMethods ="prepare")
    public void LoginTest() {
        System.out.println("test 1");
        driver.navigate().to(LoginPage.LOGIN_PAGE_URL);
        LoginPage loginpage = new LoginPage(driver);
        YahooPage yahoopage = loginpage.loginClick().enterEmail(cr.Data("login"));
        HomePage homepage = yahoopage.enterPassword(cr.Data("password"));

        Assert.assertEquals(driver.getCurrentUrl(),HomePage.HOME_PAGE_URL,"Login has failed");
    }

    @AfterTest(description = "WebDriver clean up")
    public void cleanUp(){
        //driver.close();
    }


    public WebDriver getDriver() {
        return driver;
    }
}
