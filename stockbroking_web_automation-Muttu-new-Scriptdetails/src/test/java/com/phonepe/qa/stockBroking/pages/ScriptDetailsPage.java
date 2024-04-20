package com.phonepe.qa.stockBroking.pages;
import com.phonepe.qa.projectSpecificGenericFunctions.GenericKeywords;
import com.phonepe.qa.stockBroking.pageObjects.ScriptDetailsPageObjects;
import com.phonepe.webautomationframework.generic.keywords.LocatorDetails;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebElement;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class ScriptDetailsPage extends GenericKeywords {
    ScriptDetailsPageObjects scriptdetails_page = new ScriptDetailsPageObjects();
    DecimalFormat decfor=new DecimalFormat("0.00");

    private JsonPath getFlashservice(String inputInstrument) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("instrumentId", inputInstrument);
        requestParams.put("tickViewType", "LITE");
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("content-type", "application/json");
        return postCall(getProperty("flashService_endpoint"), map, requestParams);
    }
    private JsonPath getCubeservice(String bodyquery) {
       Response response = RestAssured.given().relaxedHTTPSValidation().baseUri(getProperty("cubeservice_endpoint")).queryParam("body", bodyquery).header("Accept-Encoding", "gzip, deflate, br").get("/instrument/elements");
        return response.jsonPath();
    }
    public List<String> getCubeserviceonEvents(String inputInstrument) {
         List<String> evnttypes = new ArrayList<>();
        String eventtype[] = {"DIVIDEND", "BONUS", "SPLIT"};
        for (int i = 0; i < eventtype.length; i++) {
            String res= RestAssured.given().relaxedHTTPSValidation().pathParam("id", inputInstrument).queryParam("pageSize", 10).queryParam("eventType", eventtype[i]).header("Accept-Encoding", "gzip, deflate, br").get(getProperty("cubeserviceOnEvents_endpoint")).body().path("data.insight");
          evnttypes.add(res);
        }
      return evnttypes;
    }
    public List<String> getCubeserviceOnCorporateAnnouncements(String inputInstrument) {
        String eventtype = "CORPORATE_ANNOUNCEMENT";
        return RestAssured.given().relaxedHTTPSValidation().pathParam("id", inputInstrument).queryParam("pageSize", 10).queryParam("eventType", eventtype).header("Accept-Encoding", "gzip, deflate, br").get(getProperty("cubeserviceOnEvents_endpoint")).body().path("data.data.title");
    }
    public List getCubeServiceOnNewsTitle(String inputInstrument) {
        return RestAssured.given().relaxedHTTPSValidation().pathParam("id", inputInstrument).queryParam("pageSize", 10).header("Accept-Encoding", "gzip, deflate, br").get(getProperty("cubeserviceOnNews_endpoint")).body().path("data.data.title");
    }
    public List getCubeServiceOnNewsSource(String inputInstrument) {
       return RestAssured.given().relaxedHTTPSValidation().pathParam("id", inputInstrument).queryParam("pageSize", 10).header("Accept-Encoding", "gzip, deflate, br").get(getProperty("cubeserviceOnNews_endpoint")).body().path("data.data.source");
    }
    public List getCubeServicesOnSimilarStocks(String inputInstrument) {
        return RestAssured.given().relaxedHTTPSValidation().pathParam("id", inputInstrument).queryParam("pageSize", 10).header("Accept-Encoding", "gzip, deflate, br").get(getProperty("cubeserviceOnPeers_endpoint")).body().path("data.data.name");
    }
    public List getFlashservicesOnOverivew(String inputInstrument) {
        JsonPath json1 = getFlashservice(inputInstrument);
        List str = new ArrayList<>();
        String arr[] = {"closePrice", "lowerCircuitLimit", "upperCircuitLimit", "highPrice", "lowPrice", "averageTradedPrice","volumeTradedToday", "lastTradedPrice"};
        for (int i = 0; i < arr.length; i++) {
            str.add(Math.floor(Double.valueOf(decfor.format(json1.getDouble("response." + arr[i]) / 100))));
        }
        return str;
    }

    //get script name & ltp & percentahgechange
    public String Scriptname() {
        waitUntilVisibilityOfAnElement(5, scriptdetails_page.script_name);
        return getText(scriptdetails_page.script_name);
    }
    public double Scriptltp() {
        waitUntilVisibilityOfAnElement(5,scriptdetails_page.script_Ltp);
       return Double.valueOf(getText(scriptdetails_page.script_Ltp).replace("₹", ""));
    }
    public double ChangeValue(){
       return Double.valueOf(getText(scriptdetails_page.percentage_change).split(" ")[0]);
    }
    public double ScriptPercentagecalcluation() {
        waitUntilVisibilityOfAnElement(5,scriptdetails_page.percentage_change);
        double str1 = Double.valueOf(getText(scriptdetails_page.percentage_change).replace("%", "").replaceAll("[\\[\\[\\](){}]","").split(" ")[1]);
        double str2=(str1*Scriptltp())/100;
        return Double.valueOf(decfor.format(str2));
    }
    public double ChnagevalueCalculation(){
        double st1=Double.valueOf(getText(scriptdetails_page.prev_close).replace("₹",""));
        double str2=Scriptltp();
        double str3=str2-st1;
        return Double.valueOf(decfor.format(str3));
    }
    public List OverviewValues() {
        scrollToAnElement(scriptdetails_page.marketdepth);
        waitUntilVisibilityOfAnElement(5, scriptdetails_page.prev_close);
//        List str = new ArrayList<>();
//        str.add(Math.floor(Double.valueOf(decfor.format(Double.valueOf(getText(scriptdetails_page.prev_close).replace("₹", "").replace(",",""))))));
//        str.add(Math.floor(Double.valueOf(decfor.format(Double.valueOf(getText(scriptdetails_page.lowerCircuit).replace("₹", "").replace(",",""))))));
//        str.add(Math.floor(Double.valueOf(decfor.format(Double.valueOf(getText(scriptdetails_page.upperCircuit).replace("₹", "").replace(",",""))))));
//        str.add(Math.floor(Double.valueOf(decfor.format(Double.valueOf(getText(scriptdetails_page.todayshigh).replace("₹", "").replace(",",""))))));
//        str.add(Math.floor(Double.valueOf(decfor.format(Double.valueOf(getText(scriptdetails_page.todayslow).replace("₹", "").replace(",",""))))));
//        str.add(Math.floor(Double.valueOf(decfor.format(Double.valueOf(getText(scriptdetails_page.avgprice).replace("₹", "").replace(",",""))))));
//        str.add(Integer.valueOf(decfor.format(getText(scriptdetails_page.volume))));
//        str.add(Math.floor(Double.valueOf(decfor.format(Double.valueOf(getText(scriptdetails_page.script_Ltp).replace("₹", "").replace(",",""))))));
//        return str;
//    }
        List<Double> values = Arrays.asList(
                Double.valueOf(getText(scriptdetails_page.prev_close).replace("₹", "").replace(",", "")),
                Double.valueOf(getText(scriptdetails_page.lowerCircuit).replace("₹", "").replace(",", "")),
                Double.valueOf(getText(scriptdetails_page.upperCircuit).replace("₹", "").replace(",", "")),
                Double.valueOf(getText(scriptdetails_page.todayshigh).replace("₹", "").replace(",", "")),
                Double.valueOf(getText(scriptdetails_page.todayslow).replace("₹", "").replace(",", "")),
                Double.valueOf(getText(scriptdetails_page.avgprice).replace("₹", "").replace(",", "")),
                Double.valueOf(getText(scriptdetails_page.script_Ltp).replace("₹", "").replace(",", ""))
        );
        List<Double> str = values.stream()
                .map(d -> Math.floor(Double.valueOf(decfor.format(d))))
                .collect(Collectors.toList());
        int volume = Integer.valueOf(decfor.format(getText(scriptdetails_page.volume)));
        str.add((double) volume);
        return str;
    }
    public int OpenValue() {
        return Integer.parseInt(getText(scriptdetails_page.openValues).replace("₹", ""));
    }

    //FundamentalTab Values
    public void Fundamental_tab() {
        click(scriptdetails_page.fundamental_Tab);
    }

    public List<Double> getCubeServiceOnKeyStats(String bodyquary) {
        JsonPath json = getCubeservice(bodyquary);
        List<Double> st = new ArrayList<>();
        String arr[] = {"Market Cap", "EPS", "Book Value", "P/E Ratio", "Industry PE", "P/B Ratio", "Debt to Equity", "ROE", "Dividend Yield"};
        for (int i = 0; i <arr.length; i++) {
            st.add(json.getDouble("data[1].data.items" + "[" + i + "]" + ".itemValue.value"));
        }
        return st;
    }
    public List<Double> KeyStats() {
        List<Double> listofkeystats = new ArrayList<>();
        for (WebElement ele : getElements(scriptdetails_page.keystats).subList(0, 9))
            listofkeystats.add(Double.valueOf(Double.valueOf(ele.getText().replace("₹","").replace(",",""))));
        return listofkeystats;
    }

    //Income Statement
    public List<Double> getCubservicesOnQuarterlyIncomeStament1(String bodyquary) {
        List<Double> all = new ArrayList<>();
        JsonPath json = getCubeservice(bodyquary);
        String arr[] = {"revenue", "otherIncome", "totalIncome", "totalExpenditure", "ebidt", "depreciation", "interest", "tax", "netProfit"};
        for (int i = 0; i < arr.length; i++) {
            all.add(Math.floor(Double.valueOf(decfor.format(json.getDouble("data[3].data.quarterly[0]" + "." + arr[i])))));
        }
        return all;
    }
    public List<Double> getCubservicesOnQuarterlyIncomeStament2(String bodyquary) {
//        List<Double> all = new ArrayList<>();
//        JsonPath json = getCubeservice(bodyquary);
//        String arr[] = {"revenue", "otherIncome", "totalIncome", "totalExpenditure", "ebidt", "depreciation", "interest", "tax", "netProfit"};
//        for (int i = 0; i < arr.length; i++) {
//            double current = json.getDouble("data[3].data.quarterly[0]" + "." + arr[i]);
//            double previous = json.getDouble("data[3].data.quarterly[1]" + "." + arr[i]);
//            double percent=Double.valueOf(decfor.format(((current-previous)/previous)*100));
//            all.add(percent);
//        }
//        return all;
//    }
        JsonPath json = getCubeservice(bodyquary);
        return Arrays.stream(new String[] {"revenue", "otherIncome", "totalIncome", "totalExpenditure", "ebidt", "depreciation", "interest", "tax", "netProfit"})
                .mapToDouble(item -> {
                    double current = json.getDouble("data[3].data.quarterly[0]" + "." + item);
                    double previous = json.getDouble("data[3].data.quarterly[1]" + "." + item);
                    return Double.parseDouble(decfor.format(((current - previous) / previous) * 100));
                }).boxed().collect(Collectors.toList());
    }
    public List<Double> getCubservicesOnAnnualIncomeStament1(String bodyquary) {
        List<Double> all = new ArrayList<>();
        JsonPath json = getCubeservice(bodyquary);
        String arr[] = {"revenue", "otherIncome", "totalIncome", "totalExpenditure", "ebidt", "depreciation", "interest", "tax", "netProfit"};
        for (int i = 0; i < arr.length; i++) {
            all.add(Math.floor(Double.valueOf(decfor.format(json.getDouble("data[3].data.annual[0]" + "." + arr[i])))));
        }
        return all;
    }
    public List<Double> getCubservicesOnAnnualIncomeStament2(String bodyquary) {
//        List<Double> all = new ArrayList<>();
//        JsonPath json = getCubeservice(bodyquary);
//        String arr[] = {"revenue", "otherIncome", "totalIncome", "totalExpenditure", "ebidt", "depreciation", "interest", "tax", "netProfit"};
//        for (int i = 0; i < arr.length; i++) {
//            double current = json.getDouble("data[3].data.annual[0]" + "." + arr[i]);
//            double previous = json.getDouble("data[3].data.annual[1]" + "." + arr[i]);
//            double percent=Double.valueOf(decfor.format(((current-previous)/previous)*100));
//            all.add(percent);
//        }
//        return all;
//    }
        JsonPath json = getCubeservice(bodyquary);
        String arr[] = {"revenue", "otherIncome", "totalIncome", "totalExpenditure", "ebidt", "depreciation", "interest", "tax", "netProfit"};
        return Arrays.stream(arr).mapToDouble(property -> {
                    double current = json.getDouble("data[3].data.annual[0]" + "." + property);
                    double previous = json.getDouble("data[3].data.annual[1]" + "." + property);
                    return Double.valueOf(decfor.format(((current-previous)/previous)*100));
                })
                .boxed().collect(Collectors.toList());
    }
    public List<Double> CureentIncomestatment() {
        List<Double> listofincome = new ArrayList<>();
        for (WebElement ele : getElements(scriptdetails_page.currentincomestatment).subList(1, 10))
            listofincome.add(Math.floor(Double.valueOf(decfor.format(Double.valueOf(ele.getText())))));
        return listofincome;
    }
    public List<Double> IncomestatmentQQYYchange() {
        List<Double> listofincome = new ArrayList<>();
        for (WebElement ele : getElements(scriptdetails_page.incomstatmentQQYYchange).subList(1, 10))
           listofincome.add(Double.valueOf(decfor.format(Double.valueOf(ele.getText().replace("%","").replaceFirst("-","")))));
        return listofincome;
    }
    public void Annualbutton() {
        scrollToAnElement(scriptdetails_page.annualbutton);
        click(scriptdetails_page.annualbutton);
    }

    //Cash Flow
    public List<Double> getCubeServiceOnCashFlow(String bodyquary) {
//        JsonPath json = getCubeservice(bodyquary);
//        List<Double> st=new ArrayList<>();
//        String arr[]={"operating","financing","investing","netCashFlow"};
//        for (int i=0;i< arr.length;i++){
//            st.add(json.getDouble("data[5].data.annual[0]."+arr[i]));
//        }
//        return st;
//    }
        JsonPath json = getCubeservice(bodyquary);
        String arr[]={"operating","financing","investing","netCashFlow"};
        return Arrays.stream(arr)
                .map(arrVal -> json.getDouble("data[5].data.annual[0]." + arrVal))
                .collect(Collectors.toList());
    }
    public List<Double> CashFlowValues(){
//            List<Double> listofCashflow = new ArrayList<>();
//            WebElement q;
//            List<WebElement> sortedName = getElements(scriptdetails_page.cashFlowvalues);
//            for (int i=1;i<sortedName.size();i++) {
//                q=sortedName.get(i);
//                listofCashflow.add(Double.valueOf(q.getText()));
//            }
//            return listofCashflow;
//        }
        List<WebElement> elements = getElements(scriptdetails_page.cashFlowvalues);
        return elements.stream()
                .skip(1)
                .map(q -> Double.valueOf(q.getText()))
                .collect(Collectors.toList());
    }

    // Balncesheet
    public Map<String,Double> getCubserviceonBalnceSheet(String bodyquary) {
        JsonPath json = getCubeservice(bodyquary);
        Map<String,Double> str1=new HashMap<>();
        String arr[]={"totalCurrentAsset","totalNonCurrentAsset","fixedAsset","totalAsset","totalCurrentLiability","totalNonCurrentLiability","totalCapitalPlusLiability","totalShareholderFund","debtToAsset"};
        for (int i=0;i< arr.length;i++){
            str1.put(arr[i],Double.valueOf(decfor.format(json.getDouble("data[4].data.annual[0]"+"."+arr[i]))));
        }
        return str1;
    }
public Map<String,Double> BalanceSheet() {
    String arr[] = {"totalCurrentAsset", "totalNonCurrentAsset", "fixedAsset", "totalAsset", "totalCurrentLiability", "totalNonCurrentLiability", "totalCapitalPlusLiability", "totalShareholderFund"};
    Map<String,Double> str2 = new HashMap<>();
    for (int i = 0; i < arr.length; i++) {
        str2.put( arr[i],Math.floor(Double.valueOf(decfor.format(Double.valueOf(getText(scriptdetails_page.total_current_assets))))));
        str2.put("debtToAsset", Double.valueOf(decfor.format(Double.valueOf(getText(scriptdetails_page.debt_to_assets).replace("%", "")) / 100)));
    }
        return str2;
    }
    //Shareholding Pattern
    public List<Double> shareholdingvalues() {
//       scrollToAnElement(scriptdetails_page.info);
//        List<Double> listofvalues = new ArrayList<>();
//        List<WebElement> list = getElements(scriptdetails_page.shareholdingbuttons);
//        for (int i = 0; i < list.size(); i++) {
//            list.get(i).click();
//            List<WebElement> list1 = getElements(scriptdetails_page.shareholdingpattern);
//            for (WebElement we : list1) {
//                listofvalues.add(Double.valueOf(we.getText().replace("%", "").replaceAll("[\\[\\[\\](){}]", "").split(" ")[0]));
//            }
//        }
//        return listofvalues;
//    }
        scrollToAnElement(scriptdetails_page.info);
        List<WebElement> buttons = getElements(scriptdetails_page.shareholdingbuttons);
        return buttons.stream().flatMap(button -> { button.click();
                    List<WebElement> pattern = getElements(scriptdetails_page.shareholdingpattern);
                    return pattern.stream();
                })
                .map(we -> Double.valueOf(we.getText().replace("%", "").replaceAll("[\\[\\[\\](){}]", "").split(" ")[0]))
                .collect(Collectors.toList());
    }
    public List<Double> getCubeserviceOnShareHolding(String bodyquary) {
        JsonPath json = getCubeservice(bodyquary);
        List<Double> str1 = new ArrayList<>();
        int numGroups = json.getInt("data[6].data.groupedShareholdings.size()");
        for (int i = 0; i < numGroups; i++) {
            int numHoldings = json.getInt("data[6].data.groupedShareholdings["+i+"].holdings.size()");
            for (int j = 0; j < numHoldings; j++) {
                Double value = json.getDouble("data[6].data.groupedShareholdings["+i+"].holdings["+j+"].shareholding.holdingPercentage");
                if (value != null) {
                    str1.add(value);
                }
            }
        }
        return str1;
    }

    //Info & mangment
    public String getCubeServiceOnInfo(String bodyquary) {
        JsonPath json = getCubeservice( bodyquary);
        return json.getString("data[7].data.about");
    }
    public List<String>  getCubservicesOnMangment(String bodyquary){
        return  RestAssured.given().relaxedHTTPSValidation().baseUri(getProperty("cubeservice_endpoint")).queryParam("body", bodyquary).header("Accept-Encoding", "gzip, deflate, br").get("/instrument/elements").body().path("data[7].data.management.name");
    }
    public String info() {
        return getText(scriptdetails_page.info);
    }
    public List<String> Managmentdetails() {
        List<String> listOfTitles = new ArrayList<>();
        List<WebElement> sortedName = getElements(scriptdetails_page.managmentdetils);
        for (WebElement we : sortedName) {
            listOfTitles.add(we.getText());
        }
        return listOfTitles;
    }

    //TechnicalTab
    public String scriptname(String bodyquery) {
        JsonPath json = getCubeservice(bodyquery);
        return json.getString("data[0].data.instrumentName");
    }
    public List<Double>getCubeservicesOnIndicators(String bodyquary) {
        JsonPath json = getCubeservice(bodyquary);
        List<Double> str1=new ArrayList<>();
        for (int i=0;i<8;i++){
            str1.add(json.getDouble("data[9].data.items"+"["+i+"]"+".itemValue.value"));
        }
        return str1;
    }
    public List<Double>Indicators(){
        List<Double> listofIndicators = new ArrayList<>();
        for (WebElement ele : getElements(scriptdetails_page.indicators).subList(0, 8))
            listofIndicators.add(Double.valueOf(Double.valueOf(ele.getText().replace("₹","").replace(",",""))));
        return listofIndicators;
    }
    public List<Float>getCubeservicesOnSMAandEMA(String bodyquary) {
        JsonPath json = getCubeservice(bodyquary);
        List<Map<String, Object>> movingAverages = json.getList("data[8].data.movingAverages");
        List<Float> emaValues = new ArrayList<>();
        if (movingAverages != null) {
            for (Map<String, Object> movingAverage : movingAverages) {
                Float sma = (Float) movingAverage.get("sma");
                if (sma != null) {
                    emaValues.add(sma);
                }
            }
            for (Map<String, Object> movingAverage : movingAverages) {
                Float ema = (Float) movingAverage.get("ema");
                if (ema != null) {
                    emaValues.add(ema);
                }
            }
        }
        return emaValues;
    }
   public void TechnicalTab() {
        click(scriptdetails_page.technicals_tab);
    }
public List<Float> SMAEMAalues() {
        scrollToAnElement(scriptdetails_page.volumeanalysisbutton);
        waitUntilVisibilityOfAnElement(5,scriptdetails_page.smavalues);
    List<Float> listofvalues = new ArrayList<>();
    List<WebElement> allvalues = getElements(scriptdetails_page.smavalues);
    for (WebElement we : allvalues) {
        listofvalues.add(Float.valueOf(we.getText()));
    }
    click(scriptdetails_page.ema_button);
    List<WebElement> allvalues1 = getElements(scriptdetails_page.smavalues);
    for (WebElement we1 : allvalues1) {
        waitUntilVisibilityOfAnElement(5,scriptdetails_page.emavalues);
        listofvalues.add(Float.valueOf(we1.getText()));
    }
    return listofvalues;
}
    public List<Double>getCubeservicesOnPivotAnalysis(String bodyquary) {
        JsonPath json = getCubeservice(bodyquary);
        List<Double> str1=new ArrayList<>();
        for (int i=0;i<7;i++){
            str1.add(Double.valueOf(decfor.format(json.getDouble("data[10].data.items["+i+"].itemValue.value"))));
        }
        return str1;
    }
    public List<Double>PivotAnalysis(){
//        List<Double> str1=new ArrayList<>();
//        str1.add(Double.valueOf(getText(scriptdetails_page.resistance_R1)));
//        str1.add(Double.valueOf(getText(scriptdetails_page.resistance_R2)));
//        str1.add(Double.valueOf(getText(scriptdetails_page.resistance_R3)));
//        str1.add(Double.valueOf(getText(scriptdetails_page.pivotvalue)));
//        str1.add(Double.valueOf(getText(scriptdetails_page.support_S1)));
//        str1.add(Double.valueOf(getText(scriptdetails_page.support_S2)));
//        str1.add(Double.valueOf(getText(scriptdetails_page.support_S3)));
//        return str1;
//    }
        List<LocatorDetails> elements = Arrays.asList(scriptdetails_page.resistance_R1, scriptdetails_page.resistance_R2, scriptdetails_page.resistance_R3,
                scriptdetails_page.pivotvalue, scriptdetails_page.support_S1, scriptdetails_page.support_S2, scriptdetails_page.support_S3);
        return elements.stream()
                .map(e -> Double.valueOf(getText(e)))
                .collect(Collectors.toList());
    }

    //Events Tab
    public void Eventstab() {
        waitUntilVisibilityOfAnElement(scriptdetails_page.events_Tab);
        click(scriptdetails_page.events_Tab);
    }
    public List<String> Eventstypes() {
        List<String> listOfEvents = new ArrayList<>();
        waitUntilVisibilityOfAnElement(5,scriptdetails_page.eventstypes);
        List<WebElement> events = getElements(scriptdetails_page.eventstypes);
        for (WebElement we : events) {
            listOfEvents.add(we.getText());
        }
        return listOfEvents;
    }

    //NewsTab
    public void Newstab() {
        click(scriptdetails_page.news_tab);
    }
    public String NewsContent() {
        return getText(scriptdetails_page.newtext);
    }
    public List<String> NewsTitle() {
        List<String> listOfTitles = new ArrayList<>();
        List<WebElement> sortedName = getElements(scriptdetails_page.newstitle);
        for (WebElement we : sortedName) {
            listOfTitles.add(we.getText());
        }
        return listOfTitles;
    }
    public List<String> NewsSource() {
        List<String> listOfTitles = new ArrayList<>();
        List<WebElement> sortedName = getElements(scriptdetails_page.newssource);
        for (WebElement we : sortedName) {
            listOfTitles.add(we.getText());
        }
        return listOfTitles;
    }
    public List CorporateAnnouncements(){
        scrollToAnElement(scriptdetails_page.reportsbutton);
        click(scriptdetails_page.CorporateAnnouncementbutton);
        List<String> listOfannouncment = new ArrayList<>();
        List<WebElement> sortedName = getElements(scriptdetails_page.corporateannouncements);
        for (WebElement we : sortedName) {
            listOfannouncment.add(we.getText());
        }
        return listOfannouncment;
    }

    //RelatedTab
    public void Related_tab() {
        click(scriptdetails_page.related_Tab);
    }
    public List<String> Similar_stockss() throws InterruptedException {
        List<String> listOfTitles = new ArrayList<>();
        List<WebElement> sortedName = getElements(scriptdetails_page.similar_Stocks);
        for (WebElement we : sortedName) {
            listOfTitles.add(we.getText());
        }
        return listOfTitles;
    }
}




