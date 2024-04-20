package com.phonepe.qa.stockBroking.pages;


import com.phonepe.qa.projectSpecificGenericFunctions.GenericKeywords;
import com.phonepe.qa.stockBroking.pageObjects.LoginPageObjects;
import com.phonepe.webautomationframework.generic.ProjectCustomException;
import com.phonepe.webautomationframework.generic.keywords.LocatorDetails;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LoginPage extends GenericKeywords {
    LoginPageObjects loginPageObjects = new LoginPageObjects();


    private int getTime(LocatorDetails locatorDetails) {
        String[] minAndSec = getText(locatorDetails).split(":");
        return (Integer.parseInt(minAndSec[0].replace("m", "")) * 60) + Integer.parseInt(minAndSec[1].replace("s", ""));
    }


    public void enterMobileNumber(String mobileNumber) {
        inputText(loginPageObjects.mobileNumber_input, mobileNumber);
    }

    public void enterOTPOrPin(String OTP, boolean pin) {
        List<WebElement> elements = getElements(loginPageObjects.otp1FA_input);

        for (int i = OTP.length() - 1; i > -1; i--)
            inputText(elements.get(i), String.valueOf(OTP.charAt(i)), "Entering OTP");
        if (pin)
            waitUntilInVisibilityOfAnElement(loginPageObjects.successful2FA_toaster);
    }

    public void clickGoBack() {
        click(loginPageObjects.goBack_button);
    }

    public void clickResendOTP() {
        waitUntilInVisibilityOfAnElement(loginPageObjects.resendCode_button);
        int retryTime = getResendCodeAvailableTimeInSeconds();
        if (retryTime > 30 && retryTime < 25)
            new ProjectCustomException(getClassName(), getMethodName(), new Exception(), "OTP Retry time is less than 25 or greater than 30");
        sleep(retryTime);
        click(loginPageObjects.resendCode_button);
        waitUntilInVisibilityOfAnElement(loginPageObjects.resendCode_button);
    }

    public void verifyInvalidOTPAttempts(int attemptNumber) {
        if (Integer.parseInt(getText(loginPageObjects.invalidOTP_text).replace("Invalid otp. ", "").replace(" attempts remaining", "").trim()) != attemptNumber)
            new ProjectCustomException(getClassName(), getMethodName(), new Exception(), "OTP attempts are incorrect");
    }

    public int getResendCodeAvailableTimeInSeconds() {
        return getTime(loginPageObjects.resendCodeTimer_text);
    }


    public int getRetryCodeAvailableTimeInSeconds() {
        return getTime(loginPageObjects.retryCodeTimer_text);
    }

    public void clickProceedButton(boolean positiveFlow) {
        click(loginPageObjects.proceedOrVerifyOrSave_button);
        if (positiveFlow)
            waitUntilVisibilityOfAnElement(loginPageObjects.resendCodeTimer_text);
        else
            waitUntilVisibilityOfAnElement(loginPageObjects.sessionExpiry_toaster);
    }

    public void clickTermsAndConditions() {
        click(loginPageObjects.termsAndCondition_hyperLink);
        //todo get the new window and validate and
    }

    public void validatePhoneNumberIn1FA(String mobileNumber) {
        Assert.assertEquals(getText(loginPageObjects.mobileNumber1FA_text).replace("Please enter the 5 digit code sent to", "").trim(), mobileNumber);
    }

    public void clickVerifyButton(boolean positiveFlow) {
        click(loginPageObjects.proceedOrVerifyOrSave_button);
        if (positiveFlow) {
            try {
                waitUntilVisibilityOfAnElement(loginPageObjects.loggedInSuccessful1FA_toaster);
                waitUntilVisibilityOfAnElement(loginPageObjects.forgotPin_button);
            } catch (Exception e) {
                //in-case of the OTP failure, redoing the same process
                if (verifyElementPresent(loginPageObjects.invalidOTP_text)) {
                    sleep(5);
                    String OTP = getOTP(getText(loginPageObjects.mobileNumber1FA_text).replace("Please enter the 5 digit code sent to", "").trim());
                    enterOTPOrPin(OTP, false);
                    click(loginPageObjects.proceedOrVerifyOrSave_button);
                    waitUntilVisibilityOfAnElement(loginPageObjects.loggedInSuccessful1FA_toaster);
                    waitUntilVisibilityOfAnElement(loginPageObjects.forgotPin_button);
                } else
                    new ProjectCustomException(getClassName(), getMethodName(), e, "Failed in retry of the OTP huddle ( Check if Account is blocked");
            }
        } else ;//todo - error message}
    }


    //API methods

    /**
     * Method to get OTP for the requested MobileNumber
     *
     * @param mobileNumber - requested MobileNumber
     * @return - OTP generated for requested MobileNumber
     */
    public String getOTP(String mobileNumber) {
        JsonPath resp;
        int counter = 0;
        do {
            sleep(4);
            counter++;
            resp = getCall("http://sauron.nixy.stg-drove.phonepe.nb6/api/user/" + mobileNumber + "/getOTP").getBody().jsonPath();
        } while ((!(Boolean) resp.get("success")) && counter < 5);
        return resp.get("reply.OTP");
    }

    public void clickLogout() {
        waitUntilVisibilityOfAnElement(loginPageObjects.logout_button);
        click(loginPageObjects.logout_button);
        waitUntilPresenceOfAnElement(loginPageObjects.mobileNumber_input);
    }

    public void verifyAccountBlockerPage() {
        waitUntilVisibilityOfAnElement(loginPageObjects.accountBlocker_text);
        waitUntilVisibilityOfAnElement(loginPageObjects.loginAsAnotherAccount_button);
    }

    public void verifyRetryButton() {
        int retryTime = getRetryCodeAvailableTimeInSeconds();
        if (retryTime > 120 || retryTime < 110)
            new ProjectCustomException(getClassName(), getMethodName(), new Exception(), "Retry timer in account blocker page is incorrect");
    }

    public void clickRetryButtonAndVerify(String mobileNumber) {
        click(loginPageObjects.retry_button);
        if (!getAttributeValue(loginPageObjects.mobileNumber_input, "value").equals(mobileNumber))
            new ProjectCustomException(getClassName(), getMethodName(), new Exception(), "Mobile Number after Retry in account blocker page is incorrect");
        waitUntilClickableOfAnElement(loginPageObjects.proceedOrVerifyOrSave_button);
    }

    public void clickLoginAsDifferentUserAndVerify(boolean otpPage) {
        if (otpPage)
            click(loginPageObjects.loginAsDifferentUser1FA_button);
        else
            click(loginPageObjects.loginAsDifferentAccount2FA_button);

        if (!getAttributeValue(loginPageObjects.mobileNumber_input, "value").trim().isEmpty())
            new ProjectCustomException(getClassName(), getMethodName(), new Exception(), "Mobile Number after 'Login As Different User' in account blocker page is not empty");
        waitUntilInVisibilityOfAnElement(loginPageObjects.proceedOrVerifyOrSave_button);
    }

    public void clickForgotPin() {
        click(loginPageObjects.forgotPin_button);
    }

    public void enterPANDetails(String panCard, boolean clearField) {
        if (clearField)
            loginPageObjects.enterPan_input.getWebElement().clear(); //todo - change after new framework update
        inputText(loginPageObjects.enterPan_input, panCard);
    }

    public void clickSaveButton(boolean positiveFlow) {
        click(loginPageObjects.proceedOrVerifyOrSave_button);
        if (!(positiveFlow && verifyElementDisplayed(loginPageObjects.otp1FA_input) || !positiveFlow && verifyElementDisplayed(loginPageObjects.invalidPANError_text)))
            new ProjectCustomException(getClassName(), getMethodName(), new Exception(), "Validate to fail PAN card validation in reset pin flow " + (positiveFlow ? "positive flow" : "negative flow"));
    }

    public void clickVerifyRestPin() {
        click(loginPageObjects.proceedOrVerifyOrSave_button);
    }

    public void enterAndReEnterPin(String pin) {
        for (LocatorDetails locatorDetails : new LocatorDetails[]{loginPageObjects.enter6DigitNewPin_input, loginPageObjects.reEnter6DigitNewPin_input}) {
            List<WebElement> elements = getElements(locatorDetails);

            for (int i = pin.length() - 1; i > -1; i--)
                inputText(elements.get(i), String.valueOf(pin.charAt(i)), "Entering PIN");
        }
    }

    public void clickSaveButtonResetPin() {
    }

    public void clickProfileOptionsDropDown() {
        waitUntilPresenceOfAnElement(loginPageObjects.optionDropDown_button);
        waitUntilClickableOfAnElement(loginPageObjects.optionDropDown_button);
        click(loginPageObjects.optionDropDown_button);
    }

    public void clickProfile() {
        click(loginPageObjects.profileOption_button);
    }

    public String getMPin(String pin) {
        return decode(pin);
    }
}
