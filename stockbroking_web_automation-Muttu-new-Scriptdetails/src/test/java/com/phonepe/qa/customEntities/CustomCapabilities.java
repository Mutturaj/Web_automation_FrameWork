package com.phonepe.qa.customEntities;

import com.phonepe.webautomationframework.generic.Capabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Collections;


public class CustomCapabilities extends Capabilities {

    public void setCustomCapabilities(DesiredCapabilities capabilities,String baseURL) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--incognito");
        options.addArguments("--start -maximized");
//        options.setHeadless(true);
        options.setExperimentalOption("prefs", Collections.singletonMap("plugins.always_open_pdf_externally", true));
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
    }
}
