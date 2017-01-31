package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends AbstractPage {

    public static final String SEARCH_PAGE_URL = "https://www.flickr.com/search/?q=";

    @FindBy(xpath = "//form[@method='GET']/input[@type='text']")
    public WebElement searchField;

    @FindBy(xpath = "//div[@data-track='autosuggestNavigate_searchPhotos']")
    public WebElement field;

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
            try {
                loupe.click();
                System.out.println("Search from loupe");
            }catch(WebDriverException e) {
                field.click();
                System.out.println("Search from field");

            }
        }

        return this;
    }

    public String getFirstTitle() throws InterruptedException {

        Thread.sleep(4000); // сюда бы JS

        WebDriverWait wait = new WebDriverWait(driver, 20);
        try {

            WebElement waitElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[starts-with(@class,'main search-photos-results')]/div/div[2]/div[1]")));
        }catch(TimeoutException eE){
            System.out.println("We can't navigate to necessary page");
        }
        try {
                WebElement waitElement = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//div[starts-with(@class,'main search-photos-results')]/div/div[2]/div[1]/div/div/a")));
        }catch(TimeoutException eE){
            System.out.println("We can't find to necessary photo");
        }
        return driver.findElement(By.xpath("//div[starts-with(@class,'main search-photos-results')]/div/div[2]/div[1]/div/div/a")).getAttribute("aria-label");
    }
}
