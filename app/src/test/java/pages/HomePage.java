package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
public class HomePage {
    
    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }
        
    // ========== Locators ==========
    
    private By welcomeText = By.cssSelector(".home__ContainerText-sc-1auj767-7.iDA-Ddb");

    // ========== Actions ==========
    public String getTextName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeText)).getText();
    }
}