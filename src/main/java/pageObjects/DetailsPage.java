package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DetailsPage extends AbstractPage {

    @FindBy(xpath = "//div[@class='view sub-photo-left-view']")
    public WebElement photoView;

    @FindBy(xpath = "//div[@class='view sub-photo-tags-view']")
    public WebElement photoTags;

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

    public String getTitlePhoto() {
        try {
                WebDriverWait wait = new WebDriverWait(driver, 10);
                WebElement waitElement = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='view sub-photo-left-view']//h1[@class=' meta-field photo-title ']")));
            return photoView.findElement(By.xpath("//h1[@class=' meta-field photo-title ']")).getAttribute("innerText");

        } catch (NoSuchElementException e) {
            return "Photo has no title";
        }
    }

    public boolean checkAuthorPhoto() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement waitElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='view sub-photo-left-view']//a[@data-track='attributionNameClick']")));

            return true;

        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String getTitlePage() {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement waitElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='ui-icon-download']")));
        try {
            return driver.getTitle();
        } catch (NoSuchElementException e) {
            return "Page has no title";
        }
    }

    public boolean checkFollowPhoto() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement waitElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='view sub-photo-left-view']//span[@class='relationship']/button")));

            return true;

        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean checkNumbersPhoto() {
        try {
                WebDriverWait wait = new WebDriverWait(driver, 10);
                WebElement waitElement = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='view-count']")));
                waitElement = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='fave-count']")));
                waitElement = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='comment-count']")));

            return true;

        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean getDatePhoto() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement waitElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='date-taken-label']")));

            String examleDate = driver.findElement(By.xpath("//span[@class='date-taken-label']")).getAttribute("innerText");

            try {
                SimpleDateFormat format = new SimpleDateFormat("'Taken on' MMM dd, yyyy");
                format.setLenient(false);
                format.parse(examleDate);
                //System.out.println(format.parse(examleDate));
                return true;
            } catch (ParseException ex) {
                System.out.println(ex.getStackTrace());
                System.out.println(ex.getMessage());
                System.out.println("invalid date");
                return false;
            }

        } catch (NoSuchElementException e) {
            System.out.println("Photo has no date");
            return false;
        }
    }

    public boolean checkRightsPhoto() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement waitElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='view photo-license-view']//a")));

            return true;

        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean checkCameraDetailsPhoto() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement waitElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='view photo-charm-exif-scrappy-view']/div/ul/div[1]/li")));

            return true;

        } catch (TimeoutException e) {
            System.out.println("There is no camera details");
            return false;
        }
    }

    public boolean checkTagsDetailsPhoto() {
        int i=1;
        try {
            while (i<= driver.findElements(By.xpath("//div[@class='view sub-photo-tags-view']//ul[@class='tags-list']/li")).size()){
                WebDriverWait wait = new WebDriverWait(driver, 15);
                WebElement waitElement;
                waitElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[starts-with(@class,'view sub-photo-tags-view')]//ul[@class='tags-list']/li["+i+"]/a")));
                i++;
            }
            return true;

        } catch (NoSuchElementException e) {
            System.out.println("I think and I hope you are never see this message because that will mean, that something is wrong with tags");
            return false;
        }
    }


    public AuthorsPage goToAuthorsPage() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement waitElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='view sub-photo-left-view']//div[@class='attribution-info']/a[1]")));

        driver.findElement(By.xpath("//div[@class='view sub-photo-left-view']//div[@class='attribution-info']/a[1]")).click();

        return PageFactory.initElements(driver,AuthorsPage.class);

    }
}
