package com.amazon.utility;

import com.amazon.drivermanager.ManageDriver;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class Utility extends ManageDriver {
    //******************************* VERIFY Asserts Methods ***************************

    /**
     * This method verifies elements using Assert class after reading text from element
     * @param expectedMessage
     * @param actualMessage
     * @param displayMessage
     */
    public void doVerifyElementsJU(String displayMessage, String expectedMessage, String actualMessage ) {
        Assert.assertEquals(displayMessage, expectedMessage, actualMessage);
    }

    public void doVerifyElements(String expectedMessage, By by, String displayMessage) {
        Assert.assertEquals(expectedMessage, by, displayMessage);
    }


    public void doVerifyElements(String displayMessage, String expectedMessage,WebElement element ) {
        String actualMessage = element.getText().trim();
        Assert.assertEquals(displayMessage, expectedMessage,actualMessage);
    }

//******************************JAVA Executor Methods*********************************

    /**
     * To perform scroll vertically for 1000px
     */
    public void javaExecutorScriptExecuteScriptToPerformScroll() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
    }

    /**
     * Performing a sleep in the browser under test
     */
    public void jvJavaScriptExecutorExecuteASyncScriptPerformingSleep() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        long start = System.currentTimeMillis();
        ((JavascriptExecutor) driver).executeAsyncScript(
                "window.setTimeout(arguments[arguments.length - 1], 500);");
        System.out.println(
                "Elapsed time: " + (System.currentTimeMillis() - start));
    }

    /**
     * Scroll the page till the element is found
     *
     * @param element
     */
    public void javaExecutorScriptExecuteScriptToScrollThePageTillElementFound(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    /**
     * To click on button
     *
     * @param element
     */
    public void javaExecutorScriptExecuteScriptToClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }


    //*****************BASIC WEBDRIVER METHODS******************************

    /**
     * click on element
     *
     * @param by
     */
    public void doClickOnElement(By by) {
        WebElement loginLink = driver.findElement(by);
        loginLink.click();
    }

    public void doClickOnElement(WebElement element) {
        element.click();
    }


    public void doSendTextToElement(WebElement element, String text) {
        element.sendKeys(text);
    }

    public void doSendTextToElement(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }



    public String doGetTextFromElement(By by) {
        return driver.findElement(by).getText();
    }

    public String doGetTextFromElement(WebElement element) {
        return element.getText();
    }



    public void doClearValue(By by) {
        WebElement elementToClear = driver.findElement(by);
        elementToClear.clear();
    }

    public void doClearValue(WebElement element) {
        element.clear();
    }

    /**
     * This method will extract the value of a particular attribute from an element
     *
     * @param by
     * @param attribute
     * @return
     */
    public String doGetAttributeFromElement(By by, String attribute) {
        return driver.findElement(by).getAttribute(attribute);
    }


    //*************************** Alert Methods ***************************************//

    /**
     * This method will switch to alert
     */
    public void doSwitchToAlert() {
        driver.switchTo().alert();
    }

    /**
     * This method will accept alert
     */
    public void doAcceptAlert() {
        driver.switchTo().alert().accept();
    }

    /**
     * This method will dismiss alert
     */
    public void doDismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    /**
     * This method will get text from alert
     */
    public String doGetTextFromAlert() {
        return driver.switchTo().alert().getText();
    }


    /**
     * send text to alert
     *
     * @param text
     */
    public void doSendTextToAlert(String text) {
        driver.switchTo().alert().sendKeys(text);
    }


    //*************************** Select Class Methods ***************************************//

    /**
     * This method will select the option by visible text
     */
    public void doSelectByVisibleTextFromDropDown(By by, String text) {
        WebElement dropDown = driver.findElement(by);
        Select select = new Select(dropDown);
        select.selectByVisibleText(text);
    }

    public void doSelectByVisibleTextFromDropDown(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    /**
     * This method will select the option by value
     */
    public void doSelectByValueFromDropDown(By by, String value) {
        new Select(driver.findElement(by)).selectByValue(value);
    }

    public void doSelectByValueFromDropDown(WebElement webElement, String value) {
        new Select(webElement).selectByValue(value);
    }

    /**
     * This method will select the option by index
     */
    public void doSelectByIndexFromDropDown(By by, int index) {
        new Select(driver.findElement(by)).selectByIndex(index);
    }

    public void doSelectByIndexFromDropDown(WebElement element, int index) {
        new Select(element).selectByIndex(index);
    }

    /**
     * This method will select the option by contains text
     */
    public void doSelectByContainsTextFromDropDown(By by, String text) {
        List<WebElement> allOptions = new Select(driver.findElement(by)).getOptions();
        for (WebElement options : allOptions) {
            if (options.getText().contains(text)) {
                options.click();
            }
        }
    }

    public void doSelectByContainsTextFromDropDown(WebElement element, String text) {
        List<WebElement> allOptions = new Select(element).getOptions();
        for (WebElement options : allOptions) {
            if (options.getText().contains(text)) {
                options.click();
            }
        }
    }


    //*************************** Action Methods ***************************************//

    /**
     * This method will use to hover mouse on element
     */
    public void doMouseHoverTo(By by) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(by)).build().perform();
    }

    public void doMouseHoverTo(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }


    /**
     * This method will hover the mouse over a particular element and click it
     *
     * @param by
     */
    public void doMouseHoverAndClick(By by) {
        Actions hover = new Actions(driver);
        WebElement a = driver.findElement(by);
        hover.moveToElement(a).click().build().perform();
    }

    public void doMouseHoverAndClick(WebElement element) {
        Actions hover = new Actions(driver);
        hover.moveToElement(element).click().build().perform();
    }

    /**
     * This Method will hover mouse on one element, then on second element
     * and click the second element
     */
    public void doMouseHoverOnFirstThenSecondAndClick(By by1, By by2) {
        Actions hover = new Actions(driver);
        WebElement destination1 = driver.findElement(by1);
        WebElement destination2 = driver.findElement(by2);
        hover.moveToElement(destination1).moveToElement(destination2).click().build().perform();
    }

    public void doMouseHoverOnFirstThenSecondAndClick(WebElement element1, WebElement element2) {
        Actions hover = new Actions(driver);
        hover.moveToElement(element1).moveToElement(element2).click().build().perform();
    }


    // ****************************** Get URL methods ************************************************

    /**
     * method to get current URL
     *
     * @return
     */
    public String doGetCurrentUrl() {
        return driver.getCurrentUrl();
    }


    // ***************************** RANDOM methods ********************************************

    /**
     * method to generate random string - subject to length of characters required in random string.
     *
     * @param length
     * @return
     */
    public String doGetRandomString(int length) {
        String randString;
        RandomString rnd = new RandomString(length);
        return randString = rnd.nextString();
    }

    //************************** ScreenShot Methods *********************************************//

    /**
     * This method will take screenshot
     */
    public static void takeScreenShot() {
        String filePath = System.getProperty("user.dir") + "/src/test/java/resources/screenshots/";
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File scr1 = screenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scr1, new File(filePath + getRandomString(10) + ".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String currentTimeStamp() {
        Date d = new Date();
        return d.toString().replace(":", "_").replace(" ", "_");
    }

    public static String getScreenshot(WebDriver driver, String screenshotName) {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        // After execution, you could see a folder "FailedTestsScreenshots" under screenshot folder
        String destination = System.getProperty("user.dir") + "/src/test/java/resources/screenshots/" + screenshotName + dateName + ".png";
        File finalDestination = new File(destination);
        try {
            FileUtils.copyFile(source, finalDestination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destination;
    }

    /*
     *Screenshot methods
     */
    public static String takeScreenShot(String fileName) {
        String filePath = System.getProperty("user.dir") + "/test-output/html/";
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File scr1 = screenshot.getScreenshotAs(OutputType.FILE);
        String imageName = fileName + currentTimeStamp() + ".jpg";
        String destination = filePath + imageName;
        try {
            FileUtils.copyFile(scr1, new File(destination));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destination;
    }

    /**
     * This method will generate random string
     */
    public static String getRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    //******************************** WAIT methods ****************************************


    /**
     * wait until visibility of element is located. pass waiting time in seconds
     *
     * @param by
     * @param seconds
     */
    public void doWaitUntilVisibilityOfElementLocated(By by, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    // wait before click on element --> wait until visibility of web element
    public void doWaitUntilVisibilityOfElementLocatedAndthenClick(By by, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by)).click();
    }

    public WebElement doWaitUntilVisibilityOfElementLocatedAndReturnLocatedElement(By by, int seconds){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return driver.findElement(by);
    }


}
