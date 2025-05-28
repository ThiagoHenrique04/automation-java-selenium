package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
public class RegisterPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    // ========== Locators ==========
    private By AccessRegisterButton = By.cssSelector("button[class='style__ContainerButton-sc-1wsixal-0 ihdmxA button__child']");
    private By Emailinput = By.cssSelector("form[class='styles__ContainerFormRegister-sc-7fhc7g-0 keVbpY'] input[placeholder='Informe seu e-mail']");
    private By Nameinput = By.name("name");
    private By Passwordinput = By.cssSelector("form[class='styles__ContainerFormRegister-sc-7fhc7g-0 keVbpY'] input[placeholder='Informe sua senha']");
    private By passwordConfirmationinput = By.name("passwordConfirmation");
    private By RegisterButton = By.cssSelector("button[class='style__ContainerButton-sc-1wsixal-0 CMabB button__child']");
    private By saldoToggle = By.cssSelector("label.styles__Container-sc-1pngcbh-0.kIwoPV");
    private By MsgModal = By.id("modalText");
    private By CloseModalButton = By.id("btnCloseModal");


    // ========== Actions ==========
    public void clickAccessRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(AccessRegisterButton)).click();
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Emailinput)).sendKeys(email);
    }

    public void enterName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Nameinput)).sendKeys(name);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Passwordinput)).sendKeys(password);
    }

    public void enterPasswordConfirmation(String passwordConfirmation) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordConfirmationinput)).sendKeys(passwordConfirmation);
    }

    public void createAccountWithSaldo() {
         wait.until(ExpectedConditions.elementToBeClickable(saldoToggle)).click();

    }

    public void clickRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(RegisterButton)).click();
    }

    public String getMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(MsgModal)).getText();
    }

    public void closeModal() {
        wait.until(ExpectedConditions.elementToBeClickable(CloseModalButton)).click();
    }
}