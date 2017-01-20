package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends AbstractPage {

    public static final String SEARCH_PAGE_URL = "https://www.flickr.com/search/?q=";

    @FindBy(xpath = "//input[@class='search-box']")
    public WebElement searchField;

    @FindBy(xpath = "//div[@class='search-box-container']//button[@type='submit']")
    public WebElement searchButton;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public SearchPage sendRequest(String s) {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement waitElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@class='search-box']")));

        searchField.click();
        searchField.sendKeys(s);

        waitElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));

        searchButton.click();

        return this;
    }
}
