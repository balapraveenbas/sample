package in.redBus;

import org.openqa.selenium.By.ByCssSelector;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test {

	public static void main(String[] args) throws MalformedURLException, IOException {
		NewClass ne = NewClass.getInstance();
		System.out.println(System.identityHashCode(ne));
		File f = new File(".\\src\\test\\resources\\FileName.properties");
		FileInputStream f1 = new FileInputStream(f);
		Properties prop = new Properties();
		prop.load(f1);
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		String env = prop.getProperty("env");
		System.out.println(url);
		System.out.println(username);
		System.out.println(password);
		System.out.println(env);
		FileOutputStream f2 = new FileOutputStream(f);
		prop.setProperty("env", "uat");
		prop.setProperty("newvalue", "value");
		prop.save(f2, "Updated env");
		
		WebDriverManager.edgedriver().setup();
		DesiredCapabilities des = new DesiredCapabilities();
		EdgeOptions options = new EdgeOptions(); 
		options.addArguments("disable-popups");
		options.addArguments("disable-notifications");
		options.addArguments("start-maximized");
//		options.addArguments("--headless");
		WebDriver driver = new EdgeDriver(options);
		driver.get(url);
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//		List<WebElement> links = driver.findElements(By.tagName("link"));
//		Iterator<WebElement> linksIterator = links.iterator();
//		while(linksIterator.hasNext()) {
//			WebElement link = linksIterator.next();
//			String href = link.getAttribute("href");
//			if(href==null || href.isEmpty()) {
//				System.out.println("Given link is empty");
//			}
//			else if(!href.startsWith("https://www.flipkart.com/")) {
//				System.out.println("Given link is not related to our application  " + href);
//			}
//			else {
//				try {
//				HttpURLConnection http = ((HttpURLConnection) (new URL(href)).openConnection());
//				http.setRequestMethod("HEAD");
//				http.connect();
//				int responseCode = http.getResponseCode();
//				if(responseCode==200) {
//					System.out.println("Not a Broken Link "+href);
//				}
//				else {
//					System.out.println("Broken Link "+href);
//				}
//				}
//				catch (MalformedURLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		TakesScreenshot ts = (TakesScreenshot)driver;
//		TypeCasting
	}
}
