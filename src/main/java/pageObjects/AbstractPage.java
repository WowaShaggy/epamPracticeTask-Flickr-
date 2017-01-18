package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public abstract class AbstractPage {

    protected WebDriver driver;

    protected Actions action;


    public AbstractPage(WebDriver driver){this.driver=driver;}


}


