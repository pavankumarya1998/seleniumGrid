package practice1;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Demo1 {
	String HubUrl = "http://192.168.0.142:4444";
	String url = "https://qauatoldui.slashrtc.in";
	WebDriver driver;

	public void setupGrid() {
		DesiredCapabilities ds = new DesiredCapabilities();
		ds.setCapability("browserName", "chrome");
		try {
			driver = new RemoteWebDriver(new URL(HubUrl), ds);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver.get(url);
	}

	public void pageTitleValidation() {
		String title = driver.getTitle();
		System.out.println("Page title :- " + title);
	}

	public static void main(String[] args) {
		Demo1 obj = new Demo1();
		obj.setupGrid();
		obj.pageTitleValidation();
	}

}
