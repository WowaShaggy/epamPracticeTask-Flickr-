package factoryMethodPattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

// В процессе разработки

public class FirefoxRemoteDriverCreator extends WebDriverCreator {
    @Override
    public WebDriver factoryMethod () {
        InputStream st = null;

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        URL url = null;
        try {
            url = new URL("http://localhost:7654/wd/hub");//new URL(System.getProperty("nodeUrl"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
//        driver = new FirefoxDriver();
//        driver.manage().window().maximize();
        return new RemoteWebDriver(url, DesiredCapabilities.chrome());
    }
}
