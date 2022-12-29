package com.dice;

import com.dice.base.BaseTest;
import com.dice.base.CsvDataProvider;
import com.dice.pages.LogInPage;
import com.dice.pages.ProfilePage;
import org.testng.Assert;
import org.testng.annotations.Test;
//import org.apache.log4j.Logger;

import java.util.Map;

public class LoginTest extends BaseTest {
//    private static final Logger
    @Test(priority = 1, groups = {"positive"})
    public void positiveLogInTest() throws InterruptedException {
        LogInPage logInPage = new LogInPage(driver, log);
        String expectedPageTitle = "Dashboard Home Feed | Dice.com";
        
        String correctFirstName = "Wojti";
        String correctLastName = "Luko";
        
        //open dice login pageq
        logInPage.openLogInPage();
        
        logInPage.allowAllCookies();
        
        //fill up email and password
        logInPage.fillUpEmailAndPassword("woj_luk@wp.pl", "Automation2022");
        log.info("filling up fields");
        
        //push sign in button
        ProfilePage profilePage = logInPage.pushSignInButton();
        log.info("pushing submit button");
        profilePage.waitForProfilePageToLoad();
        
        //Veryfications:
        // - verify login title
        log.info("verification");
        String actualTitle = profilePage.getTitle();
        Assert.assertTrue(expectedPageTitle.equals(actualTitle), "Page title is not expected. \nExpected: " + expectedPageTitle + "\nActual: " + actualTitle);
        // - verify correct name
        Assert.assertTrue(profilePage.isCorrectProfileLoaded(correctFirstName, correctLastName), "Profile name is not expected");
    }
    
    @Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class, priority = 2, groups = {"negative"})
    public void negativeLoginTest(Map<String, String> testData) {
        LogInPage logInPage = new LogInPage(driver, log);
        String expectedErrorMessage = "Email and/or password incorrect.";
        
        String testNumber = testData.get("no");
        String email = testData.get("email");
        String password = testData.get("password");
        String description = testData.get("description");
        
        log.info("test number: #" + testNumber + ", for: " + description + " ,where\nEmail: " + email + "\nPassword: " + password);
        
        //open dice login page
        logInPage.openLogInPage();
        
        //fill up email and password
        logInPage.fillUpEmailAndPassword(email, password);
        
        logInPage.allowAllCookies();
        
        //push sign in button
        logInPage.pushSignInButton();
        
        String errorMessage = logInPage.getLogInErrorMessage();
        
        Assert.assertTrue(errorMessage.contains(expectedErrorMessage));
    }
}
