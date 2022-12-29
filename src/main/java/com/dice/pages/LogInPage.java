package com.dice.pages;

import com.dice.base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.apache.logging.log4j.Logger;


public class LogInPage extends BasePageObject<LogInPage> {
    private static final String URL = "https://www.dice.com/dashboard/login";
    private final By emailField = By.xpath("//input[@id='email']");
    private final By passwordField = By.xpath("//input[@id='password']");
    private final By signInButton = By.xpath("//button[@type='submit']");
    private final By errorMessage = By.xpath("//span[@data-automation-id='login-failure-help-message']");
    
    public LogInPage(WebDriver driver, Logger log) {
        super(driver, log);
    }
    
    public void allowAllCookies() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebElement cookies = (WebElement) jse.executeScript("return document.querySelector('div').shadowRoot.querySelector('div > div > span > a')");
        String js = "arguments[0].click()";
        ((JavascriptExecutor) driver).executeScript(js, cookies);
    }
    
    public void openLogInPage() {
        log.info("opening page");
        getPage(URL);
    }
    
    public void fillUpEmailAndPassword(String email, String password) {
        type(email, emailField);
        type(password, passwordField);
    }
    
    public ProfilePage pushSignInButton() {
        click(signInButton);
        return new ProfilePage(driver, log);
    }
    
    public String getLogInErrorMessage() {
        waitForVisibilityOf(errorMessage, 2);
        return getText(errorMessage);
    }
}


