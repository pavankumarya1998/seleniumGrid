package practice1;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Demo3 {
	public static void main(String[] args) throws InterruptedException {
		String hubUrl = "http://localhost:4444/wd/hub";

		List<String> urls = Arrays.asList("https://www.selenium.dev/", "https://www.lifewire.com/",
				"https://www.facebook.com/", "https://login.microsoftonline.com/");

		List<String> nodes = Arrays.asList("http://192.168.0.142:5555", "http://192.168.0.142:6666",
				"http://192.168.0.142:7777", "http://192.168.0.142:8888");

		ExecutorService executor = Executors.newFixedThreadPool(urls.size());

		for (int i = 0; i < urls.size(); i++) {
			final int index = i;
			executor.submit(() -> {
				try {
					openUrlOnNode(urls.get(index), hubUrl);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			});
		}

		executor.shutdown();
		while (!executor.isTerminated()) {
			Thread.sleep(3000);
		}

		System.out.println("All URLs opened successfully.");
	}

	public static void openUrlOnNode(String url, String hubUrl) throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		ChromeOptions options = new ChromeOptions();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		WebDriver driver = new RemoteWebDriver(new URL(hubUrl), capabilities);
		driver.get(url);
		System.out.println("Opened " + url);
		driver.quit();
	}
}

