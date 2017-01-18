package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExplorePage extends AbstractPage {

    public static final String EXPLORE_PAGE_URL = "https://www.flickr.com/explore";
    public String title, name, author;

    @FindBy(xpath = "//div[@class='view photo-list-view']")
    public WebElement photosFrame;

    @FindBy(xpath = "//div[@class='view photo-list-view']/div[1]")
    public WebElement photoPath;

    @FindBy(xpath = "//div[@class='view photo-list-view']/div[1]//a[@role='heading']")
    public WebElement photoLinkPath;

    @FindBy(xpath = "//div[@class='view photo-list-view']/div[1]//div[@class='text']/a[1]")
    public WebElement titlePath;


    public ExplorePage(WebDriver driver) {
        super(driver);
    }

    public int itemsCount() {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement waitElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='view photo-list-view']")));//копия photosFrame

        return photosFrame.findElements(By.xpath("//div[@class='interaction-view']")).size();

    }

    public String getImageTitle(int iC) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement waitElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='view photo-list-view']/div["+iC+"]"))); // Для второй

        return photosFrame.findElement(By.xpath("div["+iC+"]//a[@class='overlay']")).getAttribute("aria-label");
    }

    public WebElement getImageName(int iC) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement waitElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='view photo-list-view']/div[" + iC + "]")));
            return photosFrame.findElement(By.xpath("div["+iC+"]//div[@class='text']/a[1]"));
        }catch (NoSuchElementException e){
            System.out.println(iC+"-image has no name");
            return photosFrame.findElement(By.xpath("div["+iC+"]//div[@class='text']/span[1]"));
        }
    }

    public WebElement getImageAuthor(int iC) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement waitElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='view photo-list-view']/div["+iC+"]")));
            return photosFrame.findElement(By.xpath("div["+iC+"]//a[@class='attribution']"));
        }catch (NoSuchElementException e){
            System.out.println(iC+"-image' author has no name");
            return photosFrame.findElement(By.xpath("div["+iC+"]//div[@class='text']/span[2]"));
        }
    }

    public int cicleOfImagesChecks() {
        int imageCounter=0;

        for (int iC = 1; iC <= itemsCount(); iC++) {

                title = getImageTitle(iC);
               // System.out.println(title);
                name = getImageName(iC).getAttribute("innerText");
                //System.out.println(name);
                author = getImageAuthor(iC).getAttribute("innerText");
                //System.out.println(author);
                //System.out.println();

            //System.out.println(name+" "+author);
            if(title.equals(name+" "+author))imageCounter++; // Проверка имени
        }
        return imageCounter;
    }

    public DetailsPage getDetailsURLfromPhoto(){

            WebDriverWait wait = new WebDriverWait(driver, 20);
            WebElement waitElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='view photo-list-view']/div[1]")));


/*
           WebElement we = driver.findElement(By.xpath("//a[@data-track='gnLogoClick']"));

           if (driver instanceof JavascriptExecutor) {
               ((JavascriptExecutor)driver).executeScript("$(arguments[0]).mouseenter()",driver.findElement(By.xpath("//a[@data-track='gnLogoClick']")));
           } else {
               throw new IllegalStateException("This driver does not support JavaScript!");
           }*/

        Actions action = new Actions(driver);
           WebElement we = driver.findElement(By.xpath("//a[@data-track='gnLogoClick']"));
           action.moveToElement(we).moveToElement(photoPath).moveToElement(photoLinkPath).click().build().perform();



        return PageFactory.initElements(driver,DetailsPage.class);
    }

    public DetailsPage getDetailsURLfromTitle(){

        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement waitElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='view photo-list-view']/div[1]")));

        Actions action = new Actions(driver);
        WebElement we = driver.findElement(By.xpath("//div[@class='view photo-list-view']/div[1]"));
        action.moveToElement(we).moveToElement(photoPath).moveToElement(titlePath).click().build().perform();;

        return PageFactory.initElements(driver,DetailsPage.class);
    }
}

