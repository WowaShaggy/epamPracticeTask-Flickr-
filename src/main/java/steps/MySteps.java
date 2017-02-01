package steps;

import junit.framework.Assert;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class MySteps{
    static WebDriver driver;
    @When("I open BMI Calculator Home Page")
    public void IOpen(){
        driver = new FirefoxDriver();
        driver.get("http://patient.info/health/bmi-calculator-leaflet");
        driver.findElement(By.id("metric")).click();
    }

    @When("I enter height as '$height'")
    public void IEnterHeight(String height){
        WebElement heightCMS = driver.findElement(By.className("met_height_txt"));
        heightCMS.sendKeys(height);
    }

    @When("I enter weight as '$weight'")
    public void IEnterWeight(String weight){
        WebElement weightKg = driver.findElement(By.className("met_kgs_txt"));
        weightKg.sendKeys(weight);
    }

    @When("I click on the Calculate button")
    public void IClickOnTheButton(){
        WebElement button = driver.findElement(By.id("calc_btn"));
        button.click();
    }

    @Then("I should see bmi as '$bmi_exp' and category as '$bmi_category_exp'")
    public void IShouldBmiAndCategory(String bmi_exp, String bmi_category_exp){
        WebElement bmi = driver.findElement(By.xpath(".//*[@id='bmi-calculator']/div[8]/h3[2]/span"));
        Assert.assertEquals(bmi_exp, bmi.getText());
        WebElement bmi_category = driver.findElement(By.xpath(".//*[@id='readspeaker-content']/div[2]/div[1]/div[4]/div/div/p/span"));
        Assert.assertEquals(bmi_category_exp, bmi_category.getText());
        driver.quit();
    }
}
