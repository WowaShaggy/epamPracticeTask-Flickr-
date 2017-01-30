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

    public static WebDriver driver;
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

    public static WebDriver getWebDriver() {
        return driver;
    }

    /*
    @Override
    public void onTestFailure(ITestResult result) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        String methodName = result.getName();
        if (!result.isSuccess()) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/target/surefire-reports";
                File destFile = new File((String) reportDirectory + "/failure_screenshots/" + methodName + "_" + formater.format(calendar.getTime()) + ".png");
                FileUtils.copyFile(scrFile, destFile);
                Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='1000' width='1000'/> </a>");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
*/
}
