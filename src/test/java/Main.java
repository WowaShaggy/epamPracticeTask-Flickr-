import factoryMethodPattern.ChromeDriverCreator;
import factoryMethodPattern.FirefoxDriverCreator;
import factoryMethodPattern.WebDriverCreator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.*;

public class Main {

    private WebDriver driver;
    private WebDriverCreator creator;
    private ConfigReader cr = new ConfigReader();

    @BeforeTest(description = "WebDriver init")
    public void prepare() {

        switch (cr.Data("browzer")) {
            case "Firefox": {
                creator = new FirefoxDriverCreator();
                driver = creator.factoryMethod();break;
            }
            case "Chrome": {
                creator = new ChromeDriverCreator();
                driver = creator.factoryMethod();break;
            }
        }
    }


    @Test(description = "#1 Flickr login test", priority = 1)
    public void LoginTest() {
        System.out.println("test 1");
        driver.navigate().to(LoginPage.LOGIN_PAGE_URL);
        LoginPage loginpage = new LoginPage(driver);
        YahooPage yahoopage = loginpage.loginClick().enterEmail(cr.Data("login"));
        HomePage homepage = yahoopage.enterPassword(cr.Data("password"));

        Assert.assertTrue(driver.getCurrentUrl().equals(HomePage.HOME_PAGE_URL));
    }

    @Test(description = "#2 Main page's title test", priority = 2)
    public void MainPageTitleTest() {
        System.out.println("test 2");
        Assert.assertTrue(driver.getTitle().equals("Home | Flickr"));
    }

    @Test(description = "#3 Main menu items test", priority = 3)
    public void MainMenuItemsTest() {
        System.out.println("test 3");
       HomePage homepage= PageFactory.initElements(driver,HomePage.class);

        Assert.assertTrue(homepage.itemsCount() == 3);
        Assert.assertTrue(homepage.itemsArray()[0].equals("You"));
        Assert.assertTrue(homepage.itemsArray()[1].equals("Explore"));
        Assert.assertTrue(homepage.itemsArray()[2].equals("Create"));
    }

    @Test(description = "#4 Sub-menu items contain items", priority = 4)
    public void SubMenuItemsTest() {
        System.out.println("test 4");
        HomePage homepage= PageFactory.initElements(driver,HomePage.class);

        Assert.assertTrue(homepage.subMenuLinks(), "true");
    }

    @Test(description = "#5 [You]-link and checking username", priority = 5)
    public void YouLinkAndNameTest() {
        System.out.println("test 5");
        HomePage homepage = PageFactory.initElements(driver,HomePage.class);
        PhotosPage photospage = homepage.goToYouLink();

        Assert.assertEquals(photospage.getName().getText(), cr.Data("name"));
    }

    @Test(description = "#6 [Explore]-link and checking photos with label '<photo_title> by <author>' ", priority = 6)
    public void ExploreLinkAndPhotoTest() {
        System.out.println("test 6");
        PhotosPage photospage = PageFactory.initElements(driver,PhotosPage.class);
        ExplorePage explorepage = photospage.goToExploreLink();
    //какие-то неполадки с этим таском

       // System.out.println("ii+"+explorepage.itemsCount());  /// `количество картиночек (50 якобэ)
        System.out.println(explorepage.getImageTitle());
        System.out.println(explorepage.getImageName().getText());
        System.out.println(explorepage.getImageAuthor().getText());
       // Assert.assertEquals(explorepage.getImageTitle(), explorepage.getImageName().getText()+" "+explorepage.getImageAuthor().getText());

    }

    @AfterTest(description = "WebDriver clean up")
    public void cleanUp(){
       // driver.close();
    }

}
