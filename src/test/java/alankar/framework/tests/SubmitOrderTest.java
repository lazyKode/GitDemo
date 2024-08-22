package alankar.framework.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import alankar.framework.TestComponents.BaseTest;
import alankar.framework.pageObjects.CartPage;
import alankar.framework.pageObjects.CheckoutPage;
import alankar.framework.pageObjects.ConfirmationPage;
import alankar.framework.pageObjects.LandingPage;
import alankar.framework.pageObjects.OrderPage;
import alankar.framework.pageObjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = "Purchase")
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		driver.findElement(By.cssSelector(".action__submit")).click();
		String confirmMessage = confirmationPage.getConfirmationMessahe();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}// submitOrder

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
		// "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("alankarsharma44@gmail.com", "Payal@93");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}// OrderHistoryTest

	
	

	
	@DataProvider
	public Object[][] getData() throws IOException {

		List <HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\alankar\\framework\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}// getData

};





/*
 * HashMap<String, String> map = new HashMap<String, String>(); map.put("email",
 * "hackingnuts@gmail.com"); map.put("password", "Payal@93"); map.put("product",
 * "ZARA COAT 3");
 * 
 * HashMap<String, String> map1 = new HashMap<String, String>();
 * map1.put("email", "hackingnuts5@gmail.com"); map1.put("password",
 * "Payal@93"); map1.put("product", "ADIDAS ORIGINAL");
 */
