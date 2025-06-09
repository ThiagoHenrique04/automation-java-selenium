package tests;

import io.qameta.allure.*;
import utils.Config;
import utils.ModalUtils;
import org.testng.Assert;


import org.testng.annotations.*;

import base.BaseTest;
import helpers.UserHelper;
import pages.*;
import utils.User;



@Epic("Transfer Tests")
@Feature("User Transfer")
public class TransferTest extends BaseTest {

    private User mainUser;
    private User destinationUser;
    private TransferPage transferPage;
    private HomePage homePage;
    private LoginPage loginPage;
    private ModalUtils modalUtils;


@BeforeMethod
public void setUp() {
    driver.get(Config.BASE_URL);
    loginPage = new LoginPage(driver);
    homePage = new HomePage(driver);
    transferPage = new TransferPage(driver);
    modalUtils = new ModalUtils(driver);
}

private void loginMainUserWithBalance() {
    UserHelper userHelper = new UserHelper(driver);
    mainUser = userHelper.createUserWithBalance();
    loginPage.doLogin(mainUser.email, mainUser.password);
}

private void createDestinationUser() {
    UserHelper userHelper = new UserHelper(driver);
    destinationUser = userHelper.createUserWithoutBalance();
    driver.get(Config.BASE_URL);
}




@Test(description = "CT-001 - Test Transfer to valid account")
@Severity(SeverityLevel.BLOCKER)
public void transferToValidAccount() {
    
    createDestinationUser();
    loginMainUserWithBalance();

    homePage.clickTransferButton();

    transferPage.enterAccountNumber(destinationUser.accountNumber);
    transferPage.enterDigit(destinationUser.digit);
    transferPage.enterTransferValue("1000");
    transferPage.enterDescription("Test Transfer");
    transferPage.clickCompleteTransferButton();

    String message = modalUtils.getModalMessage();
    Assert.assertEquals(message, "Transferencia realizada com sucesso");
    String currentUrl = driver.getCurrentUrl();
    Assert.assertTrue(currentUrl.contains("/bank-statement"), "Usuário não foi redirecionado para o extrato após a transferência");
}

@Test(description = "CT-002 - Test Transfer to invalid account")
@Severity(SeverityLevel.BLOCKER)
public void transferToInvalidAccount() {
    
    loginMainUserWithBalance();
    
    homePage.clickTransferButton();

    transferPage.enterAccountNumber("123");
    transferPage.enterDigit("1");
    transferPage.enterTransferValue("100.00");
    transferPage.enterDescription("Test Transfer");
    transferPage.clickCompleteTransferButton();
    String message = modalUtils.getModalMessage();
    Assert.assertEquals(message, "Conta inválida ou inexistente");
}

@Test(description = "CT-003 - Should not allow transfer when balance is less than transfer amount")
@Severity(SeverityLevel.BLOCKER)
public void shouldNotAllowTransferWhenBalanceIsInsufficient() {
    
    createDestinationUser();
    loginMainUserWithBalance();

    homePage.clickTransferButton();

    transferPage.enterAccountNumber(destinationUser.accountNumber);
    transferPage.enterDigit(destinationUser.digit);
    transferPage.enterTransferValue("1001");
    transferPage.enterDescription("Test Transfer");
    transferPage.clickCompleteTransferButton();
    String message = modalUtils.getModalMessage();
    Assert.assertEquals(message, "Você não tem saldo suficiente para essa transação");
}

@Test(description = "CT-004 - Should accept only numeric input in account number and digit fields")
@Severity(SeverityLevel.BLOCKER)
public void accountNumberAndDigitMustBeNumericOnly() {
    
    loginMainUserWithBalance();
    
    homePage.clickTransferButton();

    transferPage.enterAccountNumber("thi");
    transferPage.enterDigit("t");
    transferPage.enterTransferValue("100.00");
    transferPage.enterDescription("Test Transfer");
    transferPage.clickCompleteTransferButton();

    String message = modalUtils.getModalMessage();
    Assert.assertEquals(message, "Número da conta e dígito devem conter apenas números");
}

@Test(description = "CT-005 - description field must be mandatory")
@Severity(SeverityLevel.CRITICAL)
public void descriptionFieldMustBeMandatory() {
    
    createDestinationUser();
    loginMainUserWithBalance();
    
    homePage.clickTransferButton();

    transferPage.enterAccountNumber(destinationUser.accountNumber);
    transferPage.enterDigit(destinationUser.digit);
    transferPage.enterTransferValue("100.00");
    transferPage.clickCompleteTransferButton();

    String message = modalUtils.getModalMessage();
    Assert.assertEquals(message, "Campo Descrição é obrigatório");
}

@Test(description = "CT-006 - Transfer value cannot be equal to zero")
@Severity(SeverityLevel.BLOCKER)
public void transferValueCannotBeEqualToZero() {
    
    createDestinationUser();
    loginMainUserWithBalance();

    homePage.clickTransferButton();

    transferPage.enterAccountNumber(destinationUser.accountNumber);
    transferPage.enterDigit(destinationUser.digit);
    transferPage.enterTransferValue("0.00");
    transferPage.enterDescription("Test Transfer");
    transferPage.clickCompleteTransferButton();

    String message = modalUtils.getModalMessage();
    Assert.assertEquals(message, "Valor da transferência não pode ser 0 ou negativo");
}

@Test(description = "CT-007 - Transfer value cannot be less than zero")
@Severity(SeverityLevel.BLOCKER)
public void  transferValueCannotBeLessThanZero () {
    
    createDestinationUser();
    loginMainUserWithBalance();

    homePage.clickTransferButton();

    transferPage.enterAccountNumber(destinationUser.accountNumber);
    transferPage.enterDigit(destinationUser.digit);
    transferPage.enterTransferValue("-1");
    transferPage.enterDescription("Test Transfer");
    transferPage.clickCompleteTransferButton();

    String message = modalUtils.getModalMessage();
    Assert.assertEquals(message, "Valor da transferência não pode ser 0 ou negativo");
}

}

