package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.*;

public class TransferPage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    public TransferPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ========== Locators ==========

    private By accountNumberInput = By.cssSelector("input[name='accountNumber']");
    private By digitInput = By.cssSelector("input[name='digit']");
    private By transferValueInput = By.cssSelector("input[name='transferValue']");
    private By descriptionInput = By.cssSelector("input[name='description']");
    private By transferButton = By.cssSelector("button[type='submit']");
   
    // ========== Actions ===========

    public void enterAccountNumber(String accountNumber) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountNumberInput)).sendKeys(accountNumber);
    }

    public void enterDigit(String digit) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(digitInput)).sendKeys(digit);
    }   

    public void enterTransferValue(String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(transferValueInput)).sendKeys(value);
    }

    public void enterDescription(String description) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(descriptionInput)).sendKeys(description);
    }

    public void clickCompleteTransferButton() {
        wait.until(ExpectedConditions.elementToBeClickable(transferButton)).click();
    }
}
