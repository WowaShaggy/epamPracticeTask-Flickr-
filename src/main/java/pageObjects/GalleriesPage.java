package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GalleriesPage extends AbstractPage{

    public static final String GALLERIES_PAGE_URL = "https://www.flickr.com/photos/flickr/galleries";

    @FindBy(xpath="//div[@id='gl-thumb-group']/div[1]")
    public WebElement firstGallery;

    @FindBy(xpath = "//input[@type='submit']")
    public WebElement loupe;

    @FindBy(xpath = "//input[@type='text']")
    public WebElement field;

    public GalleriesPage(WebDriver driver) {
        super(driver);
    }

    public boolean checkGallery() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement waitElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='gl-thumb-group']/div[1]")));

        if(checkImage() == true && checkTitle() == true && checkPhotosNumber() == true) return true;

        return false;
    }

    private boolean checkPhotosNumber() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement waitElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='gl-thumb-group']/div[1]")));

        try {
            if (firstGallery.findElement(By.xpath("div[@class='gallery-case-meta']/p[1]")).getAttribute("innerText").contains("photo")){
                return true;}
            else{
                System.out.println("else-error: There is no photos");
                return false;
            }
        }catch (NoSuchElementException e){
            System.out.println("catch-error: There is no photos");
            return false;
        }
    }

    private boolean checkTitle() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement waitElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='gl-thumb-group']/div[1]")));

        try {
            if (!firstGallery.findElement(By.xpath("div[@class='gallery-case-meta']/h4/a")).getAttribute("title").isEmpty()){
                return true;}
            else{
                System.out.println("else-error: There is no title");
                return false;
            }
        }catch (NoSuchElementException e){
            System.out.println("catch-error: There is no title");
            return false;
        }
    }

    private boolean checkImage() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement waitElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='gl-thumb-group']/div[1]//div[@class='setLinkDiv']")));

        try {
            if (!firstGallery.findElement(By.xpath("//div[@class='setLinkDiv']//img")).getAttribute("src").isEmpty()){
                return true;}
            else{
                System.out.println("else-error: There is no image");
                return false;
            }
        }catch (NoSuchElementException e){
            System.out.println("catch-error: There is no image");
            return false;
        }
    }

    public SearchPage goToSearchPageByButton() {
        loupe.click();
        return PageFactory.initElements(driver,SearchPage.class);
    }

    public SearchPage goToSearchPageByField() {
        field.click();
        return PageFactory.initElements(driver,SearchPage.class);
    }
}
