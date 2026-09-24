package mobile.pageObjects.Settings;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class DateTimePage extends SubMenuBasePage {

    @AndroidFindBy(xpath = "//*[@text='Use 24-hour format']")
    private WebElement use24HoursFormatSwitch;

    @AndroidFindBy(xpath = "//*[contains(@text, 'Use 24-hour format')]//following-sibling::*[@resource-id='android:id/summary']")
    private WebElement use24HoursFormatText;

    public DateTimePage(AndroidDriver d) {
        super(d);
    }

    /**
     * change to 24 hours format
     */
    public void use24HoursFormatSwitchClick(){
        scrollDownUntilElementFound(driver, use24HoursFormatText, 2);
        elementToBeClickable(use24HoursFormatSwitch).click();
    }

    /**
     *
     *
     * @return true if the format contains PM (12 hours)
     */
    public boolean checkUse24HoursFormatText(){
        scrollDownUntilElementFound(driver, use24HoursFormatText, 2);
        return visibilityOf(use24HoursFormatText).getText().contains("PM");
    }
}
