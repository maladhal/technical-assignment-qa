package poms;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Google extends AllPages {

	public static WebElement getSearchBox(WebDriver driver) {
		element = driver.findElement(By.cssSelector(".gLFyf"));
		return element;
	}
	public static WebElement getSearchButton(WebDriver driver) {
		element = driver.findElement(By.cssSelector(".gNO89b"));
		return element;
	}
//	public static List<WebElement> getLinksByText(WebDriver driver, String text) {
//		elements = driver.findElements(By.("cite"));
//		return elements;
//	}
	public static List<WebElement> getAllSearchLinks(WebDriver driver) {
		elements = driver.findElements(By.tagName("cite"));
		return elements;
	}
}
