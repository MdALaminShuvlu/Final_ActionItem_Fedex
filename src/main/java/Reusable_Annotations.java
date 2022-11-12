import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class Reusable_Annotations {
    public static WebDriver driver;
    public static ExtentReports reports;
    public static ExtentTest logger;
    @BeforeSuite
    public void setDriver(){
        driver=Reusable_Actions.setUpDriver();
        reports=new ExtentReports("src/main/HTML_Report/Automation_Report.html",true);
    }//end of before suit
    @BeforeMethod
    public void captureTestName(Method testName){
        logger=reports.startTest(testName.getName());
    }//end of before methode
    @AfterMethod
    public void endLogger(){
     reports.endTest(logger);
     //writing back to one html report
        reports.flush();
    }//end of after methode
    @AfterSuite
    public void quitSession(){
        driver.quit();
    }// end of After Suite
}//end of public class
