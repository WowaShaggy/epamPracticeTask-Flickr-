package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PhotosPage extends AbstractPage {

    public static final String PHOTOS_PAGE_URL = "https://www.flickr.com/photos/146737136@N08/";

    @FindBy(css = "h1.truncate")
    public WebElement userName;

    @FindBy(css = "a[data-track='gnExploreMainClick']")
    public WebElement exploreLink;

    public PhotosPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getName () {

            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement waitElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='truncate']")));

        return userName;
    }


    public ExplorePage goToExploreLink() {
        exploreLink.click();

        return PageFactory.initElements(driver,ExplorePage.class);
    }
}
