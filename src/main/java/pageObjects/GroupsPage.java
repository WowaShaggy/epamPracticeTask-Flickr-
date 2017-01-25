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

public class GroupsPage extends AbstractPage {

    public static final String GROUPS_PAGE_URL = "https://www.flickr.com/groups";

    @FindBy(xpath="//div[@class='recommended-groups clear-float']/div[1]")
    public WebElement firstRecommendedGroup;

    public GroupsPage(WebDriver driver) {
        super(driver);
    }

    public boolean checkRecommendedGroups() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement waitElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='recommended-groups clear-float']/div[1]")));

        if(checkButton() == true && checkTitle() == true) return true;

        return false;
    }

    private boolean checkTitle() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement waitElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='recommended-groups clear-float']/div[1]//div[@class='text-content']/div[@data-track='groupCardOtherClick']")));
        try {
            if (!firstRecommendedGroup.findElement(By.xpath("//div[@class='text-content']/div[@data-track='groupCardOtherClick']")).getAttribute("innerText").isEmpty()){
                return true;}
            else{
                System.out.println("else-error:  There is no title");
                return false;
            }
        }catch (NoSuchElementException e){
            System.out.println("catch-error:  There is no title");
            return false;
        }
    }

    private boolean checkButton() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement waitElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='recommended-groups clear-float']/div[1]//div[@class='button-content']//button")));

        try {
            if (firstRecommendedGroup.findElement(By.xpath("//div[@class='button-content']//button")).getAttribute("innerText").contains("Join")){
                return true;}
            else{
                System.out.println("else-error:  There is no button");
                return false;
            }
        }catch (NoSuchElementException e){
            System.out.println("catch-error: There is no button");
            return false;
        }
    }

        public GalleriesPage goToGalleriesPage() {

            WebDriverWait wait = new WebDriverWait(driver, 20);
            WebElement waitElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@data-context='explore']")));

            Actions action = new Actions(driver);
            WebElement we = driver.findElement(By.xpath("//li[@data-context='explore']"));
            WebElement we2 = driver.findElement(By.xpath("//li[@data-context='explore']//a[@data-track='gnExploreGalleriesClick']"));
            action.moveToElement(we).moveToElement(we2).click().build().perform();

            waitElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-track='edit-relationship']")));

            return PageFactory.initElements(driver,GalleriesPage.class);
        }
}
