package com.dice.pages;

import com.dice.base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.apache.logging.log4j.Logger;


public class ProfilePage extends BasePageObject<ProfilePage> {
    public ProfilePage(WebDriver driver, Logger log) {
        super(driver, log);
    }
    
    private final By editProfileButton = By.xpath("//button[@id='editProfile']");
    private final By advancedSearchButton = By.xpath("//a[@class='dice-btn-link']");
    private final By contactFirstName= By.xpath("//span[@id='contact-first-name']");
    private final By contactLastName= By.xpath("//span[@id='contact-last-name']");
    
    
    public void waitForProfilePageToLoad(){
        log.info("waiting for visibility");
        waitForVisibilityOf(editProfileButton);
        waitForVisibilityOf(advancedSearchButton,10);
    }
    public boolean isCorrectProfileLoaded(String firstName, String lastName){
        return (getText(contactFirstName).equals(firstName)) && (getText(contactLastName).equals(lastName));
    }
}