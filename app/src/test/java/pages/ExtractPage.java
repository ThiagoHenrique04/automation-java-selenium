package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class ExtractPage {
    
    private WebDriver driver;
    private WebDriverWait wait;

    public ExtractPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    // ========== Locators ==========

    // ========== Actions ==========
}
