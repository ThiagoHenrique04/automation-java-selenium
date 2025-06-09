package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
public class HomePage {
    
    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }
        
    // ========== Locators ==========
    
    private By getwelcomeText = By.cssSelector(".home__ContainerText-sc-1auj767-7.iDA-Ddb");
    private By getBalanceText = By.id("textBalance");
    private By TransferButton = By.cssSelector("#btn-TRANSFERÊNCIA");
    private By ExtractButton = By.cssSelector("#btn-EXTRATO");

    // ========== Actions ==========
    public String getTextName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(getwelcomeText)).getText();
    }

    public String getBalanceText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(getBalanceText)).getText();
    }
    
    public String getBalanceValue() {
        String balance = getBalanceText();
        return balance.replaceAll("[^0-9.,]", "").replace(",", ".");
    }

    public void clickTransferButton() {
        wait.until(ExpectedConditions.elementToBeClickable(TransferButton)).click();
    }

    public void clickExtractButton() {
        wait.until(ExpectedConditions.elementToBeClickable(ExtractButton)).click();
    }
}