import factoryMethodPattern.ChromeDriverCreator;
import factoryMethodPattern.FirefoxDriverCreator;
import factoryMethodPattern.WebDriverCreator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.YahooPage;

public class Main {

    private WebDriver driver;
    private WebDriverCreator creator;
    private ConfigReader cr = new ConfigReader();

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


    @Test(description = "#1 Flickr login test")
    public void LoginTest() {
        driver.navigate().to(LoginPage.LOGIN_PAGE_URL);
        LoginPage loginpage = new LoginPage(driver);
        YahooPage yahoopage = loginpage.loginClick().enterEmail(cr.Data("login"));
        HomePage homepage = yahoopage.enterPassword(cr.Data("password"));

        Assert.assertTrue(driver.getCurrentUrl().equals(HomePage.HOME_PAGE_URL));
    }

    @Test(description = "#2 Main page's title test")
    public void MainPageTitleTest() {

        Assert.assertTrue(driver.getTitle().equals("Home | Flickr"));
    }

    @Test(description = "#3 Main menu items test")
    public void MainMenuItems() {
       HomePage homepage= PageFactory.initElements(driver,HomePage.class);

        Assert.assertTrue(homepage.itemsCount() == 3);
        Assert.assertTrue(homepage.itemsArray()[0].equals("You"));
        Assert.assertTrue(homepage.itemsArray()[1].equals("Explore"));
        Assert.assertTrue(homepage.itemsArray()[2].equals("Create"));
    }

    @AfterTest(description = "WebDriver clean up")
    public void cleanUp(){
        driver.quit();
    }

}
