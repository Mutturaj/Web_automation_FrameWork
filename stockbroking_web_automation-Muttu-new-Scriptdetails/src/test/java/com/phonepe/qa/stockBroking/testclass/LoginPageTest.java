package com.phonepe.qa.stockBroking.testclass;

import com.phonepe.qa.stockBroking.pages.LoginPage;
import com.phonepe.webautomationframework.TestExecutionEngine;
import com.phonepe.webautomationframework.customEntities.DataProviderAnnotation;
import com.phonepe.webautomationframework.generic.ProjectCustomException;
import org.testng.annotations.Test;

import java.util.List;


public class LoginPageTest extends TestExecutionEngine {

    LoginPage loginPage = new LoginPage();

    /**
     * P0 Login flows:
     * 1. happy flow -> number -> otp -> 2fa pin
     * 2. resend otp -> 4 times and 30 secs for each
     * 3. invalid otp for 5 times, blocker and can validate the counts for the attempts and blocker with different number and try login
     * a. retry after 2 mins ( 10 mins bcos of sentinel) - so run this at last priority play with
     * 4. Session expiry in 5 minutes
     * <p>
     * PIN:
     * 1. login with another account
     * 2. pin forgot
     * 3. invalid OTP 4 attempts, 30 secs
     * 4. happy fow of new PIn, which push us to the login page
     */

    @DataProviderAnnotation(sheetName = {"Login"}, keywords = {"Login_1"})
    @Test(description = "Happy flow Login to StockBroking", groups = {"HappyFlow","Sanity","Regression"})
    public void loginToStockBroking(List<String> testData) {
        loginToTheApplication(testData);
        loginPage.clickProfileOptionsDropDown();
        loginPage.clickLogout();
    }

    void loginToTheApplication(List<String> testData) {
        try {
            String mobileNumber = loginPage.getData(testData, "MobileNumber");
            loginPage.enterMobileNumber(mobileNumber);
            loginPage.clickProceedButton(true);
            String OTP = loginPage.getOTP(mobileNumber);
            loginPage.enterOTPOrPin(OTP, false);
            loginPage.clickVerifyButton(true);
            String pin = loginPage.getMPin(loginPage.getData(testData, "Pin"));
            loginPage.enterOTPOrPin(pin, true);
        }catch (Exception e){
            new ProjectCustomException(loginPage.getClassName(), loginPage.getMethodName(), e, "failed in login" );
        }
    }


    @DataProviderAnnotation(sheetName = {"Login"}, keywords = {"Login_2"})
//    @Test1(description = "loginToStockBroking", groups = {"ResendOTP","Sanity","Regression"})
    public void resendOTPAttempts(List<String> testData) {
        String mobileNumber = loginPage.getData(testData, "MobileNumber");
        loginPage.enterMobileNumber(mobileNumber);
        int retryAttempts = 0;
        while (retryAttempts++ < 4)
            loginPage.clickResendOTP();
        //todo - validate the error in the login page
    }


    @DataProviderAnnotation(sheetName = {"Login"}, keywords = {"Login_2"})
//    @Test1(description = "loginToStockBroking", groups = {"invalidOTPAttempts","Sanity","Regression"})
    public void invalidOTPAndAccountBlocker(List<String> testData) {
        String mobileNumber = loginPage.getData(testData, "MobileNumber");
        loginPage.enterMobileNumber(mobileNumber);
        loginPage.clickProceedButton(true);
        short invalidOTPAttempt = 0, counter = 0;
        while (counter++ < 3) {
            while (invalidOTPAttempt++ < 4) {
                loginPage.enterOTPOrPin("11111", false);
                loginPage.clickProceedButton(true);
                loginPage.verifyInvalidOTPAttempts(5 - invalidOTPAttempt);
            }
            loginPage.enterOTPOrPin("11111", false);
            loginPage.clickProceedButton(true);
            if (counter == 1) {
                loginPage.verifyAccountBlockerPage();
                loginPage.verifyRetryButton();
                loginPage.clickRetryButtonAndVerify(mobileNumber);
            } else
                loginPage.clickLoginAsDifferentUserAndVerify(true);
        }
    }


    @DataProviderAnnotation(sheetName = {"Login"}, keywords = {"Login_2"})
//    @Test1(description = "loginToStockBroking", groups = {"sessionExpiry", "Regression"})
    public void sessionExpiry_Login(List<String> testData) {
        String mobileNumber = loginPage.getData(testData, "MobileNumber");
        loginPage.enterMobileNumber(mobileNumber);
        loginPage.sleep(10 * 61);
        loginPage.clickProceedButton(false);
        //todo - validate the error in the login page
    }


    @DataProviderAnnotation(sheetName = {"Login"}, keywords = {"Login_2"})
//    @Test1(description = "loginToStockBroking", groups = {"Pin_LogIntoAnotherAccount", "Regression"})
    public void logIntoAnotherAccount(List<String> testData) {
        String mobileNumber = loginPage.getData(testData, "MobileNumber");
        loginPage.enterMobileNumber(mobileNumber);
        loginPage.clickProceedButton(true);
        loginPage.enterOTPOrPin(loginPage.getOTP(mobileNumber), false);
        loginPage.clickVerifyButton(true);
        loginPage.clickLoginAsDifferentUserAndVerify(false);
    }


    @DataProviderAnnotation(sheetName = {"Login"}, keywords = {"Login_1"})
    @Test(description = "loginToStockBroking", groups = {"Pin_ResetPIN", "Sanity", "Regression"})
    public void restPin(List<String> testData) {
        String mobileNumber = loginPage.getData(testData, "MobileNumber");
        loginPage.enterMobileNumber(mobileNumber);
        loginPage.clickProceedButton(true);
        loginPage.enterOTPOrPin(loginPage.getOTP(mobileNumber), false);
        loginPage.clickVerifyButton(true);
        loginPage.clickForgotPin();
        loginPage.enterPANDetails("ATNPN0519T", false);
        loginPage.clickSaveButton(false);
        loginPage.enterPANDetails("ATNPN0519E", true);
        loginPage.clickSaveButton(true);
        loginPage.enterOTPOrPin(loginPage.getOTP(mobileNumber), false);
        loginPage.clickVerifyRestPin();
        loginPage.enterAndReEnterPin("222222");
        //todo loginPage.clickSaveButtonResetPin();
    }


    /**
     * todo - invalid OTP in rest page
     *
     */
}
