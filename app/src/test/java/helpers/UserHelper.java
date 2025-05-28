package helpers;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import pages.RegisterPage;

public class UserHelper {
    private final WebDriver driver;
    private final Faker faker = new Faker();

    public UserHelper(WebDriver driver) {
        this.driver = driver;
    }

    public String[] registerUser() {

        String email = faker.internet().emailAddress();
        String name = faker.name().fullName();
        String password = faker.internet().password();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickAccessRegisterButton();
        registerPage.enterEmail(email);
        registerPage.enterName(name);
        registerPage.enterPassword(password);
        registerPage.enterPasswordConfirmation(password);
        registerPage.clickRegisterButton();
        registerPage.closeModal();

        return new String[]{email, password, name};
    }
}
