import org.testng.annotations.Test;

public class FedEx_ActionItem extends Reusable_Annotations{

    @Test(priority = 1)
    public void TC1_Home_Page (){
        //navigate to fedex
        driver.navigate().to("https://www.fedex.com/en-us/home.html");
        //click on Design & Print
        Reusable_Actions.clickActionByIndex(driver,"//*[@class= 'fxg-mouse']",3,logger,"Design & Print");
        //click on Browse service
        Reusable_Actions.clickAction(driver,"//*[@title= 'Browse Services']",logger,"Browse service");

    }//end of test case 1//

    @Test(dependsOnMethods ="TC1_Home_Page" )
    public void TC2_PrintService_Page() throws InterruptedException {
        //click on design service
        Reusable_Actions.clickActionByIndex(driver,"//*[@class= 'cc-aem-c-tabs__list-item cc-aem-u-border--bottom-none cc-aem-u-display--block']",3,logger,"design service");
        Thread.sleep(1000);
        //click on online printing
        Reusable_Actions.clickAction(driver,"//*[@id= '#printing']",logger,"online printing");
        Thread.sleep(2000);
        //click on explore product
        Reusable_Actions.clickAction(driver,"//*[@data-analytics= 'foc|Explore Products']",logger,"explore product");
        //document printing
        Reusable_Actions.clickAction(driver,"//*[@aria-label='Copies and Custom Documents']",logger,"document printing");

    }//end of test case 2

    @Test(dependsOnMethods ="TC2_PrintService_Page" )
    public void TC3_Upload_Page() throws InterruptedException {
        Thread.sleep(3000);
        driver.switchTo().frame("https://www.office.fedex.com/default/configurator/index/index");
        //click on use your file (PROBLEM Doesn't click on browse files)
        Reusable_Actions.clickActionByIndex(driver,"//*[@class='browse-files-btn']",1,logger,"use your file");
        Thread.sleep(1000);
        //upload your picture
        Reusable_Actions.ImageUpload("C:\\Users\\hossa\\Downloads\\Automation.jpeg");

    }//end of test case 3

    @Test(dependsOnMethods = "TC3_Upload_Page")
    public void TC4_Quantity_Page() throws InterruptedException {
        Thread.sleep(3000);
        //quantity
            Reusable_Actions.sendKeysAction(driver,"//*[@class= 'input-number__input ng-untouched ng-pristine ng-valid']","3",logger,"quantity");
        //add to cart
            Reusable_Actions.clickAction(driver,"//*[text()= 'Add To Cart']",logger,"AddToCart");
        //Click on checkout
            Reusable_Actions.clickAction(driver,"//*[text()='Proceed To Checkout']",logger,"CheckOut");
        //Continue As A Guest
            Reusable_Actions.clickAction(driver,"//*[text()= 'Continue As A Guest']",logger,"Continue As A Guest");
    }//end of test case 4

    @Test(dependsOnMethods = "TC4_Quantity_Page")
    public void TC5_CheckOut_Page(){
        //click on pickUp at store
        Reusable_Actions.clickAction(driver,"//*[text()= 'PICK UP AT STORE']",logger,"pickUp at store");
        //add zip
        Reusable_Actions.sendKeysAction(driver,"//*[@id= 'zipcodePickup']","10036",logger,"add zip");
        //click on searchPickUp
        Reusable_Actions.clickAction(driver,"//*[@id= 'search-pickup']",logger,"continue");
    }//end of test case 5
    @Test(dependsOnMethods ="TC5_CheckOut_Page")
    public void TC6_CaptureAddress(){
        //capture first Address
        Reusable_Actions.getTextAction(driver,"//*[@class='pickup-location-container']",0,logger,"first Address");
        //click on first address
        Reusable_Actions.clickActionByIndex(driver,"",0,logger,"click on first address");
        }//end of test case 6

        @Test(dependsOnMethods ="TC6_CaptureAddress")
        public void TC7_ContactInfo() {
            //First name
            Reusable_Actions.sendKeysAction(driver, "//*[@id='contact-fname']", "MD ALAMIN", logger, "First name");
            Reusable_Actions.sendKeysAction(driver, "//*[@id='contact-fname']", "MD ALAMIN", logger, "First name");
            //last name
            Reusable_Actions.sendKeysAction(driver, "//*[@class='contact-lname']", "Shuvlu", logger, "last name");
            //phone number
            Reusable_Actions.sendKeysAction(driver, "//*[@id='contact-number']", "9176502584", logger, "PhoneNumber");
            //email
            Reusable_Actions.sendKeysAction(driver, "//*[@id='contact-email']", "hossain.shiblu10@gmail.com", logger, "email");
            //CONTINUE TO PAYMENT
            Reusable_Actions.clickAction(driver, "//*[text()= 'CONTINUE TO PAYMENT']", logger, "CONTINUE TO PAYMENT");
            //capture order summery
            Reusable_Actions.getTextAction(driver,"//*[@class='opc-block-summary']",0,logger,"order summery");

        }//end of test case 7

}//end of public class
