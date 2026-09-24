package mobile.pageObjects.Settings;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import mobile.pageObjects.BasePage;
import org.openqa.selenium.WebElement;

public class UserPage extends BasePage {

    @AndroidFindBy(id = "com.android.settings:id/collapsing_toolbar")
    private WebElement title;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"android:id/title\" and contains(@text,\"Switch to\")]")
    private WebElement switchToBtn;

    @AndroidFindBy(id = "android:id/button1")
    private WebElement setUpUserBtn;

    @AndroidFindBy(xpath = "//*[contains(@text,'Switching to')]")
    private WebElement switchToMessage;

    public UserPage(AndroidDriver d) {
        super(d);
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

    /**
     * switch to the new user
     */
    public void switchToClick(){
        elementToBeClickable(switchToBtn).click();
        elementToBeClickable(setUpUserBtn).click();
    }


    /**
     * check if switch to message displayed
     * @return
     */
    public boolean checkSwitchToMessageDisplayed(){
        try{
            return visibilityOf(switchToMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
