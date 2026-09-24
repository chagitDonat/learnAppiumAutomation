package com.google;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import mobile.pageObjects.chrome.FirstChromePage;
import mobile.pageObjects.chrome.GoogleSearchPage;
import mobile.pageObjects.chrome.PopUpChromePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class firstTest {

    AndroidDriver driver;

    @BeforeEach
    public void beforeEach() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("emulator-5554");
        options.setAutomationName("UiAutomator2");
        options.setPlatformName("Android");
        options.setPlatformVersion("16");
        options.setAppPackage("com.android.chrome");
        options.setAppActivity("com.google.android.apps.chrome.Main");
        options.setNoReset(false);
        options.setFullReset(false);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), options);
    }

    @Test
    public void loadGoogleSearch() throws InterruptedException, MalformedURLException {

        FirstChromePage firstChromePage = new FirstChromePage(driver);
        Assertions.assertTrue(firstChromePage.isOnPage());

        PopUpChromePage popUpChromePage = firstChromePage.useWithoutAccountBtnClick();
        GoogleSearchPage googleSearchPage = popUpChromePage.noThanksBtnClick();
        Assertions.assertTrue(googleSearchPage.isOnPage());

        googleSearchPage.searchInputSendKeys("trans");
        googleSearchPage.selectIndexInSearchResultList(0);

        // add asserttion trasn page loaded

    }

    @AfterEach
    public void afterEach(){
        driver.quit();
    }
}




//{
//        "platformName": "android",
//        "appium:platformVersion": 16,
//        "appium:deviceName": "emulator-5554",
//        "appium:appPackage": "com.google.android.deskclock",
//        "appium:appActivity": "com.android.deskclock.DeskClock",
//        "appium:automationName": "UiAutomator2",
//        "appium:noReset": false,
//        "appium:fullReset": false
//        }
//
//
//        {
//        "platformName": "android",
//        "appium:platformVersion": "16",
//        "appium:deviceName": "emulator-5554",
//        "appium:appPackage": "com.google.android.deskclock",
//        "appium:appActivity": "com.android.deskclock.DeskClock",
//        "appium:automationName": "UiAutomator2",
//        "appium:noReset": "false",
//        "appium:fullReset": "false"
//        }
