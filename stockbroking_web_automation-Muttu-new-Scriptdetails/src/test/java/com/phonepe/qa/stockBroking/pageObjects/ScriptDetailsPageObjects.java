package com.phonepe.qa.stockBroking.pageObjects;

import com.phonepe.webautomationframework.generic.keywords.LocatorDetails;

public class ScriptDetailsPageObjects {

//    public LocatorDetails nextIconToAll = new LocatorDetails("tagname", "a","torightof","xpath", "//div[text()='All']", "Next Icon to All");

    //public LocatorDetails watchlist_page=new LocatorDetails("xpath","//div[@class='tbody']","Watchlistpage");
    public LocatorDetails script_name = new LocatorDetails("xpath", "//span[text()='EQUITY']/following::span[contains(text(),'.')][1]", "Script name");
    public LocatorDetails script_Ltp = new LocatorDetails("xpath", "//span[contains(text(),'₹') and @style]", "Script Ltp");
    public LocatorDetails percentage_change = new LocatorDetails("xpath", "//*[local-name()='svg']/following::span[contains(text(),'(')]", "PercentageChange");

    // OverviewTab
    public LocatorDetails overview_Tab = new LocatorDetails("xpath", "//span[text()='Overview']", "Overview");
    public LocatorDetails marketdepth = new LocatorDetails("xpath", "//span[.='Market Depth']", "Marketdepth");
    public LocatorDetails openValues = new LocatorDetails("xpath", "//span[text()='Open']/following-sibling::span", "opnevalues");
    public LocatorDetails volume = new LocatorDetails("xpath", "//span[text()='Volume']/following-sibling::span", "Volume");
    public LocatorDetails prev_close = new LocatorDetails("xpath", "//span[text()='Prev Close']/following-sibling::span", "prev_close");
    public LocatorDetails upperCircuit = new LocatorDetails("xpath", "//span[text()='Upper Circuit']/following-sibling::span", "upper-circuit value");
    public LocatorDetails lowerCircuit = new LocatorDetails("xpath", "//span[text()='Lower Circuit']/following-sibling::span", "lower-circuit value");
    public LocatorDetails avgprice = new LocatorDetails("xpath", "//span[text()='Avg  Price']/following-sibling::span", "Avg_price");
    public LocatorDetails todayslow = new LocatorDetails("xpath", "//span[text()=\"Today's Low\"]/preceding-sibling::span", "Today'sLow");
    public LocatorDetails todayshigh = new LocatorDetails("xpath", "//span[text()=\"Today's High\"]/preceding-sibling::span", "Today'sHigh");

    //FundamentalTab
    public LocatorDetails fundamental_Tab = new LocatorDetails("xpath", "//span[text()='Fundamentals']", "Fundamental");
    public LocatorDetails keystats = new LocatorDetails("xpath", "//span[text()='Market Cap']/following::span[@class='css-1p6r367 euugi070']", "All key stats values");

    public LocatorDetails cashFlowvalues = new LocatorDetails("xpath", "(//div[@class='css-1c6mkvr eyl4nne3'])[2]//div[@class='right-col']//span", "All Cash_Flow values");

    public LocatorDetails total_current_assets = new LocatorDetails("xpath", "//span[text()='Total Current Assets']/following::div[1]/span", "Total Current Assets");

    public LocatorDetails debt_to_assets = new LocatorDetails("xpath", "//span[text()='Debt to Assets']/following::div[1]/span", "Debt_to_Assets");

    public LocatorDetails currentincomestatment = new LocatorDetails("xpath", "//div[@class='center']//span", "Current Income Statment");
    public LocatorDetails incomstatmentQQYYchange = new LocatorDetails("xpath", "(//div[@class='css-1c6mkvr eyl4nne3'])[1]//div[@class='right-col']//span", "Q/Q change");
    public LocatorDetails annualbutton = new LocatorDetails("xpath", "//span[text()='Annual']", "Annual button");
    public LocatorDetails info = new LocatorDetails("xpath", "//span[.='Info & Management']/following::span[1]", "Company info");
    public LocatorDetails managmentdetils = new LocatorDetails("xpath", "//div[@class='css-4zleql e1p9zn771']", "All mangers name");
    public LocatorDetails shareholdingbuttons = new LocatorDetails("xpath", "//span[text()='A change in their holdings can indicate performance outlook for the stock.']/following::button/span[contains(text(),'20')]", "Shore holdings buttons");
    public LocatorDetails shareholdingpattern = new LocatorDetails("xpath", "//span[text()='A change in their holdings can indicate performance outlook for the stock.']/following::div/span[contains(text(),'%')]", "Shareholding Pattern values");

    //TechnicalTab
    public LocatorDetails technicals_tab = new LocatorDetails("xpath", "//span[text()='Technicals']", "Technicals");
    public LocatorDetails SMA_Button = new LocatorDetails("xpath", "(//span[text()='SMA'])", "SMA");
    public LocatorDetails ema_button = new LocatorDetails("xpath", "(//span[text()='EMA'])", "SMA");
    public LocatorDetails indicators = new LocatorDetails("xpath", "//span[text()='RSI']/following::span[@class='css-1p6r367 euugi070']", "Indicators values");
    public LocatorDetails smavalues = new LocatorDetails("xpath", "//div[@class='tabPanel css-1st9kbp e1hc63d21']//div[@class='css-jo9s60 e1ir0mqx0']//span[@class='css-16vm9yz euugi070']", "All SMA values");
    public LocatorDetails emavalues = new LocatorDetails("xpath", "//div[@class='tabPanel css-1st9kbp e1hc63d21']//div[@class='css-jo9s60 e1ir0mqx0']//span[@class='css-16vm9yz euugi070']", "All EMA values");
    public LocatorDetails pivotvalue = new LocatorDetails("xpath", "//div/span[text()='PIVOT']/preceding::div[1]/span", "Pivot Value");
    public LocatorDetails resistance_R1 = new LocatorDetails("xpath", "//span[text()='R1']/following-sibling::span", "Resistance_R1");
    public LocatorDetails resistance_R2 = new LocatorDetails("xpath", "//span[text()='R2']/following-sibling::span", "Resistance_R2");
    public LocatorDetails resistance_R3 = new LocatorDetails("xpath", "//span[text()='R3']/following-sibling::span", "Resistance_R3");
    public LocatorDetails support_S1 = new LocatorDetails("xpath", "//span[text()='S1']/following-sibling::span", "Support_S1");
    public LocatorDetails support_S2 = new LocatorDetails("xpath", "//span[text()='S2']/following-sibling::span", "Support_S2");
    public LocatorDetails support_S3 = new LocatorDetails("xpath", "//span[text()='S3']/following-sibling::span", "Support_S3");
    public LocatorDetails volumeanalysisbutton = new LocatorDetails("xpath", "//span[text()='Volume Analysis']", "Volume Anaiysis button");

    //EventsTab
    public LocatorDetails events_Tab = new LocatorDetails("xpath", "//span[text()='Events']", "Events");
    public LocatorDetails eventstypes = new LocatorDetails("xpath", "//span[text()='Dividends']/following::div/span[contains(text(),'Ltd.')]", "Events Types");

    //NewsTab
    public LocatorDetails news_tab = new LocatorDetails("xpath", "//span[text()='News']", "News");
    public LocatorDetails newtext = new LocatorDetails("xpath", "//div[@class='css-qi3l5g e1yyitpu0']", "News Content");
    public LocatorDetails newstitle = new LocatorDetails("xpath", "//span[text()='News']/following::a/div[last()]/span", "Title of the News");
    public LocatorDetails newssource = new LocatorDetails("xpath", "//span[text()='News']/following::a/div[1]/span[1]", "Nes Sources");
    public LocatorDetails CorporateAnnouncementbutton = new LocatorDetails("xpath", " //span[text()='Corporate Announcements']", "Corporate Announcements button");
    public LocatorDetails corporateannouncements = new LocatorDetails("xpath", " //span[text()='Corporate Announcements']/following::a/div[last()]/span", "Corporate Announcements");
    public LocatorDetails reportsbutton = new LocatorDetails("xpath", "//span[text()='Reports']", "Repotrs button");

    //RelatedTab
    public LocatorDetails related_Tab = new LocatorDetails("xpath", "//span[text()='Related']", "Related");
    public LocatorDetails similar_Stocks = new LocatorDetails("xpath", "//span[text()='Similar Stocks']/following::section[@class='nameWrap']/h1", "Similar_Stocks");

}



