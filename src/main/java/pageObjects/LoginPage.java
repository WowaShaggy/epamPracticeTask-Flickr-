package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage {

    public static final String LOGIN_PAGE_URL = "https://www.flickr.com/";

    @FindBy(xpath ="//a[@data-track='gnSignin']")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver,this);
    }

    public YahooPage loginClick(){
        loginButton.click();
        return PageFactory.initElements(driver,YahooPage.class);
    }


}
