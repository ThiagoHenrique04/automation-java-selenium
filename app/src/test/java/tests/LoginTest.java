package tests;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.*;

import com.github.javafaker.Faker;
import utils.User;
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
            User user = userHelper.createUserWithBalance();
            email = user.email;
            password = user.password;
            name = user.name;
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

    Assert.assertEquals(normalizedText, expectedText);
 }

    @Test(description = "CT-002 - Test Login with invalid credentials")
    @Severity(SeverityLevel.BLOCKER)
    public void loginWithInvalidCredentials() {

    loginPage.doLogin(email, password);
    
    Assert.assertTrue(loginPage.getMessage()
    .contains("Usuário ou senha inválido"));
 }

 @Test(description = "CT003 - Test Login with empty email") 
 @Severity(SeverityLevel.CRITICAL)
 public void loginWithEmptyEmail() {

    loginPage.enterEmail("");
    loginPage.enterPassword(password);
    loginPage.clickLoginButton();

    Assert.assertTrue(loginPage.getErrorMessage()
    .contains("É campo obrigatório"));
 }

 @Test(description = "CT004 - Test Login with empty password")  
 @Severity(SeverityLevel.CRITICAL)
 public void loginWithEmptyPassword() {

    loginPage.enterEmail(email);
    loginPage.enterPassword("");
    loginPage.clickLoginButton();

    Assert.assertTrue(loginPage.getErrorMessage()
    .contains("É campo obrigatório"));
 }
 
@Test(description = "CT005 - Test Login with both fields empty") 
@Severity(SeverityLevel.CRITICAL)
public void loginWithBothFieldsEmpty() {
    
    loginPage.enterEmail("");
    loginPage.enterPassword("");
    loginPage.clickLoginButton();

    Assert.assertEquals(loginPage.getErrorMessageCount(), 2);
}
    
@Test(description = "CT006 - Test Login with invalid email format") 
@Severity(SeverityLevel.CRITICAL)
public void loginWithInvalidEmailFormat() {
    
        loginPage.enterEmail("invalid-email-format");
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
    
        Assert.assertTrue(loginPage.getErrorMessage()
        .contains("Formato inválido"));
    }

    
}