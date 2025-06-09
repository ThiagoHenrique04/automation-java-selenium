package helpers;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import pages.RegisterPage;
import utils.User;

public class UserHelper {
    private final WebDriver driver;
    private final Faker faker = new Faker();

    public UserHelper(WebDriver driver) {
        this.driver = driver;
    }

 private User createUser(boolean withBalance) {
    User user = new User();
    user.email = faker.internet().emailAddress();
    user.name = faker.name().fullName();
    user.password = faker.internet().password();


    RegisterPage registerPage = new RegisterPage(driver);
    registerPage.clickAccessRegisterButton();
    registerPage.enterEmail(user.email);
    registerPage.enterName(user.name);
    registerPage.enterPassword(user.password);
    registerPage.enterPasswordConfirmation(user.password);


    if (withBalance) {
        registerPage.createAccountWithSaldo();
    }

    registerPage.clickRegisterButton();
    String fullAccountNumber = registerPage.getAccountNumber(); // Ex: "122-4"
    String[] parts = fullAccountNumber.split("-");

    user.accountNumber = parts[0]; 
    user.digit = parts.length > 1 ? parts[1] : ""; 
    registerPage.closeModal();

    return user;
}

public User createUserWithBalance() {
    return createUser(true);
}

public User createUserWithoutBalance() {
    return createUser(false);
}

}