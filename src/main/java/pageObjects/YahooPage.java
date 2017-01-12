package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YahooPage extends AbstractPage{

    public static final String YAHOO_PAGE_URL = "https://login.yahoo.com/config/login?.src=flickrsignin&.pc=8190&.scrumb=0&.pd=c%3DH6T9XcS72e4mRnW3NpTAiU8ZkA--&.intl=by&.lang=en&mg=1&.done=https%3A%2F%2Flogin.yahoo.com%2Fconfig%2Fvalidate%3F.src%3Dflickrsignin%26.pc%3D8190%26.scrumb%3D0%26.pd%3Dc%253DJvVF95K62e6PzdPu7MBv2V8-%26.intl%3Dby%26.done%3Dhttps%253A%252F%252Fwww.flickr.com%252Fsignin%252Fyahoo%252F%253Fredir%253Dhttps%25253A%25252F%25252Fwww.flickr.com%25252F";

    public YahooPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath="//input[@id='login-username']")
    private WebElement emailArea;

    @FindBy(xpath="//input[@id='login-passwd']")
    private WebElement passwordArea;

    @FindBy(xpath="//button[@id='login-signin']")
    private WebElement loginButton;



    public YahooPage enterEmail(String s) {

            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement waitElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='login-username']")));

        emailArea.sendKeys(s);
        loginButton.click();
        return this;
    }

    public HomePage enterPassword(String s) {

            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement waitElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='forgotLnk']")));

        passwordArea.sendKeys(s);
        passwordArea.submit();

            waitElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='search-field']")));

        return PageFactory.initElements(driver,HomePage.class);
    }
}


