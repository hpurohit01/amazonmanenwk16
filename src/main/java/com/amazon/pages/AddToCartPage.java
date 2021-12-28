package com.amazon.pages;

import com.amazon.utility.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

public class AddToCartPage extends Utility {

    public AddToCartPage() {
        PageFactory.initElements(driver, this);
    }


    @CacheLookup
    @FindBy(xpath = "//div[@id='attach-desktop-sideSheet']//div//div//div[1]//div[1]//h4")
    WebElement addToCartMessage;

    @CacheLookup
    @FindBy(xpath = "//div[@class='a-section a-spacing-none a-padding-none']//span")
    WebElement addToCartMessage2;

    public void verifyTheAddToCartMessage1(String expectedMessage) {
        if (addToCartMessage.isDisplayed()) {
            System.out.println("++++++++++++++++++++Added to cart message element1 displayed+++++++++++++++");
            doWaitUntilVisibilityOfElementLocatedAndReturnLocatedElement(By.xpath("//div[@id='attach-added-to-cart-message']//h4"), 7);
            String actualMessage = addToCartMessage.getText();
            boolean messageMatched = actualMessage.contains(expectedMessage);
            System.out.println("Add to cart message1 element text = " + actualMessage);
            System.out.println("Add to cart message1: expected= " + expectedMessage);
            Assert.assertTrue(messageMatched);
            //   doVerifyElementsJU("Incorrect Message", expectedMessage, actualMessage);
            Reporter.log("Verifying the Added To Cart Message1 :" + expectedMessage + "<br>");
        } else {
            System.out.println("+++++++++++++++++Added to cart element1 not displayed+++++++++++++++++");
        }
    }

    public void verifyTheAddToCartMessage(String expectedMessage) {
        if (addToCartMessage2.isDisplayed() || addToCartMessage.isDisplayed()) {
            if (addToCartMessage2.isDisplayed()) {
                System.out.println("++++++++++++++++++++Added to cart message2 element displayed+++++++++++++++");
                doWaitUntilVisibilityOfElementLocatedAndReturnLocatedElement(By.xpath("//div[@class='a-section a-spacing-none a-padding-none']//span"), 7);
                String actualMessage = addToCartMessage2.getText();
                boolean messageMatched = actualMessage.contains(expectedMessage);
                System.out.println("Add to cart message2 element text = " + actualMessage);
                System.out.println("Add to cart message2: expected= " + expectedMessage);
                Assert.assertTrue(messageMatched);
                Reporter.log("Verifying the Added To Cart Message2 :" + expectedMessage + "<br>");
            } else {
                System.out.println("+++++++++++++++++Added to cart element2 not displayed+++++++++++++++++");
            }
            if (addToCartMessage.isDisplayed()) {
                System.out.println("++++++++++++++++++++Added to cart message element1 displayed+++++++++++++++");
                doWaitUntilVisibilityOfElementLocatedAndReturnLocatedElement(By.xpath("//div[@id='attach-added-to-cart-message']//h4"), 7);
                String actualMessage1 = addToCartMessage.getText();
                boolean messageMatched1 = actualMessage1.contains(expectedMessage);
                System.out.println("Add to cart message1 element text = " + actualMessage1);
                System.out.println("Add to cart message1: expected= " + expectedMessage);
                Assert.assertTrue(messageMatched1);
                Reporter.log("Verifying the Added To Cart Message1 :" + expectedMessage + "<br>");
            } else {
                System.out.println("+++++++++++++++++Added to cart element1 not displayed+++++++++++++++++");
            }
        }
    }
}
