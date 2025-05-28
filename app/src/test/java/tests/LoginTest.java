package tests;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.*;

import com.github.javafaker.Faker;

import base.BaseTest;
import io.qameta.allure.*;
import pages.HomePage;
import pages.LoginPage;
import helpers.UserHelper;
import utils.Config;

@Epic("Login Tests")
@Feature("User Login")
public class LoginTest extends BaseTest {

    private final Faker faker = new Faker();

    private String email;
    private String password;
    private String name;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUpUserIfNeeded(Method method) {
        driver.get(Config.BASE_URL);
        loginPage = new LoginPage(driver);
        
        if (method.getName().equals("loginWithValidCredentials")) {
            UserHelper userHelper = new UserHelper(driver);
            String[] userData = userHelper.registerUser();
            email = userData[0];
            password = userData[1];
            name = userData[2];
        } else {
            email = faker.internet().emailAddress();
            password = faker.internet().password();
        }
    }

    @Test(description = "CT-001 - Test Login with valid credentials")
    @Severity(SeverityLevel.BLOCKER)
    public void loginWithValidCredentials() {

    loginPage.doLogin(email, password);

    HomePage homePage = new HomePage(driver);
    String welcomeText = homePage.getTextName();
    String normalizedText = welcomeText.replaceAll("\\s+", " ").trim();
    String expectedText = "Olá " + name + ", bem vindo ao BugBank :)";

    Assert.assertTrue(normalizedText.contains(expectedText), 
    "Texto de boas vindas não encontrado. Esperado: " + expectedText + " | Encontrado: " + normalizedText);
 }

    @Test(description = "CT-002 - Test Login with invalid credentials")
    @Severity(SeverityLevel.BLOCKER)
    public void loginWithInvalidCredentials() {

    loginPage.doLogin(email, password);
    
    Assert.assertTrue(loginPage.getMessage()
    .contains("Usuário ou senha inválido"));
 }
}