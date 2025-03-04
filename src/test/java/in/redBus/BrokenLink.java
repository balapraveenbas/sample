package in.redBus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrokenLink {
	private BrokenLink() {}
	
	static BrokenLink br = null;
	
	public static BrokenLink justInstance() {
		if(br==null) {
			br = new BrokenLink();
			
		}
		return br;
	}

	public static void main(String[] args) throws MalformedURLException, IOException {
		File f = new File(".\\src\\test\\resources\\File.property");
		FileInputStream f1 = new FileInputStream(f);
		Properties prop = new Properties();
		prop.load(f1);
		String url = prop.getProperty("url");
		
		FileOutputStream f2 = new FileOutputStream(f);
		prop.setProperty("username", "newName");
		prop.setProperty("n", "n");
		prop.save(f2, "updated username");
		
		WebDriverManager.edgedriver().setup();
		WebDriver driver = new EdgeDriver();
		Actions act = new Actions(driver);
		driver.get(url);
		List<WebElement> links = driver.findElements(By.tagName("link"));
		Iterator<WebElement> linkIterator = links.iterator();
		while (linkIterator.hasNext()) {
				WebElement link = linkIterator.next();
				String href = link.getAttribute("href");
				if(href==null||href.isEmpty()) {
					System.out.println("Given link is empty or null");
				}
//				else if(!href.startsWith("https://www.flipkart.com")){
//					System.out.println("Given link is not related to our application " + href);
//				}
				else {
					HttpURLConnection http = (HttpURLConnection) (new URL(href)).openConnection();
					http.setRequestMethod("HEAD");
					http.connect();
					int responseCode = http.getResponseCode();
					if(responseCode==200) {
						System.out.println("Not a Broken Link "+ href);
					}else {
						System.out.println("Broken Link "+ href);
					}
				}
		}
	}
}
