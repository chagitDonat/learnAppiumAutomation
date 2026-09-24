package mobile.pageObjects.Settings;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;



public class UsersPage extends SubMenuBasePage {


    @AndroidFindBy(xpath = "//*[child::*[contains(@text,'Add user')]]")
    private WebElement addNewUserBtn;

    @AndroidFindBy(id = "com.android.settings:id/button_ok" )
    private WebElement okBtn;

    @AndroidFindBy(id = "com.android.settings:id/user_name")
    private WebElement userNameInput;

    @AndroidFindBy(id = "com.android.settings:id/button_ok" )
    private WebElement doneBtn;

    public UsersPage(AndroidDriver d) {
        super(d);
    }


    /**
     * add new user function
     * @param userName
     */
    public void addNewUser(String userName){
        elementToBeClickable(addNewUserBtn);
        elementToBeClickable(okBtn);
        visibilityOf(userNameInput).sendKeys(userName);
        elementToBeClickable(doneBtn);
    }


    /**
     *
     * check if the user added successfully
     *
     * @param userName
     * @return
     */
    public boolean checkIfUserAdded(String userName){
        return visibilityOfElementLocated(getByUserXpath(userName)).getText().contains(userName);
    }

    private By getByUserXpath(String userName){
        return By.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='" + userName+ "']");
    }

    /**
     * go to user page
     *
     * @param userName
     * @return
     */
    public UserPage goToUserPage(String userName){
        visibilityOfElementLocated(getByUserXpath(userName)).click();

        return new UserPage(driver);
    }


}
