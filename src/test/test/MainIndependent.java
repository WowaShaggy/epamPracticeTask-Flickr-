import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.*;

@Listeners(ScreenshotListener.class)
public class MainIndependent extends Conditions {

    @Test(description = "#2 Main page's title test")
    public void MainPageTitleTest() {
        System.out.println("test 2");
        driver.navigate().to(HomePage.HOME_PAGE_URL);
        HomePage homepage= PageFactory.initElements(driver,HomePage.class);

        Assert.assertEquals(driver.getTitle(),homepage.HOME_PAGE_TITLE, "Home Page has another title");
    }

    @Test(description = "#3.1 Main menu items count test")
    public void MainMenuItemsContTest() {
        System.out.println("test 3.1");
        driver.navigate().to(HomePage.HOME_PAGE_URL);
        HomePage homepage= PageFactory.initElements(driver,HomePage.class);

        Assert.assertEquals(homepage.itemsCount(),homepage.HOME_PAGE_ITEM_NUMBER,"Menu items' number is incorrect");
    }

    @Test(description = "#3.2 Main menu items test")
    public void MainMenuItemsTest() {
        System.out.println("test 3.2");
        driver.navigate().to(HomePage.HOME_PAGE_URL);
        HomePage homepage= PageFactory.initElements(driver,HomePage.class);
        String resultingName = homepage.itemsArray()[0] + homepage.itemsArray()[1] + homepage.itemsArray()[2];
        String expectedName = homepage.HOME_PAGE_ITEM_1 + homepage.HOME_PAGE_ITEM_2 + homepage.HOME_PAGE_ITEM_3;

        Assert.assertEquals(resultingName,expectedName,"Menu items have other names");
    }

    @Test(description = "#4 Sub-menu items contain links")
    public void SubMenuItemsTest() {
        System.out.println("test 4");
        driver.navigate().to(HomePage.HOME_PAGE_URL);
        HomePage homepage= PageFactory.initElements(driver,HomePage.class);

        Assert.assertEquals(homepage.subMenuLinks(), true,"Not each of Submenu items has link" );
    }

    @Test(description = "#5 [You]-link and checking username")
    public void YouLinkAndNameTest() {
        System.out.println("test 5");
        driver.navigate().to(HomePage.HOME_PAGE_URL);
        HomePage homepage = PageFactory.initElements(driver,HomePage.class);
        PhotosPage photospage = homepage.goToYouLink();

        Assert.assertEquals(photospage.getName().getText(), cr.Data("name"), "Username is not right!");
    }

    @Test(description = "#6 [Explore]-link and checking photos with label '<photo_title> by <author>' ")
    public void ExploreLinkAndPhotoTest() {
        System.out.println("test 6");
        driver.navigate().to(PhotosPage.PHOTOS_PAGE_URL);
        PhotosPage photospage = PageFactory.initElements(driver,PhotosPage.class);
        ExplorePage explorepage = photospage.goToExploreLink();
        int numberPassedTests = explorepage.cicleOfImagesChecks();
        int numberAllTests = explorepage.itemsCount();

        System.out.println("Тестов пройдено:" + numberPassedTests + " из " + numberAllTests);

        Assert.assertEquals(numberPassedTests, numberAllTests,"Not each photo passes test!");
    }

    @Test(description = "#7 Photo and its title navigate to the same page with other picture details")
    public void NavigateToDetailsTest(){   /*для одной фотографии*/
        System.out.println("test 7");
        driver.navigate().to(ExplorePage.EXPLORE_PAGE_URL);
        ExplorePage explorepage = PageFactory.initElements(driver,ExplorePage.class);
        DetailsPage detailspage = explorepage.getDetailsURLfromPhoto();
        String Url = detailspage.getUrl();
        explorepage = detailspage.goBack();
        detailspage = explorepage.getDetailsURLfromTitle();

        Assert.assertEquals(Url,detailspage.getUrl(),"Photo and title navigate to different page");
    }

    @Test(description = "#8.1.1 Page with photo details contains every all necessary items (Title)")
    public void DetailsTitleTest(){
        System.out.println("test 8.1.1");

        driver.navigate().to(ExplorePage.EXPLORE_PAGE_URL);
        ExplorePage explorepage = PageFactory.initElements(driver,ExplorePage.class);
        DetailsPage detailspage = explorepage.getDetailsURLfromPhoto();

        Assert.assertTrue(detailspage.getTitlePage().contains(detailspage.getTitlePhoto()));
    }

    @Test(description = "#8.1.2 Page with photo details contains every all necessary items (Author)")
    public void DetailsAuthorTest(){
        System.out.println("test 8.1.2");
        driver.navigate().to(ExplorePage.EXPLORE_PAGE_URL);
        ExplorePage explorepage = PageFactory.initElements(driver,ExplorePage.class);
        DetailsPage detailspage = explorepage.getDetailsURLfromPhoto();


        Assert.assertTrue(detailspage.checkAuthorPhoto());
    }

    @Test(description = "#8.2 Page with photo details contains every all necessary items (Follow-button)")
    public void DetailsFollowTest(){
        System.out.println("test 8.2");
        driver.navigate().to(ExplorePage.EXPLORE_PAGE_URL);
        ExplorePage explorepage = PageFactory.initElements(driver,ExplorePage.class);
        DetailsPage detailspage = explorepage.getDetailsURLfromPhoto();

        Assert.assertTrue(detailspage.checkFollowPhoto());
    }

    @Test(description = "#8.3 Page with photo details contains every all necessary items (number of views, faves, comments)")
    public void DetailsNumbersTest(){
        System.out.println("test 8.3");
        driver.navigate().to(ExplorePage.EXPLORE_PAGE_URL);
        ExplorePage explorepage = PageFactory.initElements(driver,ExplorePage.class);
        DetailsPage detailspage = explorepage.getDetailsURLfromPhoto();

        Assert.assertTrue(detailspage.checkNumbersPhoto());
    }

    @Test(description = "#8.4 Page with photo details contains every all necessary items (Date of photo)")
    public void DetailsDateTest(){
        System.out.println("test 8.4");
        driver.navigate().to(ExplorePage.EXPLORE_PAGE_URL);
        ExplorePage explorepage = PageFactory.initElements(driver,ExplorePage.class);
        DetailsPage detailspage = explorepage.getDetailsURLfromPhoto();

        Assert.assertTrue(detailspage.getDatePhoto());
    }

    @Test(description = "#8.5 Page with photo details contains every all necessary items (Rights link)")
    public void DetailsRightsTest(){
        System.out.println("test 8.5");
        driver.navigate().to(ExplorePage.EXPLORE_PAGE_URL);
        ExplorePage explorepage = PageFactory.initElements(driver,ExplorePage.class);
        DetailsPage detailspage = explorepage.getDetailsURLfromPhoto();

        Assert.assertTrue(detailspage.checkRightsPhoto());
    }

    @Test(description = "#8.6 Page with photo details contains every all necessary items (Camera)")
    public void DetailsCameraTest(){
        System.out.println("test 8.6");
        driver.navigate().to(ExplorePage.EXPLORE_PAGE_URL);
        ExplorePage explorepage = PageFactory.initElements(driver,ExplorePage.class);
        DetailsPage detailspage = explorepage.getDetailsURLfromPhoto();

        Assert.assertTrue(detailspage.checkCameraDetailsPhoto());
    }

    @Test(description = "#8.7 Page with photo details contains every all necessary items (Tags)")
    public void DetailsTagsTest(){
        System.out.println("test 8.7");
        driver.navigate().to(ExplorePage.EXPLORE_PAGE_URL);
        ExplorePage explorepage = PageFactory.initElements(driver,ExplorePage.class);
        DetailsPage detailspage = explorepage.getDetailsURLfromPhoto();

        Assert.assertTrue(detailspage.checkTagsDetailsPhoto());
    }

    @Test(description = "#9 Author’s name navigates to the page with author’s photostream")
    public void AuthorsLinkTest(){
        System.out.println("test 9");
        driver.navigate().to(ExplorePage.EXPLORE_PAGE_URL);
        ExplorePage explorepage = PageFactory.initElements(driver,ExplorePage.class);
        DetailsPage detailspage = explorepage.getDetailsURLfromPhoto();
        AuthorsPage authorsPage = detailspage.goToAuthorsPage();

        Assert.assertTrue(authorsPage.getTitle().contains(authorsPage.getName()));
    }

    @Test(description = "#10 Every Album has a thumbnail, title, link and a number of photos and views ")
        public void AlbumTest() throws InterruptedException {
        System.out.println("test 10");
        driver.navigate().to(ExplorePage.EXPLORE_PAGE_URL);
        ExplorePage explorepage = PageFactory.initElements(driver,ExplorePage.class);
        DetailsPage detailspage = explorepage.getDetailsURLfromPhoto();
        AuthorsPage authorsPage = detailspage.goToAuthorsPage();

        authorsPage.goToAlbums().JSwait();
        authorsPage.checkNumberOfAlbums();

        Assert.assertEquals(authorsPage.cicleOfAlbumChecks(),authorsPage.albumCounter(),"Not each album has all necessary items");
    }

    @Test(description = "#11.1 Additional own testing scenarios ([You]->[Groups]). Check Url")
    public void GroupsTestURL() {
        System.out.println("test 11.1");
        driver.navigate().to(ExplorePage.EXPLORE_PAGE_URL);
        ExplorePage explorepage = PageFactory.initElements(driver,ExplorePage.class);
        DetailsPage detailspage = explorepage.getDetailsURLfromPhoto();
        AuthorsPage authorsPage = detailspage.goToAuthorsPage();
        GroupsPage groupsPage = authorsPage.goToGroupsPage();

        Assert.assertEquals(driver.getCurrentUrl(),groupsPage.GROUPS_PAGE_URL,"Can't found Groups Page!");
    }

    @Test(description = "#11.2 Additional own testing scenarios ([You]->[Groups]). Check recommended group")
    public void GroupsTestRecommendedGroups(){
        System.out.println("test 11.2");
        driver.navigate().to(GroupsPage.GROUPS_PAGE_URL);
        GroupsPage groupsPage = PageFactory.initElements(driver,GroupsPage.class);

        Assert.assertTrue(groupsPage.checkRecommendedGroups());
    }

    @Test(description = "#12.1 Additional own testing scenarios ([Explore]->[Galleries]). Check Url")
    public void GalleriesTestURL() {
        System.out.println("test 12.1");
        driver.navigate().to(GroupsPage.GROUPS_PAGE_URL);
        GroupsPage groupsPage = PageFactory.initElements(driver,GroupsPage.class);
        GalleriesPage galleriesPage = groupsPage.goToGalleriesPage();

        Assert.assertEquals(driver.getCurrentUrl(),galleriesPage.GALLERIES_PAGE_URL,"Can't found Gallery Page");
    }

    @Test(description = "#12.2 Additional own testing scenarios ([Explore]->[Galleries]). Check gallery's items")
    public void GalleriesTestItems(){
        System.out.println("test 12.2");
        driver.navigate().to(GalleriesPage.GALLERIES_PAGE_URL);
        GalleriesPage galleriesPage = PageFactory.initElements(driver,GalleriesPage.class);

        Assert.assertTrue(galleriesPage.checkGallery());
    }

    @Test(description = "#13.1 Additional own testing scenarios (Search functionality). Search Url")
    public void SearchTestUrl(){
        System.out.println("test 13.1");
        driver.navigate().to(GalleriesPage.GALLERIES_PAGE_URL);
        GalleriesPage galleriesPage = PageFactory.initElements(driver,GalleriesPage.class);
        SearchPage searchPage = galleriesPage.goToSearchPageByButton();

        Assert.assertEquals(searchPage.SEARCH_PAGE_URL, driver.getCurrentUrl(),"Can't found Gallery Page");
    }

    @Test(description = "#13.2 Additional own testing scenarios (Search functionality). Search Photo")
    public void SearchTestPhoto() throws InterruptedException {
        System.out.println("test 13.2");
        driver.navigate().to(SearchPage.SEARCH_PAGE_URL);
        SearchPage searchPage  = PageFactory.initElements(driver,SearchPage.class);
        String searchRequest = "Red Panda";
        searchPage.sendRequest(searchRequest);

        Assert.assertEquals(searchPage.getFirstTitle().contains(searchRequest), true, "Your search returned incorrect results, possibly due Firefox!  ");
    }

}
