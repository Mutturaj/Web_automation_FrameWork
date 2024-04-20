package com.phonepe.qa.stockBroking.pageObjects;
import com.phonepe.webautomationframework.generic.keywords.LocatorDetails;


public class LoginPageObjects {

//    public LocatorDetails nextIconToAll = new LocatorDetails("tagname", "a","torightof","xpath", "//div[text()='All']", "Next Icon to All");

    public LocatorDetails mobileNumber_input = new LocatorDetails("id", "mobileNumber", "Mobile Number Input Field");
    public LocatorDetails termsAndCondition_hyperLink = new LocatorDetails("xpath", "//a[text() = 'Terms & Conditions']", "Terms and Conditions hyperlink");
    public LocatorDetails proceedOrVerifyOrSave_button = new LocatorDetails("xpath", "//button[@type='submit' and not(@disabled)]", "Proceed Or Verify Or Save Button");
    public LocatorDetails mobileNumber1FA_text = new LocatorDetails("xpath", "//div[contains(text(),'Please enter the 5 digit code sent to')]", "1FA Mobile Number");
    public LocatorDetails resendCode_button = new LocatorDetails("xpath", "//span[text()='Resend Code']", "Resend Code Button");
    public LocatorDetails resendCodeTimer_text = new LocatorDetails("xpath", "//span[text()='Resend Code']/span", "Resend Code time");
    public LocatorDetails goBack_button = new LocatorDetails("xpath", "//span[text()='Go Back']", "'Go Back' button");
    public LocatorDetails invalidOTP_text = new LocatorDetails("xpath", "//div[contains(text(),'Invalid otp. ')]", "Invalid OTP Attempts time");
    public LocatorDetails retryCodeTimer_text = new LocatorDetails("xpath", "//span[text()='Retry']/span", "Retry Code time");
    public LocatorDetails loginAsDifferentUser1FA_button = new LocatorDetails("xpath", "//span[text()='Login as Different User']", "'Login as Different User' button");
    public LocatorDetails contactSupport_button = new LocatorDetails("xpath", "//span[text()='Contact Support']", "'Contact Support' button");
    public LocatorDetails otp1FA_input = new LocatorDetails("xpath", "//input[starts-with(@aria-label,'otp input ')]", "1FA OTP Input");
    public LocatorDetails loggedInSuccessful1FA_toaster = new LocatorDetails("xpath", "//div[text()='Logged in successfully!']", "'Logged in successfully!' toaster after 1FA");



    //2FA
    public LocatorDetails mobileNumber2FA_input = new LocatorDetails("xpath", "//span[text()='Hello, ']/../../div[2]/span", "Mobile Number Text 2FA");
    public LocatorDetails pin2FA_input = new LocatorDetails("xpath", "//input[contains(@aria-label,'otp input ')]", "2FA PIN Input");
    public LocatorDetails forgotPin_button = new LocatorDetails("xpath", "//span[text()='Forgot PIN?']", "Forgot Pin button");
    public LocatorDetails loginAsAnotherAccount_button = new LocatorDetails("xpath", "//span[text()='Log into another account']", "Log into another account button");
    public LocatorDetails successful2FA_toaster = new LocatorDetails("xpath", "//div[text()='2Fa done successfully!']", "'2Fa done successfully!' toaster");
    public LocatorDetails enterPan_input = new LocatorDetails("xpath", "//label[text()='Enter PAN']//following::input", "'Enter PAN' textbox");
    public LocatorDetails enter6DigitNewPin_input = new LocatorDetails("xpath", "//label[text()='Enter your new 6 digit PIN']/../..//input[starts-with(@aria-label,'otp input ')]", "'Enter 6 digit PIN' textbox");
    public LocatorDetails reEnter6DigitNewPin_input = new LocatorDetails("xpath", "//label[text()='Re-enter PIN']/../..//input[starts-with(@aria-label,'otp input ')]", "'Re-Enter 6 digit PIN' textbox");
    public LocatorDetails loginAsDifferentAccount2FA_button = new LocatorDetails("xpath", "//span[text()='Log into another account']", "'Log into another account' button");
    public LocatorDetails invalidPANError_text = new LocatorDetails("xpath", "//small[text()='Please enter a valid PAN number associated to your account']", "Invalid PAN error message");







    //Account blocker Page
    public LocatorDetails accountBlocker_text = new LocatorDetails("xpath", "//h1[text()='Account Blocked Temporarily']", "'Account Blocked Temporarily' text");
    public LocatorDetails retry_button = new LocatorDetails("xpath", "//span[text()='Retry']", "'Retry' button in blocker page");
//    public LocatorDetails loginAsDifferentUser1FA_button = new LocatorDetails("xpath", "//span[text()='Login as Different User']", "'Login as Different User' button");
//    public LocatorDetails contactSupport_button = new LocatorDetails("xpath", "//span[text()='Log into another account']", "Log into another account button");
//    public LocatorDetails loginAsAnotherAccount_button = new LocatorDetails("xpath", "//span[text()='Log into another account']", "Log into another account button");




    //error
    //fixme- fix the relative locator public LocatorDetails sessionExpiry_toaster = new LocatorDetails("xpath", "//div[text()='Session Expired']","torightof","xpath", "//div[@class='go2534082608']", "Session Expiry");
    public LocatorDetails sessionExpiry_toaster = new LocatorDetails("xpath", "//div[text()='Session Expired']", "Session Expiry");


    public LocatorDetails profileOption_button = new LocatorDetails("xpath", "//span[text()='Profile']", "'Profile' button");
    public LocatorDetails logout_button = new LocatorDetails("xpath", "//span[text()='Logout']", "'Logout' button");
    public LocatorDetails reports_button = new LocatorDetails("xpath", "//span[text()='Reports']", "'Logout' button");

    //fixme - refactor below xpath
    public LocatorDetails optionDropDown_button = new LocatorDetails("xpath", "//button","torightof","xpath", "//span[text()='Funds:']/parent::button", "User Options DropDown");



}

