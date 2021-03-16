package orion.users;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * 
 */
public class OriondevTest {
  private static WebDriver driver;
  JavascriptExecutor js;

  @BeforeAll
  public static void setUp() {
    OriondevTest.driver = new FirefoxDriver();
  }

  @AfterAll
    public static void tearDown() {
      OriondevTest.driver.quit();
    }
    @Test
    public void oriondev() {
      OriondevTest.driver.get("https://orion-services.dev/");
      OriondevTest.driver.manage().window().setSize(new Dimension(1366, 728));
      OriondevTest.driver.findElement(By.linkText("About the project")).click();
      OriondevTest.driver.findElement(By.linkText("Game service")).click();
    }
  }