package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

    public static final String HOME_PAGE_URL = "https://www.flickr.com/";

    @FindBy(xpath="//ul[@class='nav-menu']")
    public WebElement navMenu;

    public HomePage(WebDriver driver) {
        super(driver);
    }


    public int itemsCount () {
        return navMenu.findElements(By.xpath("li")).size();
    }

    public String[] itemsArray () {
        WebElement pop3;
        String [] array = new String[itemsCount()];

        for(int i=1; i <= itemsCount();i++ ){
            By p = new By.ByXPath("li[" +i+"]");
            pop3 = navMenu.findElement(p);
            array[i-1]=pop3.getAttribute("aria-label");
        }
        return array;
    }



}
