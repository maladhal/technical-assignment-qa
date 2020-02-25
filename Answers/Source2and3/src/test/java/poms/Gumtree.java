package poms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Gumtree extends AllPages{
	public static WebElement getGumtreeLogo(WebDriver driver){
		//this step might fail as gumtree thinks its a bot.
	    element = driver.findElement(By.className("gumtree-logo-svg"));
	    return element;
	}
}
