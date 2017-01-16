package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExplorePage extends AbstractPage {

    public static final String EXPLORE_PAGE_URL = "https://www.flickr.com/explore";

    @FindBy(xpath = "//div[@class='view photo-list-view']")   // рамка типа (Железно)
    // @FindBy(xpath = "//div[@class='view photo-list-view requiredToShowOnServer']")
    public WebElement photosFrame;

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
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement waitElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='view photo-list-view']/div["+iC+"]"))); // Для второй

        return photosFrame.findElement(By.xpath("div["+iC+"]//a[@class='overlay']")).getAttribute("aria-label");
    }

    public WebElement getImageName(int iC) {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement waitElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='view photo-list-view']/div["+iC+"]")));

        return photosFrame.findElement(By.xpath("div["+iC+"]//div[@class='text']/a[1]"));
    }

    public WebElement getImageAuthor(int iC) {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement waitElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='view photo-list-view']/div["+iC+"]")));

        return photosFrame.findElement(By.xpath("div["+iC+"]//a[@class='attribution']"));
    }

    //Неиспользуемый метод для теста #6
    public boolean cicleOfImagesChecks() {
        String title, name, author;

        for (int iC = 1; iC <= itemsCount(); iC++) {
            try {
                title = getImageTitle(iC);
                //System.out.println(title);
                name = getImageName(iC).getAttribute("innerText");
                //System.out.println(name);
                author = getImageAuthor(iC).getAttribute("innerText");
                //System.out.println(author);
                //System.out.println();
            } catch (NoSuchElementException e) {
                return false;
            }
        }
        return true;
    }

}
