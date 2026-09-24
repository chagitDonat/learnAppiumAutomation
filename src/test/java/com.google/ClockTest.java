package com.google;

import ai.agent.TestAnalysisAgent;
import ai.tools.TestExecutionResult;
import ai.tools.TestResultStore;
import base.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import mobile.pageObjects.Clock.ClockPage;
import mobile.pageObjects.Clock.TimerPage;
import mobile.pageObjects.chrome.FirstChromePage;
import mobile.pageObjects.chrome.GoogleSearchPage;
import mobile.pageObjects.chrome.PopUpChromePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.net.URL;

public class ClockTest extends BaseTest{


    AndroidDriver driver;

    @BeforeEach
    public void beforeEach() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("emulator-5554");
        options.setAutomationName("UiAutomator2");
        options.setPlatformName("Android");
        options.setPlatformVersion("16");
        options.setAppPackage("com.google.android.deskclock");
        options.setAppActivity("com.android.deskclock.DeskClock");
        options.setNoReset(false);
        options.setFullReset(false);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
    }

    @Test
    public void clockTest() throws InterruptedException, MalformedURLException {

        long startTime = System.currentTimeMillis();
        String status = "PASS";
        Throwable failure = null;
        try{

            // סעיף 1 - ודאי שהאפליקציה עלתה
            ClockPage clockPage = new ClockPage(driver);
            Assertions.assertTrue(clockPage.isOnPage() );

            // סעיף 2 - לחצי על טימר
            TimerPage timerPage = clockPage.timerBtnClick();
            Assertions.assertTrue(timerPage.isOnPage());


            // סעיף 3 - הגדירי זמן
            timerPage.clickOnNumber(6).clickOnNumber(0);

            // סעיף 4 - ודאי שהזמן שהזנת התעדכן בהצלחה
            Assertions.assertTrue(timerPage.checkTimerUpdated("60"));

            // סעיף 5 - לחצי על הפעלת הטימר
            timerPage.startTimerBtnClick();

            // לאחר המתנה של 30 שניות בטסט
            Thread.sleep(30000);
            timerPage.startPauseBtnClick();
            Assertions.assertTrue(timerPage.getRemainingSecondsText() < 20);

         } catch (Throwable e) {

        status = "FAIL";
        failure = e;

        throw e;

    } finally {

        long duration =
                System.currentTimeMillis() - startTime;

        if (failure != null) {

            // Send the thrown error (for example an
            // org.opentest4j.AssertionFailedError) to the store so the
            // analysis agent can inspect it.
            TestResultStore.save(
                    TestExecutionResult.failure(
                            "ClockTest",
                            duration,
                            failure
                    )
            );

        } else {

            TestResultStore.save(
                    new TestExecutionResult(
                            "ClockTest",
                            status,
                            duration
                    )
            );
        }
    }


    }

    @AfterEach
    public void afterEach(){
        driver.quit();

            TestAnalysisAgent agent =
            new TestAnalysisAgent();

    String analysis = agent.analyze(
            "Analyze the latest ClockTest result."
    );

    System.out.println();
    System.out.println("========== AI TEST ANALYSIS ==========");
    System.out.println(analysis);
    }

}
