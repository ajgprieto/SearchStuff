package search;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Search {
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();

	private Random rand = new Random();
	private Credentials cred;

	@Before
	public void setUp() throws Exception {

		driver = new FirefoxDriver();
		baseUrl = "http://www.bing.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		cred = new Credentials();
	}

	@Test
	public void testSearch(List<String> searchTerms) throws Exception {
		driver.get(baseUrl + "/");
		driver.findElement(By.id("id_s")).click();
		driver.findElement(By.cssSelector("span.id_link_text")).click();
		driver.findElement(By.id("i0116")).clear();
		driver.findElement(By.id("i0116")).sendKeys(cred.getUserName());
		Thread.sleep(500);
		driver.findElement(By.id("i0118")).clear();
		driver.findElement(By.id("i0118")).sendKeys(cred.getPassword());
		Thread.sleep(430);
		driver.findElement(By.id("idSIButton9")).click();
		driver.findElement(By.id("sb_form_q")).clear();

		for (String term : searchTerms) {
			driver.findElement(By.id("sb_form_q")).clear();
			driver.findElement(By.id("sb_form_q")).sendKeys(term);

			driver.findElement(By.id("sb_form_go")).submit();

			long sleepTime = rand.nextInt(9000);
			Thread.sleep(sleepTime);
		}

	}

	@After
	public void tearDown() throws Exception {
		Thread.sleep(5000);
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

}