package alankar.framework.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import alankar.framework.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {
	
	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}// constructor
	
	@FindBy(css=".hero-primary")
	WebElement confirmationMessage;
	
	public String getConfirmationMessahe() {
		
		return confirmationMessage.getText();
		
	}//verifyConfirmationMessahe
	
	

}
