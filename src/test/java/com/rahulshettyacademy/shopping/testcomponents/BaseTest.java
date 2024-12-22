package com.rahulshettyacademy.shopping.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rahulshettyacademy.shopping.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDriver() {
		try {
			String projectPath = System.getProperty("user.dir");
			Properties props = new Properties();

			props.load(new FileInputStream(projectPath
					+ "//src//main//java//com//rahulshettyacademy//shopping//resources//Global.properties"));

			//String browserName = props.getProperty("browser");
			String browserName = (System.getProperty("browser"))!=null ? System.getProperty("browser") : props.getProperty("browser");
			
			if (browserName.equalsIgnoreCase("chrome")) {
				//ChromeOptions options = new ChromeOptions();
				//options.addArguments("headless");
				//driver = new ChromeDriver(options);
				driver = new ChromeDriver();

			} else if (browserName.equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver();

			}
			driver.manage().window().setSize(new Dimension(1440, 900));
			//driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

			// return driver;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return driver;
	}

	public List<HashMap<String, String>> getJsonDataMap(String filePath)  {

		List<HashMap<String, String>> data = null;
		// covert json to String
		String jsonContent;

		try {
			jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		
		// convert String to HashMap using Jackson Data Binding

		ObjectMapper mapper = new ObjectMapper();
		data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {

				});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;

	}

	@BeforeMethod(alwaysRun = true)
	public void launchApplication() {
		driver = initializeDriver();

		//return landingPage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
	public String getScreeshot(String testCaseName,WebDriver driver) {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		try {
			FileUtils.copyFile(source, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
		
	}

}
