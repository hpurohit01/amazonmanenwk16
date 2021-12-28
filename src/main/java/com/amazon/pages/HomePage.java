package com.amazon.pages;

import com.amazon.propertyreader.PropertyReader;
import com.amazon.utility.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.AssertJUnit;
import org.testng.Reporter;

import java.util.List;

public class HomePage extends Utility {

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@id='nav-main']//div[contains(@class,'nav-fill')]//div//div//a[2]")
    WebElement custServ;

    @CacheLookup
    @FindBy(xpath = "//input[@id='twotabsearchtextbox']")
    WebElement searchInputBox;

    @CacheLookup
    @FindBy(xpath = "//input[@id='nav-search-submit-button']")
    WebElement searchButton;

    @CacheLookup
    @FindBy(xpath = "//div[@id='nav-main']//div//a")
    List<WebElement> menuBar;


    public void clickOnCustomerServiceMenu() {
        doWaitUntilVisibilityOfElementLocatedAndthenClick(By.xpath("//div[@id='nav-main']//div[contains(@class,'nav-fill')]//div//div//a[2]"), 2);
        doClickOnElement(custServ);
        Reporter.log("clicking on customer service from menu."+"<br>");
    }

    public void clickOnMenu(String menu) {
        for (WebElement element : menuBar) {
            if (element.getText().trim().equalsIgnoreCase(menu)) {
                element.click();
                Reporter.log("Clicking on menu: "+element.getText()+"<br>");
                break;
            }
        }
    }

    public void verifyHomePage() {
        String expected = PropertyReader.getInstance().getProperty("baseUrl");
        String actual = driver.getCurrentUrl();
        Reporter.log("verifying homepage."+"<br>");
        AssertJUnit.assertEquals(expected, actual);
    }

    public void sendTextToSearchInputField(String text) {
        doSendTextToElement(searchInputBox, text);
        Reporter.log("Sending text to search box. '" + text + "'"+"<br>");
    }

    public void clickOnSearchButton() {
        doClickOnElement(searchButton);
    }


}
