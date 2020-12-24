package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;
//Comment
public class ContactsPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage; 
	ContactsPage contactsPage;
	TestUtil testUtil;
	String sheetName="Contacts"; 
	
	public ContactsPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage=new LoginPage();
		testUtil=new TestUtil();
		contactsPage = new ContactsPage();
		homePage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToFrame();
		contactsPage=homePage.clickOnContactsLink();
	}
	
	@DataProvider
	public Object[][] getContactTestData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=1)
	public void verifyContactsLabelTest() {
		Assert.assertTrue(contactsPage.verifyContactsLabel(),"Contacts label is missing on the page");
	}
	
	@Test(priority=2)
	public void selectContactsTest() {
		contactsPage.selectContactsByName("Aarthy Arvind");
	}
	
	@Test(priority=3)
	public void selectMultipleContacts() {
		contactsPage.selectContactsByName("Aarthy Arvind");
		contactsPage.selectContactsByName("AA1 LST1");
	}
	
	@Test(priority=4, dataProvider="getContactTestData")
	public void validateCreateNewContact(String title,String firstName,String lastName,String company) {
		homePage.clickOnNewContactLink();
		contactsPage.createNewContact(title, firstName, lastName, company);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
