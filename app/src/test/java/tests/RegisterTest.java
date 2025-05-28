package tests;

import org.testng.annotations.*;

import com.github.javafaker.Faker;

import base.BaseTest;
import io.qameta.allure.*;
import pages.RegisterPage;

@Epic("Register Tests")
@Feature("User Registration")
public class RegisterTest extends BaseTest{
    
    private final Faker faker = new Faker();
    

    @Test(description = "CT-001 - Test Register user successfully - without balance")
    @Severity(SeverityLevel.BLOCKER)
    public void registerUserSuccessfullyWhithoutBalance() {

        String generatedPassword = faker.internet().password(8, 16, true, true, true);

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickAccessRegisterButton();
        registerPage.enterEmail(faker.internet().emailAddress());
        registerPage.enterName(faker.name().fullName());
        registerPage.enterPassword(generatedPassword);
        registerPage.enterPasswordConfirmation(generatedPassword);
        registerPage.clickRegisterButton();
        registerPage.getMessage()
            .contains("criada com sucesso");
        //Depois realizar a validação do saldo

    }

    @Test(description = "CT-002 - Test Register user successfully - without balance")
    @Severity(SeverityLevel.BLOCKER)
    public void registerUserSuccessfullyWhithBalance() {

        String generatedPassword = faker.internet().password(8, 16, true, true, true);

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickAccessRegisterButton();
        registerPage.enterEmail(faker.internet().emailAddress());
        registerPage.enterName(faker.name().fullName());
        registerPage.enterPassword(generatedPassword);
        registerPage.enterPasswordConfirmation(generatedPassword);
        registerPage.createAccountWithSaldo();
        registerPage.clickRegisterButton();
        registerPage.getMessage()
            .contains("criada com sucesso");
        //Depois realizar a validação do saldo
    }

    @Test(description = "CT-003 - Test Register User Without Filling in Name")
    @Severity(SeverityLevel.BLOCKER)
    public void registerUserWithoutFillingName() {

        String generatedPassword = faker.internet().password(8, 16, true, true, true);

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickAccessRegisterButton();
        registerPage.enterEmail(faker.internet().emailAddress());
        registerPage.enterPassword(generatedPassword);
        registerPage.enterPasswordConfirmation(generatedPassword);
        registerPage.clickRegisterButton();
        registerPage.getMessage()
            .contains("Nome não pode ser vazio.");
    }


    @Test(description = "CT-004 - Test Register User Without Filling in Email")
    @Severity(SeverityLevel.BLOCKER)
    public void registerUserWithoutFillingEmail() {

        String generatedPassword = faker.internet().password(8, 16, true, true, true);

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickAccessRegisterButton();
        registerPage.enterName(faker.name().fullName());
        registerPage.enterPassword(generatedPassword);
        registerPage.enterPasswordConfirmation(generatedPassword);
        registerPage.clickRegisterButton();
        registerPage.getMessage()
            .contains("Email não pode ser vazio.");
    }

    @Test(description = "CT-005 - Test Register User Without Filling in Password")
    @Severity(SeverityLevel.BLOCKER)
    public void registerUserWithoutFillingPassword() {

        String generatedPassword = faker.internet().password(8, 16, true, true, true);

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickAccessRegisterButton();
        registerPage.enterEmail(faker.internet().emailAddress());
        registerPage.enterName(faker.name().fullName());
        registerPage.enterPasswordConfirmation(generatedPassword);
        registerPage.clickRegisterButton();
        registerPage.getMessage()
            .contains("Senha não pode ser vazio.");
    }

    @Test(description = "CT-006 - Test Register User Without Filling in Password Confirmation")
    @Severity(SeverityLevel.BLOCKER)
    public void registerUserWithoutFillingPasswordConfirmation() {

        String generatedPassword = faker.internet().password(8, 16, true, true, true);

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickAccessRegisterButton();
        registerPage.enterEmail(faker.internet().emailAddress());
        registerPage.enterName(faker.name().fullName());
        registerPage.enterPassword(generatedPassword);
        registerPage.clickRegisterButton();
        registerPage.getMessage()
            .contains("criada com sucesso");
    }

    @Test(description = "CT-007 - Test Register User with different password and password confirmation")
    @Severity(SeverityLevel.BLOCKER)
    public void RegisterUserWithDifferentPasswordAndPasswordConfirmation() {

        String generatedPassword = faker.internet().password(8, 16, true, true, true);

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickAccessRegisterButton();
        registerPage.enterEmail(faker.internet().emailAddress());
        registerPage.enterName(faker.name().fullName());
        registerPage.enterPassword(generatedPassword);
        registerPage.enterPasswordConfirmation("teste123");
        registerPage.clickRegisterButton();
        registerPage.getMessage()
            .contains("As senhas não são iguais.");
    }
}