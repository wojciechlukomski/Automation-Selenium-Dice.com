package com.dice.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

import org.apache.logging.log4j.Logger;

public class BrowserFactory {
    public static WebDriver getDriver(String browser, Logger log) {
        WebDriver driver = null;
        
        if ("Safari".equals(browser)) {
            log.info("open safari browser");
            driver = new SafariDriver();
        } else {
            log.info("open chrome browser");
            System.setProperty("webdriver.chrome.driver", "/Users/wojciechlukomski/Downloads/chromedriver 2");
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        return driver;
    }
}

