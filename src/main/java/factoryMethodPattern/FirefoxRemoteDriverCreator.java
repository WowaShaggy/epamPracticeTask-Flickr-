package factoryMethodPattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

// В процессе разработки


public class FirefoxRemoteDriverCreator extends WebDriverCreator {

    DesiredCapabilities cab = DesiredCapabilities.firefox();

    @Override
    public WebDriver factoryMethod () {
       // InputStream st = null;

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
//        URL url = null;
//        try {
//            url = new URL("http://localhost:7654/wd/hub");//new URL(System.getProperty("nodeUrl"));
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }

//        driver = new FirefoxDriver();
        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
        driver.manage().window().maximize();
       // return new RemoteWebDriver(new URL("http://localhost:7654/wd/hub"), cab);
        return driver;
    }
}
