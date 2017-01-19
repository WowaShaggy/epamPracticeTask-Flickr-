package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractPage {

    public static final String HOME_PAGE_URL = "https://www.flickr.com/";

    public static final String HOME_PAGE_TITLE = "Home | Flickr";

    public static final int HOME_PAGE_ITEM_NUMBER = 3;
    public static final String HOME_PAGE_ITEM_1 = "You";
    public static final String HOME_PAGE_ITEM_2 = "Explore";
    public static final String HOME_PAGE_ITEM_3 = "Create";


    @FindBy(xpath="//ul[@class='nav-menu']")
    public WebElement navMenu;

    @FindBy(css = "a[data-track='gnYouMainClick']")
    public WebElement youLink;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public int itemsCount () {
        return navMenu.findElements(By.xpath("li")).size();
    }

    public int subMenuItemsCount (int i) {
        return navMenu.findElements(By.xpath("li["+i+"]/ul/li")).size();
    }

    public String[] itemsArray () {
        WebElement menuitem;
        String [] array = new String[itemsCount()];

        for(int i=1; i <= itemsCount();i++ ){
            By p = new By.ByXPath("li[" +i+"]");
            menuitem = navMenu.findElement(p);
            array[i-1]=menuitem.getAttribute("aria-label");
        }
        return array;
    }

    public boolean subMenuLinks () {

        for (int mC=1; mC<=itemsCount ();mC++ )
            for (int smC =1; smC<=subMenuItemsCount(mC);smC++) {
                try {
                    navMenu.findElement(By.xpath("li["+mC+"]/ul/li[" + smC + "]/a"));
                   // System.out.println("#"+smC);
                } catch (NoSuchElementException e) {
                    return false;
                }
            }
        return true;
    }

    public PhotosPage goToYouLink() {
        youLink.click();

        return PageFactory.initElements(driver,PhotosPage.class);
    }

}
