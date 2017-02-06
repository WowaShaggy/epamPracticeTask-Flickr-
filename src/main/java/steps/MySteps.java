package steps;

import factoryMethodPattern.ChromeDriverCreator;
import factoryMethodPattern.FirefoxDriverCreator;
import factoryMethodPattern.WebDriverCreator;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pageObjects.*;


public class MySteps{

    protected WebDriver driver;
    protected WebDriverCreator creator;
    ScenarioContext sc;


    @Given("I choose browzer '$browzer'")
    public void IPrepare(String browzer) {

        switch (browzer) {
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

    @Given("I login as '$login' with password '$password'")
    public void ILogin(String login, String password) {
        driver.navigate().to(LoginPage.LOGIN_PAGE_URL);
        LoginPage loginpage = new LoginPage(driver);
        YahooPage yahoopage = loginpage.loginClick().enterEmail(login);
        HomePage homepage = yahoopage.enterPassword(password);
    }


    @Then("I should check url")
    public void IShouldCheckUrl() {
        HomePage homepage = PageFactory.initElements(driver, HomePage.class);
        Assert.assertEquals(driver.getCurrentUrl(), homepage.HOME_PAGE_URL, "Login has failed");
        driver.quit();
    }


    @Then("I should check title")
    public void IShouldCheckTitle() {
        HomePage homepage = PageFactory.initElements(driver, HomePage.class);
        Assert.assertEquals(driver.getTitle(),homepage.HOME_PAGE_TITLE, "Home Page has another title");
        driver.quit();
    }


    @Then("I should check number of menu's items")
    public void IShouldCheckNumberOfMenusItems() {
        HomePage homepage = PageFactory.initElements(driver, HomePage.class);
        Assert.assertEquals(homepage.itemsCount(),homepage.HOME_PAGE_ITEM_NUMBER,"Menu items' number is incorrect");
        driver.quit();
    }


    @Then("I should compare names of menu's items")
    public void IShouldCompareItemsName() {
        HomePage homepage = PageFactory.initElements(driver, HomePage.class);
        Assert.assertEquals(homepage.itemsArray()[0] + homepage.itemsArray()[1] + homepage.itemsArray()[2],homepage.HOME_PAGE_ITEM_1 + homepage.HOME_PAGE_ITEM_2 + homepage.HOME_PAGE_ITEM_3,"Menu items have other names");
        driver.quit();
    }


    @Then("I should check that Sub-menu items contain links")
    public void IShouldCheckThatSbMenuItemsContainLinks() {
        HomePage homepage = PageFactory.initElements(driver, HomePage.class);
        Assert.assertEquals(homepage.subMenuLinks(), true,"Not each of Submenu items has link" );
        driver.quit();
    }


    @When("I go to [You]-link")
    public void IGoToYouLink() {
        HomePage homepage = PageFactory.initElements(driver, HomePage.class);
        PhotosPage photospage = homepage.goToYouLink();
    }
    @Then("I should compare username with '$name'")
    public void IShouldCheckYsername(String name) {
        PhotosPage photospage = PageFactory.initElements(driver, PhotosPage.class);
        Assert.assertEquals(photospage.getName().getText(), name, "Username is not right!");
        driver.quit();
    }


    @Given("I am on PhotoPage")
    public void IAmOnPhotoPage(){
        driver.navigate().to(PhotosPage.PHOTOS_PAGE_URL);
    }
    @When("I go to [Explore]-link")
    public void IGoToExploreLink() {
        PhotosPage photospage = PageFactory.initElements(driver,PhotosPage.class);
        ExplorePage explorepage = photospage.goToExploreLink();
    }
    @Then("I should check photos")
    public void IShouldCheckPhotos() {
        ExplorePage explorepage = PageFactory.initElements(driver,ExplorePage.class);
        int numberPassedTests = explorepage.cicleOfImagesChecks();
        int numberAllTests = explorepage.itemsCount();
        System.out.println("Тестов пройдено:" + numberPassedTests + " из " + numberAllTests);
        Assert.assertEquals(numberPassedTests, numberAllTests,"Not each photo passes test!");
    }


    @Given("I am on ExplorePage")
    public void IAmOnExplorePage(){
        driver.navigate().to(ExplorePage.EXPLORE_PAGE_URL);
    }
    @When("I go to DetailsPage from photo")
    public void IGoToDetailsPageFromPhoto() {
        ExplorePage explorepage = PageFactory.initElements(driver,ExplorePage.class);
        DetailsPage detailspage = explorepage.getDetailsURLfromPhoto();
        System.out.println("ok");
        String nanana= detailspage.getUrl();
        System.out.println("ok+"+nanana);
        sc.addTrade(new Trade (nanana));
        System.out.println("ok2+"+ sc.getFirstTrade());
        explorepage = detailspage.goBack();
    }
    @When("I go to DetailsPage from title")
    public void IGoToDetailsPageFromTitle() {
        ExplorePage explorepage = PageFactory.initElements(driver,ExplorePage.class);
        DetailsPage detailspage = explorepage.getDetailsURLfromTitle();
    }
    @Then("I should compare urls")
    public void IShouldCompareUrls() {
        DetailsPage detailspage = PageFactory.initElements(driver,DetailsPage.class);
        Assert.assertEquals(sc.getFirstTrade(),detailspage.getUrl(),"Photo and title navigate to different page");
    }

}