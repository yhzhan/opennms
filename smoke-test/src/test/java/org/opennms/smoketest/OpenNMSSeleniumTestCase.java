/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2011-2012 The OpenNMS Group, Inc.
 * OpenNMS(R) is Copyright (C) 1999-2012 The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is a registered trademark of The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * OpenNMS(R) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenNMS(R).  If not, see:
 *      http://www.gnu.org/licenses/
 *
 * For more information contact:
 *     OpenNMS(R) Licensing <license@opennms.org>
 *     http://www.opennms.org/
 *     http://www.opennms.com/
 *******************************************************************************/

package org.opennms.smoketest;

import org.junit.After;
import org.junit.Before;
import org.opennms.core.test.MockLogAppender;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.thoughtworks.selenium.SeleneseTestBase;

public class OpenNMSSeleniumTestCase extends SeleneseTestBase {

    protected static final String LOAD_TIMEOUT = "60000";

    @Before
    public void setUp() throws Exception {
        MockLogAppender.setupLogging(true, "DEBUG");

        // Google Chrome
        // System.setProperty("webdriver.chrome.driver", "/Users/ranger/Downloads/chromedriver");
        // WebDriver driver = new ChromeDriver();

        // Selenium remote server
        // DesiredCapabilities capability = DesiredCapabilities.firefox();
        // WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);

        // Firefox
        WebDriver driver = new FirefoxDriver();

        String baseUrl = "http://localhost:8980/";
        selenium = new WebDriverBackedSelenium(driver, baseUrl);
        selenium.open("/opennms/login.jsp");
        selenium.type("name=j_username", "admin");
        selenium.type("name=j_password", "admin");
        selenium.click("name=Login");
        waitForPageToLoad();
    }

    @After
    public void tearDown() throws Exception {
        selenium.stop();
    }

    protected void waitForPageToLoad() {
        selenium.waitForPageToLoad(LOAD_TIMEOUT);
    }

}
