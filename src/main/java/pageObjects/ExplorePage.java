package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExplorePage extends AbstractPage {

    public static final String EXPLORE_PAGE_URL = "https://www.flickr.com/explore";

    @FindBy(css = "div[class='view photo-list-view']")
    public WebElement allPhotos;

    public ExplorePage(WebDriver driver) {
        super(driver);
    }

    public int itemsCount () {

        WebDriverWait wait = new WebDriverWait(driver, 50);
        WebElement waitElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("interaction-bar")));

        return allPhotos.findElements(By.cssSelector("div.interaction-bar")).size();// может выкинуть это вверх, в аннтоацию
    }

    public String getImageTitle () {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            WebElement waitElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.className("overlay")));// поменять попробовать на более точный

        return allPhotos.findElement(By.cssSelector("div.interaction-bar")).getAttribute("title");// может выкинуть это вверх, в аннтоацию
    }

    public WebElement getImageName () {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement waitElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("title")));

        return allPhotos.findElement(By.cssSelector("div.interaction-bar div a.title"));// может выкинуть это вверх, в аннтоацию
    }

    public WebElement getImageAuthor () {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement waitElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("attribution")));

        return allPhotos.findElement(By.cssSelector("div.interaction-bar div a.attribution")); // может выкинуть это вверх, в аннтоацию
    }
}
