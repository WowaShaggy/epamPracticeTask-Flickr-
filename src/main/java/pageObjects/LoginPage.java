package pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
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
                                                                                        //// Часть написанная для отловли ошибки , не робит
        boolean breakIt = true;
        while (true) {
            breakIt = true;
            try {
                loginButton.click();
            } catch (NoSuchElementException e) {
                if (e.getMessage().contains("no such element")) {
                    breakIt = false;
                }
            }
               catch (StaleElementReferenceException e) {
                if (e.getMessage().contains("stale element reference")) {
                    breakIt = false;
                }
            }
            if (breakIt) {
                break;
            }

        }
                                                                                         //// Часть написанная для отловли ошибки , не робит

        return PageFactory.initElements(driver,YahooPage.class);
    }


}
