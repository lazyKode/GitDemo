package alankar.framework.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import alankar.framework.TestComponents.BaseTest;
import alankar.framework.TestComponents.Retry;
import alankar.framework.pageObjects.CartPage;
import alankar.framework.pageObjects.CheckoutPage;
import alankar.framework.pageObjects.ConfirmationPage;
import alankar.framework.pageObjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest{

	@Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {
		
		landingPage.loginApplication("hackingnutss@gmail.com", "ffPayal@93");
		Assert.assertEquals("Incorrect email password.", landingPage.getErrorMessage());
		}//submitOrder
	
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		ProductCatalogue productCatalogue = landingPage.loginApplication("hackingnuts5@gmail.com", "Payal@93");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);		
	}//ProductErrorValidation



};
