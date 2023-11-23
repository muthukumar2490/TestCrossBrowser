package com.crossBrowser;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class CrossBrowser {

	WebDriver driver;

	@BeforeTest
	@Parameters({ "browser", "headless", "privateBrowsing" })
	public void LaunchBrowser(String browser, String headless, String privateBrowsing) {

		if (browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			if (headless.equalsIgnoreCase("yes")) {
				options.addArguments("--headless");
				driver = new ChromeDriver(options);
			} else if (privateBrowsing.equalsIgnoreCase("yes")) {
				options.addArguments("--incognito");
				driver = new ChromeDriver(options);
			} else
				driver = new ChromeDriver();

		}
		if (browser.equalsIgnoreCase("Edge")) {
			WebDriverManager.chromedriver().setup();
			EdgeOptions options = new EdgeOptions();
			if (headless.equalsIgnoreCase("yes")) {
				options.addArguments("--headless");
				driver = new EdgeDriver(options);
			}

			else if (privateBrowsing.equalsIgnoreCase("yes")) {
				options.addArguments("--InPrivate");
				driver = new EdgeDriver(options);
			} else
				driver = new EdgeDriver();
		}
		if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();

			if (headless.equalsIgnoreCase("yes")) {
				options.addArguments("--headless");
				driver = new FirefoxDriver(options);
			} else if (privateBrowsing.equalsIgnoreCase("yes")) {
				options.addArguments("-private");
				driver = new FirefoxDriver(options);
			} else
				driver = new FirefoxDriver();

		}

	}

	@Test(enabled = true)
	@Parameters({ "url", "data" })
	public void searchData(String url, String data) {
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		driver.findElement(By.name("q")).sendKeys(data);
		driver.findElement(By.name("q")).submit();

	}

	@AfterTest
	public void teardown() {

		driver.quit();

	}
}
