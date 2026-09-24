package mobile.pageObjects.Settings;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class GooglePage extends SubMenuBasePage{
    public GooglePage(AndroidDriver d) {
        super(d);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Google services\"]")
    public WebElement title;


    @Override
    public boolean isOnPage(String titleText){
        try{
            Thread.sleep(2000);
            return visibilityOf(title).getAttribute("text").contains(titleText);
            //return textToBe(By.xpath("//*[@resource-id='com.android.settings:id/collapsing_toolbar']"), titleText);
        } catch (Exception e) {
            return false;
        }
    }
}
