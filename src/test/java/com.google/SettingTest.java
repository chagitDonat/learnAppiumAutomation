package com.google;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import mobile.Utils;
import mobile.enums.MainMenu;
import mobile.enums.SystemMenu;
import mobile.pageObjects.Settings.*;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SettingTest {


    AndroidDriver driver;

    @BeforeAll
    public void beforeEach() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("emulator-5554");
        options.setAutomationName("UiAutomator2");
        options.setPlatformName("Android");
        options.setPlatformVersion("16");
        options.setAppPackage("com.android.settings");
        options.setAppActivity("com.android.settings.Settings");
        options.setFullReset(false);
        options.setNoReset(false);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), options);
    }


    @Test
    public void settingsTestLesson2() throws IOException {

        // step 1 - menu page loaded successfully
        MenuPage menuPage = new MenuPage(driver);
        Assertions.assertTrue(menuPage.isOnPage());

        //step 2 - go to system menu with assert
        menuPage.clickOnMenuValue(MainMenu.SYSTEM);
        SystemPage systemPage = new SystemPage(driver);
        Assertions.assertTrue(systemPage.isOnPage(MainMenu.SYSTEM.getTitle()));


        // step 3 - open Date & Time with assert
        systemPage.clickOnMenuValue(SystemMenu.DATE_TIME);
        DateTimePage dateTimePage = new DateTimePage(driver);
        Assertions.assertTrue(dateTimePage.isOnPage(SystemMenu.DATE_TIME.getTitle()));

        //step 4 - change to 12 hours format
        dateTimePage.use24HoursFormatSwitchClick();
        // assert the ui
        Assertions.assertTrue(dateTimePage.checkUse24HoursFormatText());

        //step 5 - check if the format changed with adb
        String result = Utils.runAdbCommand("settings get system time_12_24");
        Assertions.assertEquals("12", result);

        // step 6 - close the app and reopen, check that the definition is the same
        driver.terminateApp("com.android.settings");
        driver.activateApp("com.android.settings");
        menuPage.clickOnMenuValue(MainMenu.SYSTEM);
        Assertions.assertTrue(systemPage.isOnPage(MainMenu.SYSTEM.getTitle()));
        systemPage.clickOnMenuValue(SystemMenu.DATE_TIME);
        Assertions.assertTrue(dateTimePage.isOnPage(SystemMenu.DATE_TIME.getTitle()));
        Assertions.assertTrue(dateTimePage.checkUse24HoursFormatText());

    }


    @Test
    public void terminateAndActivate() throws InterruptedException {
        driver.terminateApp("com.android.settings");
        driver.activateApp("com.android.settings");
        Thread.sleep(2000);
    }

    @Test
    public void menu() throws InterruptedException {

        // step 1 - menu page loaded successfully
        MenuPage menuPage = new MenuPage(driver);
        Assertions.assertTrue(menuPage.isOnPage());

        //step 2 - go to google menu with assert
        menuPage.clickOnMenuValueByIndex(MainMenu.NETWORK_INTERNET);
        NetworkPage networkPage = new NetworkPage(driver);
        Assertions.assertTrue(networkPage.isOnPage(MainMenu.NETWORK_INTERNET.getTitle()));




    }


    @AfterAll
    public void afterAll(){
        driver.quit();
    }






//    {
//        "platformName": "android",
//            "appium:platformVersion": "16",
//            "appium:deviceName": "emulator-5554",
//            "appium:appPackage": "com.android.settings",
//            "appium:appActivity": "com.android.settings.Settings",
//            "appium:automationName": "UiAutomator2",
//            "appium:noReset": "false",
//            "appium:fullReset": "false"
//    }
}
