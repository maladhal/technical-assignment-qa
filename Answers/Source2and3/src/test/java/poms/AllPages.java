package poms;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public abstract class AllPages {
  
  protected static WebElement element = null;
  protected static List<WebElement> elements = null;
  
  public static void openBaseUrl(WebDriver driver, String baseURL){
      driver.get(baseURL);
  } 
}

