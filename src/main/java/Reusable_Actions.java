import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

public class Reusable_Actions {
    static int timeout=10;
    //reusable method to define and launch webDriver
    public static WebDriver setUpDriver() {
        //define the webDriver manager setup for chromedriver
        WebDriverManager.chromedriver().setup();
        //initialize chrome options
        ChromeOptions options = new ChromeOptions();
        //add argument to chrome options
        options.addArguments("start-maximized");
        //initialize driver with chrome options
        WebDriver driver = new ChromeDriver(options);
        //set it to headless
        //options.addArguments("headless");
        return driver;
    }//end of setup driver method


    //method to click on any web element by explicit wait
    public static void MouseHoverAction(WebDriver driver, WebElement xpathLocator, ExtentTest logger, String elementName){
        //define by explicit wait
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        //mouse action command
        Actions actions = new Actions(driver);
        //use try catch to locate an element and click on it
        try{
            WebElement element = wait.until(ExpectedConditions.visibilityOf(xpathLocator));
            actions.moveToElement(element).perform();
            logger.log(LogStatus.PASS,"Successfully hover to " + elementName);
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Unable to hover to an element " + elementName + " " + e);
            logger.log(LogStatus.FAIL,"Unable to hover to an element " + elementName + " " + e);
        }
    }//end of mouseHover

    //method to capture screenshot when logger fails
    public static void getScreenShot(WebDriver driver,String imageName, ExtentTest logger) {
        try {
            String fileName = imageName + ".png";
            String directory = null;
            String snPath = null;
            directory = "src/main/java/HTML_Report/Screenshots/";
            snPath = "Screenshots//";
            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(sourceFile, new File(directory + fileName));
            //String imgPath = directory + fileName;
            String image = logger.addScreenCapture(snPath + fileName);
            logger.log(LogStatus.FAIL, "", image);
        } catch (Exception e) {
            logger.log(LogStatus.FAIL, "Error Occurred while taking SCREENSHOT");
            e.printStackTrace();
        }
    }//end of getScreenshot method

    //method to click on any webElement by explicit wait
    public static void clickAction(WebDriver driver,String xpath, ExtentTest logger, String elementName){
        //define by explicit wait
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        //use try catch to locate an element and click on it
        try{
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            element.click();
        } catch (Exception e) {
            logger.log(LogStatus.FAIL,"Unable to click on element " + elementName + " " + e);
            getScreenShot(driver,elementName,logger);
            System.out.println("Unable to click on element " + elementName + " For reason : " + e);
        }
    }//end of click method

    //click action by index
    public static void clickActionByIndex(WebDriver driver, String xpath, int index, ExtentTest logger, String elementName) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        try {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath))).get(index).click();
            logger.log(LogStatus.PASS,"Successfully clicked on " + elementName);
        } catch (Exception e) {
            getScreenShot(driver,elementName,logger);
            System.out.println("Unable to click on element: " + elementName + " for reason: " + e);
        }
    }//end of click action by index


    //method to submit on any webElement by explicit wait
    public static void submitAction(WebDriver driver,WebElement xpathLocator, ExtentTest logger, String elementName){
        //define by explicit wait
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        //use try catch to locate an element and click on it
        try{
            wait.until(ExpectedConditions.visibilityOf(xpathLocator)).submit();
            logger.log(LogStatus.PASS,"Successfully submit on " + elementName);
        } catch (Exception e) {
            logger.log(LogStatus.FAIL,"Unable to submit on element " + elementName + " " + e);
            getScreenShot(driver,elementName,logger);
            System.out.println("Unable to submit on element " + elementName + " " + e);
        }
    }//end of submit method

    //method to type on any webElement by explicit wait
    public static void sendKeysAction(WebDriver driver,String xpath, String userValue,ExtentTest logger,String elementName){
        //define by explicit wait
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        //use try catch to locate an element and click on it
        try{
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            element.clear();
            element.sendKeys(userValue);
            logger.log(LogStatus.PASS,"Successfully entered a value on " + elementName);
        } catch (Exception e) {
            logger.log(LogStatus.FAIL,"Unable to enter value on element " + elementName + " " + e);
            getScreenShot(driver,elementName,logger);
            System.out.println("Unable to enter value on element " + elementName + " " + e);
        }
    }//end of sendKeys method


    public static void dropdownByTextAction(WebDriver driver,String xpath,String userValue,ExtentTest logger,String elementName){
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        try{
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            Select dropDown = new Select(element);
            dropDown.selectByVisibleText(userValue);
            logger.log(LogStatus.PASS,"Successfully selected value " + userValue + " on " + elementName);
        } catch (Exception e) {
            System.out.println("Unable to select a value from " + elementName + " " + e);
            logger.log(LogStatus.FAIL,"Unable to select a value from " + elementName + " " + e);
            getScreenShot(driver,elementName,logger);
        }
    }//end of dropdown by text

    public static String getTextAction(WebDriver driver,String xpath,int index,ExtentTest logger,String elementName){
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        String result = "";
        try{
            WebElement element = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath))).get(index);
            result = element.getText();
            logger.log(LogStatus.PASS,"Successfully captured a text on " + elementName);
        } catch (Exception e) {
            logger.log(LogStatus.FAIL,"Unable to select a value from " + elementName + " " + e);
            System.out.println("Unable to select a value from " + elementName + " " + e);
            getScreenShot(driver,elementName,logger);
        }
        return result;
    }//end of capture element by text

    //compare expected and actual title
    public static void compareExpectedAndActualText(String expectedText, String actualText, ExtentTest logger) {
        if (actualText.equals(expectedText)) {
            logger.log(LogStatus.PASS, "Expected Text: " + expectedText + " and Actual Text: " + actualText + " match");
        }
        else{
            logger.log(LogStatus.FAIL, "Expected Text: " + expectedText + " and Actual Text: " + actualText + " does not match");
        }
    }//end of compareExpectedAndActualText


    //switch tab
    public static void SwitchToTabByIndex(WebDriver driver,int index) {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

        //switch to new tabs
        driver.switchTo().window(tabs.get(index));
    }

    public static void ImageUpload(String fileLocation) {
        try {
            //Setting clipboard with file location
            StringSelection stringSelection = new StringSelection(fileLocation);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
            //native key strokes for CTRL, V and ENTER keys
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }//end of uploadFile method






}//end of public class
