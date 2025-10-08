package pom.hrm.pages.Dashboard.SideMenu;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.WaitUtils;

public class SideMenu {
	
	WebDriver driver;
	WebDriverWait wait;
	
	//Constructor
	public SideMenu(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	//Locators
	By optionAdmin = By.xpath("//span[text()='Admin']");
	By optionPIM = By.xpath("//span[text()='PIM']");
	By optionLeave = By.xpath("//span[text()='Leave']");
	By optionRecruitment = By.xpath("//span[text()='Recruitment']");
	By optionMyInfo = By.xpath("//span[text()='My Info']");
	By dashboardTitle = By.xpath("//h6[text()='Dashboard']");
	
	//Action
	public void clickOptionAdmin() {
		driver.findElement(optionAdmin).click();
	}
	public void clickOptionPIM() {
		driver.findElement(optionPIM).click();
	}
	public void clickOptionLeave() {
		driver.findElement(optionLeave).click();
	}
	public void clickOptionRecruitment() {
		driver.findElement(optionRecruitment).click();
	}
	public void clickOptionMyInfo() {
		driver.findElement(optionMyInfo).click();
	}

	public void performSideMenu(
			boolean isOptionAdmin,
			boolean isOptionPIM,
			boolean isOptionLeave,
			boolean isOptionRecruitment,
			boolean isOptionMyInfo) {
		
		WaitUtils waitUtils = new WaitUtils(driver);
		waitUtils.waitForVisibility(dashboardTitle);
		if(isOptionAdmin) {
			clickOptionAdmin();
		}
		if(isOptionPIM) {
			clickOptionPIM();
		}
		if(isOptionLeave) {
			clickOptionLeave();
		}
		if(isOptionRecruitment) {
			clickOptionRecruitment();
		}
		if(isOptionMyInfo) {
			clickOptionMyInfo();
		}
	}
}
