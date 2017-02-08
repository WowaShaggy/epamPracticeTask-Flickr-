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


    @Given("I choose browzer '$browzer'")
    public void IPrepare(String browzer){

        switch (browzer) {
            case "Firefox": {
                creator = new FirefoxDriverCreator();
                driver = creator.factoryMethod();
                break;

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
        driver.quit();
    }

    @When("I go to DetailsPage from photo")
    public void IGoToDetailsPageFromPhoto() {
        ExplorePage explorepage = PageFactory.initElements(driver,ExplorePage.class);
        DetailsPage detailspage = explorepage.getDetailsURLfromPhoto();
        String nanana= detailspage.getUrl();
        MyThreadLocal.get().setData(nanana);
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
        Assert.assertEquals(MyThreadLocal.get().getData(), detailspage.getUrl(),"Photo and title navigate to different page");
    }

    @Given("I am on ExplorePage")
    public void IAmOnExplorePage(){
        driver.navigate().to(ExplorePage.EXPLORE_PAGE_URL);
    }
    @When("I go to DetailsPage")
    public void IGoToDetailsLink() {
        ExplorePage explorepage = PageFactory.initElements(driver,ExplorePage.class);
        DetailsPage detailspage = explorepage.getDetailsURLfromPhoto();
    }
    @Then("I should check Title of Photo")
    public void IShouldCheckTitleOfPhoto() {
        DetailsPage detailspage = PageFactory.initElements(driver,DetailsPage.class);
        Assert.assertTrue(detailspage.getTitlePage().contains(detailspage.getTitlePhoto()));
        driver.quit();
    }

    @Then("I should check Author of Photo")
    public void IShouldCheckAuthorOfPhoto() {
        DetailsPage detailspage = PageFactory.initElements(driver,DetailsPage.class);
        Assert.assertTrue(detailspage.checkAuthorPhoto());
        driver.quit();
    }

    @Then("I should check Follow-button below Photo")
    public void IShouldCheckFollowButtonBelowPhoto() {
        DetailsPage detailspage = PageFactory.initElements(driver,DetailsPage.class);
        Assert.assertTrue(detailspage.checkFollowPhoto());
        driver.quit();
    }

    @Then("I should check Numbers of Views etc")
    public void IShouldCheckNumbersOfViewsETC() {
        DetailsPage detailspage = PageFactory.initElements(driver,DetailsPage.class);
        Assert.assertTrue(detailspage.checkNumbersPhoto());
        driver.quit();
    }

    @Then("I should check Date of Photo")
    public void IShouldCheckDateOfPhoto() {
        DetailsPage detailspage = PageFactory.initElements(driver,DetailsPage.class);
        Assert.assertTrue(detailspage.getDatePhoto());
        driver.quit();
    }

    @Then("I should check Rights of Photo")
    public void IShouldCheckRightsOfPhoto() {
        DetailsPage detailspage = PageFactory.initElements(driver,DetailsPage.class);
        Assert.assertTrue(detailspage.checkRightsPhoto());
        driver.quit();
    }

    @Then("I should check Camera details")
    public void IShouldCheckCameraDetails() {
        DetailsPage detailspage = PageFactory.initElements(driver,DetailsPage.class);
        Assert.assertTrue(detailspage.checkCameraDetailsPhoto());
        driver.quit();
    }

    @Then("I should check Tags of Photo")
    public void IShouldCheckTagsOfPhoto() {
        DetailsPage detailspage = PageFactory.initElements(driver,DetailsPage.class);
        Assert.assertTrue(detailspage.checkTagsDetailsPhoto());
        driver.quit();
    }

    @When("I go to AuthorsPage")
    public void IGoToAuthorsPage() {
        DetailsPage detailspage = PageFactory.initElements(driver,DetailsPage.class);
        AuthorsPage authorsPage = detailspage.goToAuthorsPage();
    }
    @Then("I should check Name on AuthorsPage")
    public void IShouldCheckNameOnAuthorsPage() {
        AuthorsPage authorsPage = PageFactory.initElements(driver,AuthorsPage.class);
        Assert.assertTrue(authorsPage.getTitle().contains(authorsPage.getName()));
        driver.quit();
    }

    @When("I go to Albums and check number of Albums")
    public void IGoToAlbumsAndCheckNumberOfAlbums() throws InterruptedException {
        AuthorsPage authorsPage = PageFactory.initElements(driver,AuthorsPage.class);
        authorsPage.goToAlbums();
        authorsPage.checkNumberOfAlbums();
    }
    @Then("I should check every Album")
    public void IShouldCheckEveryAlbum() {
        AuthorsPage authorsPage = PageFactory.initElements(driver,AuthorsPage.class);
        Assert.assertEquals(authorsPage.cicleOfAlbumChecks(),authorsPage.albumCounter(),"Not each album has all necessary items");
        driver.quit();
    }

    @When("I go to GroupPage")
    public void IGoToGroupPage() throws InterruptedException {
        AuthorsPage authorsPage = PageFactory.initElements(driver,AuthorsPage.class);
        GroupsPage groupsPage = authorsPage.goToGroupsPage();
    }
    @Then("I should check GroupPage Url")
    public void IShouldCheckGroupPageUrl() {
        GroupsPage groupsPage = PageFactory.initElements(driver,GroupsPage.class);
        Assert.assertEquals(driver.getCurrentUrl(),groupsPage.GROUPS_PAGE_URL,"Can't found Groups Page!");
        driver.quit();
    }

    @Given("I am on GroupPage")
    public void IAmOnGroupPage() {
        driver.navigate().to(GroupsPage.GROUPS_PAGE_URL);
    }
    @Then("I should check recommended group")
    public void IShouldCheckRecommendedGroup() {
        GroupsPage groupsPage = PageFactory.initElements(driver,GroupsPage.class);
        Assert.assertTrue(groupsPage.checkRecommendedGroups());
        driver.quit();
    }

    @When("I go to GalleriesPage")
    public void IGoToGalleriesPage(){
        GroupsPage groupsPage = PageFactory.initElements(driver,GroupsPage.class);
        GalleriesPage galleriesPage = groupsPage.goToGalleriesPage();
    }
    @Then("I should check GalleriesPage Url")
    public void IShouldCheckGalleriesPageUrl() {
        GalleriesPage galleriesPage = PageFactory.initElements(driver,GalleriesPage.class);
        Assert.assertEquals(driver.getCurrentUrl(),galleriesPage.GALLERIES_PAGE_URL,"Can't found Gallery Page");
        driver.quit();
    }

    @Given("I am on GalleriesPage")
    public void IAmOnGalleriesPage() {
        driver.navigate().to(GalleriesPage.GALLERIES_PAGE_URL);
    }
    @Then("I should check gallery's items")
    public void IShouldCheckGalleriesPageItems() {
        GalleriesPage galleriesPage = PageFactory.initElements(driver,GalleriesPage.class);
        Assert.assertTrue(galleriesPage.checkGallery());
        driver.quit();
    }

    @When("I go to SearchPage")
    public void IGoToSearchPage(){
        GalleriesPage galleriesPage = PageFactory.initElements(driver,GalleriesPage.class);
        SearchPage searchPage = galleriesPage.goToSearchPageByButton();
    }
    @Then("I should check SearchPage Url")
    public void IShouldCheckSearchPageUrl() {
        SearchPage searchPage = PageFactory.initElements(driver,SearchPage.class);
        Assert.assertEquals(searchPage.SEARCH_PAGE_URL, driver.getCurrentUrl(),"Can't found Gallery Page");
        driver.quit();
    }

    @Given("I am on SearchPage")
    public void IAmOnSearchPage() {
        driver.navigate().to(SearchPage.SEARCH_PAGE_URL);
    }
    @When ("I send request '$request'")
    public void ISendRequest(String request){
        SearchPage searchPage = PageFactory.initElements(driver,SearchPage.class);
        searchPage.sendRequest(request);
        MyThreadLocal.get().setData(request);
    }
    @Then("I should check First Photo Name")
    public void IShouldCheckFirstPhotoName() throws InterruptedException {
        SearchPage searchPage = PageFactory.initElements(driver,SearchPage.class);
        Assert.assertEquals(searchPage.getFirstTitle().contains(MyThreadLocal.get().getData()), true, "Your search returned incorrect results, possibly due Firefox!  ");
        driver.quit();
    }










}