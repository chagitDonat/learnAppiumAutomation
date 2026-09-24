package mobile.pageObjects.Clock;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import mobile.pageObjects.BasePage;
import org.openqa.selenium.WebElement;

public class ClockPage extends BasePage {

    @AndroidFindBy(id = "com.google.android.deskclock:id/action_bar_title")
    WebElement title;

    @AndroidFindBy(accessibility = "Timer")
    WebElement timerBtn;

    public ClockPage(AndroidDriver d) {
        super(d);
    }

    public boolean isOnPage(){
        try{
            if(visibilityOf(title).getText().contains("Clock"))
                return true;
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public TimerPage timerBtnClick(){
        elementToBeClickable(timerBtn).click();
        return new TimerPage(driver);
    }
}
