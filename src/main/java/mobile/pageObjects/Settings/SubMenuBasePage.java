package mobile.pageObjects.Settings;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import mobile.enums.IMenu;
import mobile.pageObjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SubMenuBasePage extends BasePage  {

    @AndroidFindBy(accessibility = "Navigate up")
    private WebElement navigateUpBtn;

    @AndroidFindBy(xpath = "//*[@resource-id='com.android.settings:id/collapsing_toolbar']")
    private WebElement title;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='android:id/title']")
    private List<WebElement> menuList;

    public SubMenuBasePage(AndroidDriver d) {
        super(d);
    }

    public boolean isOnPage(String titleText){
        try{
            Thread.sleep(2000);
            return visibilityOf(title).getAttribute("content-desc").contains(titleText);
            //return textToBe(By.xpath("//*[@resource-id='com.android.settings:id/collapsing_toolbar']"), titleText);
        } catch (Exception e) {
            return false;
        }
    }

    public void navigateUpBtnClick(){
        elementToBeClickable(navigateUpBtn).click();
    }

    public void clickOnMenuValue(IMenu menu){
        By menuBy =
                By.xpath("//android.widget.TextView[@resource-id='android:id/title' and contains(@text,'"+ menu.getTitle()+"')]");
        (this.scrollDownUntilElementFound( driver, menuBy, 5)).click();
    }

    public void clickOnMenuValueByIndex(IMenu menu){

       elementToBeClickable(menuList.get(menu.getIndex())).click();

       // (this.scrollDownUntilElementFound( driver, menuList.get(menu.getIndex()) , 5)).click();
    }
}
