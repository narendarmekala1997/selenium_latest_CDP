package chromedevtools;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v124.fetch.Fetch;
import org.openqa.selenium.devtools.v124.network.model.ErrorReason;
import org.openqa.selenium.devtools.v125.fetch.model.RequestPattern;

public class NetworkFailedRequest {

	public static void main(String[] args) {

		ChromeDriver driver = new ChromeDriver();

		DevTools devTools = driver.getDevTools();
		devTools.createSession();
		
		Optional<List<RequestPattern>> patterns = Optional.of(Arrays.asList(new RequestPattern(Optional.of("*GetBook*"),Optional.empty(),Optional.empty())));
		devTools.send(org.openqa.selenium.devtools.v125.fetch.Fetch.enable(patterns, Optional.empty()));
		
		devTools.addListener(Fetch.requestPaused(), request ->
		{
			devTools.send(Fetch.failRequest(request.getRequestId(), ErrorReason.FAILED));
		});
		
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.cssSelector("button[routerlink*='library']")).click();
	}

}
