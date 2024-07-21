package chromedevtools;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

public class SetGeoLocation {

	public static void main(String[] args) throws InterruptedException {

		ChromeDriver driver = new ChromeDriver();
		DevTools devTools = driver.getDevTools();
		devTools.createSession();
		
		Map<String , Object> coordinates = new HashMap<String,Object>();
		coordinates.put("latitude", 50);
		coordinates.put("longitude", 3);
		coordinates.put("accuracy", 1);
		driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);
		
		driver.get("http://google.com");
		Thread.sleep(3000);
		driver.findElement(By.name("q")).sendKeys("netflix",Keys.ENTER);
		
		driver.findElements(By.cssSelector(".LC20lb")).get(0).click();
		
		String title = driver.findElement(By.cssSelector("h1[data-uia=nmhp-card-hero-text-title]")).getText();
		System.out.println(title);
	}

}
