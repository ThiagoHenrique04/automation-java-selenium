package tests;

import org.testng.Assert;
import org.testng.annotations.*;

import com.github.javafaker.Faker;

import base.BaseTest;
import io.qameta.allure.*;
import pages.RegisterPage;

import utils.Config;


@Epic("Register Tests")
@Feature("User Registration")
public class RegisterTest extends BaseTest{
    
    private final Faker faker = new Faker();
        
    private String email;
    private String name;
    private String password;
    private RegisterPage registerPage;

    @BeforeMethod
    public void setUp() {
        driver.get(Config.BASE_URL); 
        email = faker.internet().emailAddress();
        name = faker.name().fullName();
        password = faker.internet().password();
        registerPage = new RegisterPage(driver);
        registerPage.clickAccessRegisterButton();
    }

    @Test(description = "CT-001 - Test Register user successfully - without balance")
    @Severity(SeverityLevel.BLOCKER)
    public void registerUserSuccessfullyWithoutBalance() {

        registerPage.enterEmail(email);
        registerPage.enterName(name);
        registerPage.enterPassword(password);
        registerPage.enterPasswordConfirmation(password);
        registerPage.clickRegisterButton();
        Assert.assertTrue(registerPage.getMessage()
            .contains("criada com sucesso"));
        //Depois realizar a validação do saldo

    }

    @Test(description = "CT-002 - Test Register user successfully - with balance")
    @Severity(SeverityLevel.BLOCKER)
    public void registerUserSuccessfullyWithBalance() {

        registerPage.enterEmail(email);
        registerPage.enterName(name);
        registerPage.enterPassword(password);
        registerPage.enterPasswordConfirmation(password);
        registerPage.createAccountWithSaldo();
        registerPage.clickRegisterButton();
        Assert.assertTrue(registerPage.getMessage()
            .contains("criada com sucesso"));
        //Depois realizar a validação do saldo
    }

    @Test(description = "CT-003 - Test Register User Without Filling in Name")
    @Severity(SeverityLevel.BLOCKER)
    public void registerUserWithoutFillingName() {

        registerPage.enterEmail(email);
        registerPage.enterPassword(password);
        registerPage.enterPasswordConfirmation(password);
        registerPage.clickRegisterButton();
        Assert.assertTrue(registerPage.getMessage()
            .contains("Nome não pode ser vazio."));
    }


    @Test(description = "CT-004 - Test Register User Without Filling in Email")
    @Severity(SeverityLevel.BLOCKER)
    public void registerUserWithoutFillingEmail() {

        registerPage.enterName(name);
        registerPage.enterPassword(password);
        registerPage.enterPasswordConfirmation(password);
        registerPage.clickRegisterButton();
        Assert.assertTrue(registerPage.getMessage()
            .contains("Email não pode ser vazio."));
    }

    @Test(description = "CT-005 - Test Register User Without Filling in Password")
    @Severity(SeverityLevel.BLOCKER)
    public void registerUserWithoutFillingPassword() {

        registerPage.enterEmail(email);
        registerPage.enterName(name);
        registerPage.enterPasswordConfirmation(password);
        registerPage.clickRegisterButton();
        Assert.assertTrue(registerPage.getMessage()
            .contains("Senha não pode ser vazio."));
    }

    @Test(description = "CT-006 - Test Register User Without Filling in Password Confirmation")
    @Severity(SeverityLevel.BLOCKER)
    public void registerUserWithoutFillingPasswordConfirmation() {

        registerPage.enterEmail(email);
        registerPage.enterName(name);
        registerPage.enterPassword(password);
        registerPage.clickRegisterButton();
        Assert.assertTrue(registerPage.getMessage()
            .contains("criada com sucesso"));
    }

    @Test(description = "CT-007 - Test Register User with different password and password confirmation")
    @Severity(SeverityLevel.BLOCKER)
    public void RegisterUserWithDifferentPasswordAndPasswordConfirmation() {

        registerPage.enterEmail(email);
        registerPage.enterName(name);
        registerPage.enterPassword(password);
        registerPage.enterPasswordConfirmation("teste123");
        registerPage.clickRegisterButton();
        Assert.assertTrue(registerPage.getMessage()
            .contains("As senhas não são iguais."));
    }
}