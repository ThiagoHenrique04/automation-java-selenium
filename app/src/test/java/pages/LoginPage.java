package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class LoginPage {
    
    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    // ========== Locators ==========
    private By Emailinput = By.name("email");
    private By Passwordinput = By.name("password");
    private By LoginButton = By.cssSelector("button[class='style__ContainerButton-sc-1wsixal-0 otUnI button__child']");
    private By MsgModal = By.id("modalText");


    // ============ Actions ==========
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Emailinput)).sendKeys(email);;
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Passwordinput)).sendKeys(password);
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(LoginButton)).click();
    }

    public String getMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(MsgModal)).getText();
    }
    

    public void doLogin(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }
}
