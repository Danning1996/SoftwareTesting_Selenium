package testSelenium;

//package com.example.tests;

import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

public class TestSelenium {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  public static String stNum;
  public static String stPw;
  public static String gitAd;
  
  public String inString  = "";	
  public String delimeter = ",";
  public BufferedReader reader;
  
  public void read() {
		
		File readFile = new File("e:\\SoftwareTesting\\Lab2\\inputgit.csv");
		try{
			reader = new BufferedReader(new FileReader(readFile));
//			String inString  = "";		
//			String delimeter = ",";
//			String[] info;
//			int flag = 0;
//			
//			while((inString  = reader.readLine()) != null){
//				if(flag > 0){
//					info = inString.split(delimeter);
//					stNum = info[0];
//					stPw = stNum.substring(4, 10);
//					gitAd = info[2];
//				}
//				flag ++;
//			}
//			reader.close();
		}
		catch (FileNotFoundException ex) {
          System.out.println("No found!");
      } catch (IOException ex) {
          System.out.println("Reading error£¡");
      }
		
	}
  
  @Before
  public void setUp() throws Exception {
	//find the Firefox path binary 
	File pathToBinary = new File("d:\\program\\Firefox\\firefox.exe");
	FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
	FirefoxProfile firefoxProfile = new FirefoxProfile(); 
	//open the specified url
	
	driver = new FirefoxDriver(ffBinary,firefoxProfile);
	
    baseUrl = "http://121.193.130.195:8080";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    //initialize the info of a student
    //stNum = "3014218140";
    //stPw = "218140";
    //gitAd = "https://github.com/Danning1996";
  }

  @Test
  public void testSelenium() throws Exception {
	read();
	int flag = 0;
	String[] info;
	while((inString  = reader.readLine()) != null){
		if(flag > 0){
			info = inString.split(delimeter);
			stNum = info[0];
			stPw = stNum.substring(4, 10);
			gitAd = info[2];
			driver.get(baseUrl + "/");
		    driver.findElement(By.id("name")).clear();
		    driver.findElement(By.id("name")).sendKeys(stNum);
		    driver.findElement(By.id("pwd")).clear();
		    driver.findElement(By.id("pwd")).sendKeys(stPw);
		    driver.findElement(By.id("submit")).click();
		    
		    assertEquals(gitAd, driver.findElement(By.xpath("//tbody[@id='table-main']/tr[3]/td[2]")).getText());
		  
//			break;
		}
		flag ++;
	}
	reader.close();
//    driver.get(baseUrl + "/");
//    driver.findElement(By.id("name")).clear();
//    driver.findElement(By.id("name")).sendKeys(stNum);
//    driver.findElement(By.id("pwd")).clear();
//    driver.findElement(By.id("pwd")).sendKeys(stPw);
//    driver.findElement(By.id("submit")).click();
//    
//    assertEquals(gitAd, driver.findElement(By.xpath("//tbody[@id='table-main']/tr[3]/td[2]")).getText());
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
//
//  private boolean isElementPresent(By by) {
//    try {
//      driver.findElement(by);
//      return true;
//    } catch (NoSuchElementException e) {
//      return false;
//    }
//  }
//
//  private boolean isAlertPresent() {
//    try {
//      driver.switchTo().alert();
//      return true;
//    } catch (NoAlertPresentException e) {
//      return false;
//    }
//  }
//
//  private String closeAlertAndGetItsText() {
//    try {
//      Alert alert = driver.switchTo().alert();
//      String alertText = alert.getText();
//      if (acceptNextAlert) {
//        alert.accept();
//      } else {
//        alert.dismiss();
//      }
//      return alertText;
//    } finally {
//      acceptNextAlert = true;
//    }
//  }
}
