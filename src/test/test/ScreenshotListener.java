import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ScreenshotListener extends TestListenerAdapter {

   @Override
   public void onTestFailure(ITestResult result) {
       Object currentClass = result.getInstance();
       WebDriver webDriver = ((Conditions) currentClass).getDriver();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        String methodName = result.getName();
        String testName = result.getTestName();

            File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            try {
                String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/target/surefire-reports";
                File destFile = new File((String) reportDirectory+"/failure_screenshots/"+methodName+"_"+testName+"_"+formater.format(calendar.getTime())+".png");
                FileUtils.copyFile(scrFile, destFile);
                Reporter.log("<a href='"+ destFile.getAbsolutePath() + "'> <img src='"+ destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


