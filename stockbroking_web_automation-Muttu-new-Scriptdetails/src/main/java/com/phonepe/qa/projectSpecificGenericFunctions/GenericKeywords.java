package com.phonepe.qa.projectSpecificGenericFunctions;

import com.phonepe.webautomationframework.customEntities.CustomReporter;
import com.phonepe.webautomationframework.generic.ProjectCustomException;
import com.phonepe.webautomationframework.generic.keywords.LocatorDetails;
import com.phonepe.webautomationframework.generic.keywords.SeleniumGenericKeywords;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.Map;


public class GenericKeywords extends SeleniumGenericKeywords {

    protected Response getCall(String url) {
        return RestAssured.given().get(url);
    }

    protected JsonPath getCall(String url, Map<String, Object> header) {
        return RestAssured.given().headers(header).get(url).getBody().jsonPath();
    }

    protected JsonPath postCall(String url, Map<String, String> header, JSONObject body) {
        return RestAssured.given().relaxedHTTPSValidation().headers(header).body(body).post(url).getBody().jsonPath();
    }


    public String getData(List<String> testData, String columnName) {
        return testData.get(columnNumber(columnName));
    }


    public boolean waitUntilPresenceOfAttributeValue(LocatorDetails locatorDetails, String attribute, String value) {
        return waitUntilPresenceOfAttributeValue(explicitWaitTime, locatorDetails, attribute, value);
    }

    public boolean waitUntilPresenceOfAttributeValue(int seconds, LocatorDetails locatorDetails, String attribute, String value) {
        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(seconds)).until(ExpectedConditions.domPropertyToBe(locatorDetails.getWebElement(), attribute, value));
            return true;
        } catch (TimeoutException t) {
            return false;
        }
    }

}

