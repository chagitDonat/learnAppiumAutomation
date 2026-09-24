package mobile.pageObjects.Settings;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import mobile.enums.IMenu;
import mobile.pageObjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MenuPage extends SubMenuBasePage {

    @AndroidFindBy(id = "com.android.settings:id/search_action_bar")
    public WebElement searchBox;

    public MenuPage(AndroidDriver d) {
        super(d);
    }

    public boolean isOnPage(){
        try{
            if(visibilityOf(searchBox).isDisplayed())
                return true;
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public void clickOnMenuValue(IMenu menu){
        By menuBy =
                By.xpath("//android.widget.TextView[@resource-id='android:id/title' and contains(@text,'"+ menu.getTitle()+"')]");
        (this.scrollDownUntilElementFound( driver, menuBy, 5)).click();
    }

}
