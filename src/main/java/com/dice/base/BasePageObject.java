package com.dice.base;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageObject<T> {
    protected Logger log;
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    public BasePageObject(WebDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;
        wait = new WebDriverWait(driver, 15);
    }
    
    @SuppressWarnings("unchecked")
    protected T getPage(String url) {
        driver.get(url);
        return (T) this;
    }
    
    protected void type(String text, By element) {
        find(element).sendKeys(text);
    }
    
    protected void click(By element) {
        find(element).click();
    }
    
    protected void waitForVisibilityOf(By locator, Integer... timeOutInSeconds) {
        int attemps = 0;
        while (attemps < 1) {
            try {
                waitFor(ExpectedConditions.visibilityOfElementLocated(locator), (timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
                break;
            } catch (Exception ignored) {
            }
            attemps++;
        }
    }
    
    private void waitFor(ExpectedCondition<WebElement> condition, Integer timeOutInSeconds) {
        timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds : 10;
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(condition);
    }
    
    public String getTitle() {
        return driver.getTitle();
    }
    
    protected String getText(By element) {
        return find(element).getText();
    }
    
    private WebElement find(By element) {
        return driver.findElement(element);
    }
}
