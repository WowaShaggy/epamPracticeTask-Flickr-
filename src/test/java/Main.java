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

    @Test(description = "#6 [Explore]-link and checking photos with label '<photo_title> by <author>' ", priority = 6, enabled = false)
    public void ExploreLinkAndPhotoTest() { //С этим тестом вопросы: имя автора не отображается в названии изображения, только ник
        System.out.println("test 6");
        PhotosPage photospage = PageFactory.initElements(driver,PhotosPage.class);
        ExplorePage explorepage = photospage.goToExploreLink();

        System.out.println("Тестов пройдено:" + explorepage.cicleOfImagesChecks() + " из " + explorepage.itemsCount());

        Assert.assertEquals(explorepage.cicleOfImagesChecks(), explorepage.itemsCount());
    }

    @Test(description = "#7 Photo and its title navigate to the same page with other picture details", priority = 7)
    public void NavigateToDetailsTest(){
        System.out.println("test 7");
        PhotosPage photospage = PageFactory.initElements(driver,PhotosPage.class); //На случай если тест 6 заблокирован
        ExplorePage explorepage = photospage.goToExploreLink();
        //ExplorePage explorepage = PageFactory.initElements(driver,ExplorePage.class); //На случай если тест 6 доступен

        DetailsPage detailspage = explorepage.getDetailsURLfromPhoto();
        String Url = detailspage.getUrl();
        explorepage=detailspage.goBack();
        detailspage = explorepage.getDetailsURLfromTitle();

        Assert.assertEquals(Url,detailspage.getUrl());
    }

    @AfterTest(description = "WebDriver clean up")
    public void cleanUp(){
        //driver.close();
    }

}
