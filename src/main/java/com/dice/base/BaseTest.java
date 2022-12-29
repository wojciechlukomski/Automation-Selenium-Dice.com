package com.dice.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.awt.event.WindowFocusListener;

public class BaseTest {
    protected static Logger log;
    protected WebDriver driver;
   
    
    @BeforeClass(alwaysRun = true)
    protected void setUpClass(ITestContext ctx) {
        String testName = ctx.getCurrentXmlTest().getName();
        log = LogManager.getLogger(testName);
        String test = log.getLevel().toString();
        
    }
    
    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    protected void methodSetUp(String browser) {
        log.info("Method set up");
        driver = BrowserFactory.getDriver(browser, log);
    }
    
    @AfterMethod(alwaysRun = true)
    protected void methodTearDown() {
        log.info("Method tear down");
        driver.quit();
    }
}
