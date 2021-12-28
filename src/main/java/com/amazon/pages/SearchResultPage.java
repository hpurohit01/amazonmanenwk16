package com.amazon.pages;

import com.amazon.utility.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.List;

public class SearchResultPage extends Utility {


    // productSelection variable is static due to it's new value remains with one copy so following step in feature file can take the value from this static variable.
    private static boolean productFound;

    public SearchResultPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "quantity")
    WebElement quantityBox;

    @FindBy(xpath = "//div[@class='s-main-slot s-result-list s-search-results sg-row']//div[@data-component-type='s-search-result']//div//h2//a")
    List<WebElement> searchResultAllTexts;

    @FindBy(linkText = "//span[@class='a-size-medium a-color-base a-text-normal'])")
    List<WebElement> productsList;

    @FindBy(xpath = "//a[normalize-space()='Next']")
    WebElement nextPageButtonByXpath;

    @FindBy(css = ".s-pagination-item.s-pagination-next.s-pagination-button.s-pagination-separator")
    WebElement nextPageButtonByCss;

    @CacheLookup
    @FindBy(id = "add-to-cart-button")
    WebElement addToCartButton;

    @CacheLookup
    @FindBy(linkText = "Next")
    WebElement nextByttonByLinkedText;

    public void clickOnNextPageButton() {
        doClickOnElement(nextPageButtonByCss);
    }

    public boolean searchFirstPageIteam(String item) {
        boolean result = false;
        for (WebElement element : searchResultAllTexts) {
            if (element.getText().trim().equals(item)) {
                result = true;
                System.out.println("item found: " + element.getText().trim());
                break;
            }
        }
        return result;
    }

    public void clickOnAddItemToCart() {
        doClickOnElement(addToCartButton);
    }

    public void changeProductQuantity(String qty) throws InterruptedException {
        if (SearchResultPage.productFound) {
            doSelectByValueFromDropDown(quantityBox, qty);
        } else {
            System.out.println("Product not found message");
        }
    }


    public void searchItem(String item) throws InterruptedException {
        boolean myBreak = true;
        SearchResultPage.productFound = false;
        while (myBreak) {
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.visibilityOfAllElements(searchResultAllTexts));
            for (WebElement element : searchResultAllTexts) {
                if (element.getText().contains("\"")) {
                    String replacedText = element.getText().replace("\"", "inch");
                    //  System.out.println("Replaced product= " + replacedText);
                    if (replacedText.equalsIgnoreCase(item)) {
                        System.out.println("Replaced Product found= " + element.getText());
                        doClickOnElement(element);
                        SearchResultPage.productFound = true;
                        myBreak = false;
                        break;
                    }
                } else if (element.getText().equalsIgnoreCase(item)) {
                    System.out.println("Product found= " + element.getText());
                    doClickOnElement(element);
                    SearchResultPage.productFound = true;
                    myBreak = false;
                    break;
                }
            }
            if (!SearchResultPage.productFound) {
                if (myBreak) {
                    if (!driver.getCurrentUrl().contains("pg_4")) { // in this scenario pg_2 actually refers to page 3 display page
                        doWaitUntilVisibilityOfElementLocatedAndReturnLocatedElement(By.linkText("Next"), 30).click();
                    } else {
                        System.out.println("+++++++++ ALL PAGES SEARCHED - product NOT FOUND  +++++++");
                        myBreak = false;
                    }
                    PageFactory.initElements(driver, this);
                    Thread.sleep(5000);
                }
            }
        }
        System.out.println("this is return selection inside method: " + SearchResultPage.productFound); // true
    }

    public void searchItemUsingForLoop(String item) {
        System.out.println("size of items list: " + searchResultAllTexts.size());
        String actualItem = "";
        String actual2 = "";
        int s = 4;
        boolean mybreak = false;
        for (int i = 0; i <= s; i++) {
            for (int j = 1; j < searchResultAllTexts.size(); j++) {
                if (searchResultAllTexts.get(j).getText().contains("\"")) {
                    actual2 = searchResultAllTexts.get(j).getText().replace("\"", "inch").trim();
                    if (actual2.equals(item)) {
                        actualItem = actual2;
                        System.out.println("item with problem quote=" + actualItem);
                        Reporter.log("finiding item:= " + actual2 + "<br>");
                        mybreak = true;
                        break;
                    }
                } else if (searchResultAllTexts.get(j).getText().trim().equals(item)) {
                    actualItem = searchResultAllTexts.get(j).getText();
                    System.out.println("Item without quote=" + actualItem);
                    mybreak = true;
                    break;
                }
                if (mybreak) {
                    break;
                }
            }
            if (!mybreak) {
                javaExecutorScriptExecuteScriptToClick(nextPageButtonByCss);
            }
        }
        boolean expected = actualItem.contains(item);
        Reporter.log("Asserting item by name: " + item + "<br>");
        Assert.assertTrue(expected);

    }
}