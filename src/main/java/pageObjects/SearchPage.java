package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends AbstractPage {

    public static final String SEARCH_PAGE_URL = "https://www.flickr.com/search/?q=";

    @FindBy(xpath = "//form[@method='GET']/input[@type='text']")
    public WebElement searchField;


    @FindBy(xpath = "//input[@class='autosuggest-selectable-item no-outline']")
    public WebElement loupeField;

    @FindBy(xpath = "//div[@class='search-box-container']//button[@type='submit']")
    public WebElement searchButton;

    @FindBy(xpath = "//input[@data-track='gnSearchSearchIcon']")
    public WebElement loupe;



    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public SearchPage sendRequest(String s) {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement waitElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//form[@method='GET']/input[@type='text']")));

    
        searchField.click();
        searchField.sendKeys(s);
        try{
            searchButton.click();
            System.out.println("Search from button");
        }catch(ElementNotVisibleException ex){
            loupe.click();
            System.out.println("Search from loupe");
        }

        return this;
    }

    public String getFirstTitle() throws InterruptedException {

        Thread.sleep(3000);

        try {
            WebDriverWait wait = new WebDriverWait(driver, 20);
            WebElement waitElement = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='main search-photos-results']/div/div[2]/div[1]")));
        }catch(TimeoutException eE){
            System.out.println("something is wrong! Try again");
        }

        return driver.findElement(By.xpath("//div[@class='main search-photos-results']/div/div[2]/div[1]/div/div/a")).getAttribute("aria-label");
    }
}
