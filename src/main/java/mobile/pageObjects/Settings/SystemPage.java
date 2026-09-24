package mobile.pageObjects.Settings;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class SystemPage extends SubMenuBasePage {

    @AndroidFindBy(accessibility = "System")
    private WebElement title;

    public SystemPage(AndroidDriver d) {
        super(d);
    }

//    public boolean isOnPage(){
//        try{
//            if(visibilityOf(title).isDisplayed())
//                return true;
//        } catch (Exception e) {
//            return false;
//        }
//        return false;
//    }

}
