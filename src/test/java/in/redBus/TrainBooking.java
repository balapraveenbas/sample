package in.redBus;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TrainBooking {
	@DataProvider(name = "TestData")
	public Object[][] data() {
		return new Object[][] {{"Trichy", 123}};
	}
	@DataProvider(name = "TestData2")
	public Object[][] data1() {
		return new Object[][] {{"Trichy",true}};
	}
	@DataProvider(name = "TestData3")
	public Object[][] data2() {
		return new Object[][] {{"Trichy",12.1230}};
	}
	

//	HardAssert and SoftAssert
	public static WebDriver driver;
	
	@BeforeClass
	public static void applicationLaunch(){
		WebDriverManager.edgedriver().setup();
		EdgeOptions options = new EdgeOptions();
		options.addArguments("disable-notifications");
		options.addArguments("disable-popups");
		options.addArguments("start-maximized");
		driver = new EdgeDriver(options);
		driver.get("https://www.redbus.in");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	@AfterClass
	public static void browserClose() {
		//driver.quit();
	}
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("before method");
	}
	
	
	@AfterMethod
	public void afterMethod() {
		System.out.println("after method");
	}
	
	@Test(priority = 0)
	public void send_Value_In_From_Input_Box(){
		String fromPlace = "Chennai";
		WebElement from = driver.findElement(By.xpath("//label[text()='From']/preceding-sibling::input"));
		from.sendKeys(fromPlace);
		WebElement frompl = driver.findElement(By.xpath("//text[text()='Chennai']/parent::div[contains(@class,'grvhsy')]"));
		frompl.click();
	}
	
	
	@Test(priority = 1, dependsOnMethods = {"send_Value_In_From_Input_Box"}, dataProvider = "TestData")
	public void send_Value_In_To_Input_Box(String toplac, int id){
		WebElement to = driver.findElement(By.id("dest"));
		to.sendKeys(toplac);
		WebElement topl = driver.findElement(By.xpath("//text[text()='"+toplac+"']/parent::div[contains(@class,'grvhsy')]"));
		topl.click();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals("trichy", toplac, "Value mistmatch at To Place");
		soft.assertTrue(false, "\"Value mistmatch at To Place\"");
		System.out.println("testing assertion");
		System.out.println(id);
	}
	
	@Test(priority = 2, dependsOnMethods =  "send_Value_In_To_Input_Box")
	public void select_A_Date_From_DropDown(){
		WebElement date = driver.findElement(By.xpath("//span[text()='15']/preceding::span[text()='10']"));
		date.click();
	}
	
	@Test(priority = 3, dependsOnMethods =  "select_A_Date_From_DropDown")
	public void click_On_Search_Button(){
		WebElement searchBtn = driver.findElement(By.xpath("//button[contains(text(),'SEARCH')]"));
		searchBtn.click();
	}
	@Test(priority = 4)
	public void validate_Buses_Displayed() {
		List<WebElement> buses = driver.findElements(By.xpath("//div[contains(@class,'travels')]"));
		List<WebElement> dpTime = driver.findElements(By.xpath("//div[contains(@class,'dp-time')]"));
		List<WebElement> bpTime = driver.findElements(By.xpath("//div[contains(@class,'dp-time')]"));
		List<WebElement> fares = driver.findElements(By.xpath("//span[contains(@class,'f-19')]"));
		int size = buses.size();
		for (int i = 0; i < size; i++) {
			if(i == buses.size()-1) {
				JavascriptExecutor js = (JavascriptExecutor)driver;
				js.executeScript("arguments[0].scrollIntoView(true)", buses.get(i));
				buses = driver.findElements(By.xpath("//div[contains(@class,'travels')]"));
				dpTime = driver.findElements(By.xpath("//div[contains(@class,'dp-time')]"));
				bpTime = driver.findElements(By.xpath("//div[contains(@class,'dp-time')]"));
				fares = driver.findElements(By.xpath("//span[contains(@class,'f-19')]"));
				size=buses.size();
			}else {
				String bus = buses.get(i).getText();
				String dp = dpTime.get(i).getText();
				String bp = bpTime.get(i).getText();
				String fare = fares.get(i).getText();
				System.out.println("Bus Name :"+bus + " Departure Time :" + dp+" Arrival Time :"+bp+ " Bus Fare :"+fare);
			}
		}
	}
	
}
