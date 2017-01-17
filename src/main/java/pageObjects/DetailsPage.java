package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class DetailsPage extends AbstractPage {


    public DetailsPage(WebDriver driver) {
        super(driver);
    }

    public String getUrl(){
        return driver.getCurrentUrl();
    }

    public ExplorePage goBack(){
        driver.navigate().back();
        return PageFactory.initElements(driver,ExplorePage.class);
    }

}
