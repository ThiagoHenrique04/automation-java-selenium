package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class ModalUtils {

    private WebDriver driver;
    private WebDriverWait wait;

    private By modalText = By.id("modalText");

    public ModalUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getModalMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(modalText)).getText();
    }

    public boolean isModalVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(modalText)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
