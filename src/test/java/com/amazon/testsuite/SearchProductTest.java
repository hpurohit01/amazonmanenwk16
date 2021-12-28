package com.amazon.testsuite;

import com.amazon.pages.AddToCartPage;
import com.amazon.pages.HomePage;
import com.amazon.pages.SearchResultPage;
import com.amazon.testbase.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SearchProductTest extends TestBase {


    AddToCartPage addToCartPage;
    HomePage homePage;
    SearchResultPage searchResultPage;

    @BeforeMethod(alwaysRun = true)
    public void initialisation(){
        addToCartPage = new AddToCartPage();
        homePage = new HomePage();
        searchResultPage = new SearchResultPage();
    }

    @Test
    @Parameters({"itemKeyword","productName","qty","expectedMessage"})
    public void verifyIfSelectedProductIsAddedToCartSuccessfully(String itemKeyword, String productName, String qty, String expectedMessage) throws InterruptedException {
        new HomePage().verifyHomePage();
        new HomePage().sendTextToSearchInputField(itemKeyword);
        new HomePage().clickOnSearchButton();
        new SearchResultPage().searchItem(productName);
        new SearchResultPage().changeProductQuantity(qty);
        new SearchResultPage().clickOnAddItemToCart();
    }


}
