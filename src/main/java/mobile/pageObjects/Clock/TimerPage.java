package mobile.pageObjects.Clock;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import mobile.pageObjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TimerPage extends BasePage {

    @AndroidFindBy(xpath = "//*[contains(@resource-id,'com.google.android.deskclock:id/timer_setup_time')]")
    WebElement title;

    @AndroidFindBy(id = "com.google.android.deskclock:id/fab")
    WebElement startTimerBtn;

    @AndroidFindBy(id = "com.google.android.deskclock:id/play_pause")
    WebElement startPauseBtn;

    @AndroidFindBy(id = "com.google.android.deskclock:id/timer_text")
    WebElement remainingSecondsText;

    public TimerPage(AndroidDriver driver) {
        super(driver);
    }

    public boolean isOnPage(){
        try{
            if(visibilityOf(title).isDisplayed())
                return true;
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public TimerPage clickOnNumber(int i){
        visibilityOfElementLocated(By.id("com.google.android.deskclock:id/timer_setup_digit_" + i)).click();
        return this;
    }

    public boolean checkTimerUpdated(String str){
        return visibilityOf(title).getText().contains(str);
    }

    public void startTimerBtnClick(){
        elementToBeClickable(startTimerBtn).click();
    }

    public void startPauseBtnClick(){
        elementToBeClickable(startPauseBtn).click();
    }

    public int getRemainingSecondsText(){
        return Integer.parseInt(visibilityOf(remainingSecondsText).getText());
    }

}
