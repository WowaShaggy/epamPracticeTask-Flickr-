package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthorsPage extends AbstractPage{

    public AuthorsPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement waitElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='truncate']")));

        return driver.getTitle();
    }

    public String getName() {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement waitElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='truncate']")));

        return driver.findElement(By.xpath("//h1[@class='truncate']")).getAttribute("innerText");
    }

    public AuthorsPage goToAlbums() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement waitElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='links']/li[@id='albums']")));

        driver.findElement(By.xpath("//ul[@class='links']/li[@id='albums']/a")).click();

        return this;
    }

    public AuthorsPage checkNumberOfAlbums() throws InterruptedException {

        Thread.sleep(5000);///////////////////////////////////////Поменять на JS ожидание?

        try {
            WebDriverWait wait = new WebDriverWait(driver, 20);
            WebElement waitElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='main']/div")));

            waitElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='main']/div/div[1]")));
                return this;
            }catch (NoSuchElementException e){
                System.out.println("There are no albums");
                return this;
            }
    }

    public int albumCounter(){
        return driver.findElements(By.xpath("//div[@class='view photo-list-view']/div")).size();
    }

    public boolean checkThumbnail(int iC) {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement waitElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='main']/div/div["+iC+"]")));

        try {
            if (driver.findElement(By.xpath("//div[@role='main']/div/div["+iC+"]")).getAttribute("style").contains("background-image")){
            return true;}
            else{
                System.out.println("else-error:  "+iC+"-album has no Thumbnail");
                return false;
            }
        }catch (NoSuchElementException e){
            System.out.println("catch-error:"+iC+"-album has no Thumbnail");
            return false;
        }
    }

    public boolean checkTitle(int iC) {
        try {
            if (!driver.findElement(By.xpath("//div[@role='main']/div/div["+iC+"]/a")).getAttribute("title").isEmpty()){
                return true;}
            else{
                System.out.println(iC+"-album has no Title");
                return false;
            }
        }catch (NoSuchElementException e){
            System.out.println(iC+"-album has no Title");
            return false;
        }
    }

    public boolean checkLink(int iC) {
        try {
            if (!driver.findElement(By.xpath("//div[@role='main']/div/div["+iC+"]/a")).getAttribute("href").isEmpty()){
                return true;}
            else{
                System.out.println(iC+"-album has no Link");
                return false;
            }
        }catch (NoSuchElementException e){
            System.out.println(iC+"-album has no Link");
            return false;
        }
    }

    public boolean checkNumberOfPhotos(int iC) {
        try {
            if (!driver.findElement(By.xpath("//div[@role='main']/div/div["+iC+"]//div[@class='metadata']/span[@class='album-photo-count secondary']")).getAttribute("innerText").isEmpty()){
                return true;}
            else{
                System.out.println(iC+"-album has no Photos");
                return false;
            }
        }catch (NoSuchElementException e){
            System.out.println(iC+"-album has no Photos");
            return false;
        }
    }

    public boolean checkNumberOfViews(int iC) {
        try {
            if (!driver.findElement(By.xpath("//div[@role='main']/div/div["+iC+"]//div[@class='metadata']//span[@class='album-views-label secondary']")).getAttribute("innerText").isEmpty()){
                return true;}
            else{
                System.out.println(iC+"-album has no Views");
                return false;
            }
        }catch (NoSuchElementException e){
            System.out.println(iC+"-album has no Views");
            return false;
        }
    }

    public int cicleOfAlbumChecks() {
        int albumPassCount=0;

        for (int iC = 1; iC <= albumCounter(); iC++) {
            if(checkThumbnail(iC) == true && checkTitle(iC)==true && checkLink(iC)==true && checkNumberOfPhotos(iC)==true && checkNumberOfViews(iC)==true ) albumPassCount++;
        }
        return albumPassCount;
    }


}
