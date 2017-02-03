package steps;

import factoryMethodPattern.ChromeDriverCreator;
import factoryMethodPattern.FirefoxDriverCreator;
import factoryMethodPattern.WebDriverCreator;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.YahooPage;


public class MySteps {

    protected WebDriver driver;
    protected WebDriverCreator creator;

    @Given("I choose browzer '$browzer'")
    public void IPrepare(String browzer) {

        switch (browzer) {
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

    @Given("I login as '$login' with password '$password'")
    public void ILogin(String login, String password) {
        driver.navigate().to(LoginPage.LOGIN_PAGE_URL);
        LoginPage loginpage = new LoginPage(driver);
        YahooPage yahoopage = loginpage.loginClick().enterEmail(login);
        HomePage homepage = yahoopage.enterPassword(password);
    }


    @Then("I should check url")
    public void IShouldCheckUrl() {
        HomePage homepage = PageFactory.initElements(driver, HomePage.class);
        Assert.assertEquals(driver.getCurrentUrl(), homepage.HOME_PAGE_URL, "Login has failed");
        driver.quit();
    }


    @Then("I should check number of menu's items")
    public void IShouldCheckNumberOfMenusItems() {
        HomePage homepage = PageFactory.initElements(driver, HomePage.class);
        Assert.assertEquals(homepage.itemsCount(),homepage.HOME_PAGE_ITEM_NUMBER,"Menu items' number is incorrect");
        driver.quit();
    }

}