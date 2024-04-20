package com.phonepe.qa.stockBroking.testclass;

import com.phonepe.qa.customEntities.CustomCapabilities;
import com.phonepe.qa.stockBroking.pages.ScriptDetailsPage;
import com.phonepe.webautomationframework.TestExecutionEngine;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

import com.phonepe.webautomationframework.customEntities.DataProviderAnnotation;

public class ScriptDetailsPageTest extends TestExecutionEngine {

    ScriptDetailsPage scrip_values = new ScriptDetailsPage();
    SoftAssert softAssert = new SoftAssert();
    CustomCapabilities custom=new CustomCapabilities();
    static boolean login = true;

    @BeforeSuite(alwaysRun = true)
    public void setBrowserLaunch(ITestContext context) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Welcome\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        custom.setCustomCapabilities(new DesiredCapabilities(),scrip_values.getProperty("baseURL"));
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Object[] combinations) {
        ArrayList<String> testData = (ArrayList<String>) combinations[0];
        if (login) {
            new LoginPageTest().loginToTheApplication(testData);
            login = false;
        }
    }

    @DataProviderAnnotation(sheetName = {"scripts"}, keywords = {"scrip_3"})
    @Test(description = "Load url", groups = {"group1"})
    public void A_load_url(List<String> testData) throws InterruptedException {
        String inputInstrument = scrip_values.getData(testData, "instrumentId");
        scrip_values.loadURL(scrip_values.getProperty("baseURL") + "scrip?scripId=" + inputInstrument);
        // String pin = loginPage.getMPin(loginPage.getData(testData, "Pin"));
        //loginPage.enterOTPOrPin(pin, true);
    }

    @DataProviderAnnotation(sheetName = {"scripts"}, keywords = {"scrip_3"})
    @Test(description = "Verify all values in Overview  Tab")
    public void B_VerifyOverviewTabValues(List<String> testData) throws InterruptedException {
        String bodyquary = scrip_values.getData(testData, "QuaryParameter");
        String inputInstrument = scrip_values.getData(testData, "instrumentId");
        Assert.assertEquals(scrip_values.Scriptname(), scrip_values.scriptname(bodyquary));
        Assert.assertEquals(scrip_values.ChangeValue(), scrip_values.ChnagevalueCalculation());
        Assert.assertEquals(scrip_values.OverviewValues(), scrip_values.getFlashservicesOnOverivew(inputInstrument));
        Assert.assertEquals(scrip_values.ChangeValue(), scrip_values.ScriptPercentagecalcluation());

    }

    @DataProviderAnnotation(sheetName = {"scripts"}, keywords = {"scrip_3"})
    @Test(description = "Verify all values in Fundamental_tab")
    public void C_VerifyFundamentalTab_Values(List<String> testData) {
        scrip_values.Fundamental_tab();
//        String bodyquary9=scrip_values.getData(testData, "QuaryParameter");
//        Assert.assertEquals(scrip_values.shareholdingvalues(),scrip_values.getCubeserviceOnShareHolding(bodyquary9));
//        String bodyquary10=scrip_values.getData(testData, "QuaryParameter");
//        Assert.assertEquals(scrip_values.info(),scrip_values.getCubeServiceOnInfo(bodyquary10));
//        String bodyquary2=scrip_values.getData(testData, "QuaryParameter");
//        Assert.assertEquals(scrip_values.CashFlowValues(),scrip_values.getCubeServiceOnCashFlow(bodyquary2));
//        String bodyquary=scrip_values.getData(testData, "QuaryParameter");
//        Assert.assertEquals(scrip_values.KeyStats(),scrip_values.getCubeServiceOnKeyStats(bodyquary));
//        String bodyquary6=scrip_values.getData(testData, "QuaryParameter");
//        Assert.assertEquals(scrip_values.CureentIncomestatment(),scrip_values.getCubservicesOnQuarterlyIncomeStament1(bodyquary6));
//        String bodyquary1=scrip_values.getData(testData, "QuaryParameter");
//        Assert.assertEquals(scrip_values.IncomestatmentQQYYchange(),scrip_values.getCubservicesOnQuarterlyIncomeStament2(bodyquary1));
//        scrip_values.Annualbutton();
//        String bodyquary7=scrip_values.getData(testData, "QuaryParameter");
//        Assert.assertEquals(scrip_values.CureentIncomestatment(),scrip_values.getCubservicesOnAnnualIncomeStament1(bodyquary7));
//        String bodyquary8=scrip_values.getData(testData, "QuaryParameter");
//        Assert.assertEquals(scrip_values.IncomestatmentQQYYchange(),scrip_values.getCubservicesOnAnnualIncomeStament2(bodyquary8));
//        String bodyquary4=scrip_values.getData(testData, "QuaryParameter");
//        Assert.assertEquals(scrip_values.Managmentdetails(),scrip_values.getCubservicesOnMangment(bodyquary4));
//        String bodyquary3=scrip_values.getData(testData, "QuaryParameter");
//        Assert.assertEquals(scrip_values.BalanceSheet(),scrip_values.getCubserviceonBalnceSheet(bodyquary3));
        String[][] testCases = {
                {"QuaryParameter", "shareholdingvalues", "getCubeserviceOnShareHolding"},
                {"QuaryParameter", "info", "getCubeServiceOnInfo"},
                {"QuaryParameter", "CashFlowValues", "getCubeServiceOnCashFlow"},
                {"QuaryParameter", "KeyStats", "getCubeServiceOnKeyStats"},
                {"QuaryParameter", "CureentIncomestatment", "getCubservicesOnQuarterlyIncomeStament1"},
                {"QuaryParameter", "IncomestatmentQQYYchange", "getCubservicesOnQuarterlyIncomeStament2"},
                {"QuaryParameter", "CureentIncomestatment", "getCubservicesOnAnnualIncomeStament1"},
                {"QuaryParameter", "IncomestatmentQQYYchange", "getCubservicesOnAnnualIncomeStament2"},
                {"QuaryParameter", "Managmentdetails", "getCubservicesOnMangment"},
                {"QuaryParameter", "BalanceSheet", "getCubserviceonBalnceSheet"},
        };
        for (String[] testCase : testCases) {
            String bodyquary = scrip_values.getData(testData, testCase[0]);
            String actual = testCase[1] + "()";
            String expected = testCase[2] + "(" + bodyquary + ")";
            Assert.assertEquals(actual, expected);
        }
    }

    @DataProviderAnnotation(sheetName = {"scripts"}, keywords = {"scrip_3"})
    @Test(description = "Verify all values in Technical Tab")
    public void D_VerifyTechnicalTab_Values(List<String> testData) throws InterruptedException {
        scrip_values.TechnicalTab();
//        String bodyquary=scrip_values.getData(testData, "QuaryParameter");
//        String bodyquary1=scrip_values.getData(testData, "QuaryParameter");
//        String bodyquary2=scrip_values.getData(testData, "QuaryParameter");
//        Assert.assertEquals(scrip_values.Indicators(),scrip_values.getCubeservicesOnIndicators(bodyquary));
//        Assert.assertEquals(scrip_values.SMAEMAalues(),scrip_values.getCubeservicesOnSMAandEMA(bodyquary1));
//        Assert.assertEquals(scrip_values.PivotAnalysis(),scrip_values.getCubeservicesOnPivotAnalysis(bodyquary2));

        String[][] testCases = {
                {"QuaryParameter", "Indicators", "getCubeservicesOnIndicators"},
                {"QuaryParameter", "SMAEMAalues", "getCubeservicesOnSMAandEMA"},
                {"QuaryParameter", "PivotAnalysis", "getCubeservicesOnPivotAnalysis"}
        };
        for (String[] testCase : testCases) {
            String bodyquary = scrip_values.getData(testData, testCase[0]);
            String actual = testCase[1] + "()";
            String expected = testCase[2] + "(" + bodyquary + ")";
            Assert.assertEquals(actual, expected);
        }
    }

    @DataProviderAnnotation(sheetName = {"scripts"}, keywords = {"scrip_3"})
    @Test(description = "Verify all values in Events Tab")
    public void E_VerifyEventsTab_Values(List<String> testData) throws InterruptedException {
        scrip_values.Eventstab();
        String inputInstrument = scrip_values.getData(testData, "instrumentId");
        Assert.assertEquals(scrip_values.Eventstypes(), scrip_values.getCubeserviceonEvents(inputInstrument));
    }

    @DataProviderAnnotation(sheetName = {"scripts"}, keywords = {"scrip_3"})
    @Test(description = "Verify all values in news Tab")
    public void F_VerifyNewsTab_Values(List<String> testData) throws InterruptedException {
        scrip_values.Newstab();
//       String inputInstrument2=scrip_values.getData(testData,"instrumentId");
//       String inputInstrument = scrip_values.getData(testData, "instrumentId");
//       String inputInstrument1 = scrip_values.getData(testData, "instrumentId");
//     Assert.assertEquals(scrip_values.NewsTitle(),scrip_values.getCubeServiceOnNewsTitle(inputInstrument));
//     Assert.assertEquals(scrip_values.NewsSource(),scrip_values.getCubeServiceOnNewsSource(inputInstrument1));
//     Assert.assertEquals(scrip_values.CorporateAnnouncements(),scrip_values.getCubeserviceOnCorporateAnnouncements(inputInstrument2));

        String[][] testCases = {
                {"QuaryParameter", "NewsTitle", "getCubeServiceOnNewsTitle"},
                {"QuaryParameter", "NewsSource", "getCubeServiceOnNewsSource"},
                {"QuaryParameter", "CorporateAnnouncements", "getCubeserviceOnCorporateAnnouncements"}
        };
        for (String[] testCase : testCases) {
            String bodyquary = scrip_values.getData(testData, testCase[0]);
            String actual = testCase[1] + "()";
            String expected = testCase[2] + "(" + bodyquary + ")";

            Assert.assertEquals(actual, expected);
        }
    }

    @DataProviderAnnotation(sheetName = {"scripts"}, keywords = {"scrip_3"})
    @Test(description = "Verify all values in Related  Tab")
    public void G_VerifyRelatedTab_Values(List<String> testData) throws InterruptedException {
        scrip_values.Related_tab();
        String inputInstrument1 = scrip_values.getData(testData, "instrumentId");
        Assert.assertEquals(scrip_values.Similar_stockss(), scrip_values.getCubeServicesOnSimilarStocks(inputInstrument1));
    }
}








