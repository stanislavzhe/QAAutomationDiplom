package HotelsTest;

import HotelsTest.pages.NavigationMenu;
import HotelsTest.pages.RegisterNewHotelPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HotelsTest extends BaseTest {
    private RegisterNewHotelPage registerNewHotelPage;

    @BeforeMethod
    public void openNewHotelPage() {
        // open registerNewHotelPage
        NavigationMenu navigationMenu = new NavigationMenu(driver);
        registerNewHotelPage = navigationMenu
                .moveToArticleButton()
                .moveToNewButton()
                .clickOnHotelButton();
    }

    @Test(description = "Verify that user can open New Hotel page")
    public void newHotelPage() {
        //Verify that Register new Hotel page is displayed when user selects Article->New->Hotel
        String expectedPageTitle = "Register new Hotel";
        String actualPageTitle = registerNewHotelPage.getPageTitle();
        Assert.assertEquals(actualPageTitle, expectedPageTitle);

        String expectedPageUrl = "hotel";
        String actualPageUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualPageUrl.contains(expectedPageUrl));

        //Verify that Data section is displayed on Register new Hotel
        boolean actualDataFormDisplayed = registerNewHotelPage.isDataFormDisplayed();
        Assert.assertTrue(actualDataFormDisplayed);

        String expectedDataFromTitle = "Data";
        String actualDataFromTitle = registerNewHotelPage.getDataFormTitle();
        Assert.assertTrue(actualDataFromTitle.contains(expectedDataFromTitle));

        //Verify that save button is displayed on Register new Hotel
        boolean actualSaveButtonDisplayed = registerNewHotelPage.isSaveButtonDisplayed();
        Assert.assertTrue(actualSaveButtonDisplayed);
    }

    @Test(description = "Verify that user can edit Name field")
    public void nameField() {
        //Verify that Name field is displayed in Data section of Register new Hotel page
        boolean actualNameFieldDisplayed = registerNewHotelPage.isNameFieldDisplayed();
        Assert.assertTrue(actualNameFieldDisplayed);

        String expectedNameFiledTitle = "Name";
        String actualNameFiledTitle = registerNewHotelPage.getNameFiledTitle();
        Assert.assertTrue(actualNameFiledTitle.contains(expectedNameFiledTitle));

        //Verify that Name field is marked with asterisk
        String expectedNameAsterisk = "*";
        actualNameFiledTitle = registerNewHotelPage.getNameFiledTitle();
        Assert.assertTrue(actualNameFiledTitle.contains(expectedNameAsterisk));

        //Verify that Name field is editable
        String expectedNameField = "Name";
        registerNewHotelPage.clearAndTypeName(expectedNameField);
        String actualName = registerNewHotelPage.getNameFieldValue();
        Assert.assertEquals(actualName, expectedNameField);

        String secondPartOfName = " Last";
        expectedNameField = expectedNameField + secondPartOfName;
        registerNewHotelPage.typeName(secondPartOfName);
        actualName = registerNewHotelPage.getNameFieldValue();
        Assert.assertEquals(actualName, expectedNameField);

        expectedNameField = "Last Name";
        registerNewHotelPage.clearAndTypeName(expectedNameField);
        actualName = registerNewHotelPage.getNameFieldValue();
        Assert.assertEquals(actualName, expectedNameField);

        //Verify that Name field allows to input alphanumeric characters
        expectedNameField = "1Name 2Last";
        registerNewHotelPage.clearAndTypeName(expectedNameField);
        actualName = registerNewHotelPage.getNameFieldValue();
        Assert.assertEquals(actualName, expectedNameField);

        expectedNameField = "1Name2Last3";
        registerNewHotelPage.clearAndTypeName(expectedNameField);
        actualName = registerNewHotelPage.getNameFieldValue();
        Assert.assertEquals(actualName, expectedNameField);

        //Verify that it is not possible to save the empty Name field and a meaningful error message is displayed
        registerNewHotelPage.clearNameField();
        registerNewHotelPage.clickOnSaveButton();
        boolean actualNameErrorDisplayed = registerNewHotelPage.isNameErrorDisplayed();
        Assert.assertTrue(actualNameErrorDisplayed);

        String expectedNameErrorText = "Name: Validation Error: Value is required.";
        String actualNameErrorText = registerNewHotelPage.getNameErrorText();
        Assert.assertEquals(actualNameErrorText, expectedNameErrorText);

        //Verify that it is possible to save the valid name field
        expectedNameField = "First Name";
        registerNewHotelPage.clearAndTypeName(expectedNameField);
        registerNewHotelPage.clickOnSaveButton();
        actualNameErrorDisplayed = registerNewHotelPage.isNameErrorDisplayed();
        Assert.assertFalse(actualNameErrorDisplayed);

        String actualNameField = registerNewHotelPage.getNameFieldValue();
        Assert.assertEquals(actualNameField, expectedNameField);

    }

    @Test(description = "Verify that user can edit Global Rating field")
    public void globalRating() {
        //Verify that Global Rating field is displayed in Data section of Register new Hotel page
        String expectedGlobalRateTitle = "Global Rating";
        String actualGlobalRateTitle = registerNewHotelPage.getGlobalRateTitle();
        Assert.assertTrue(actualGlobalRateTitle.contains(expectedGlobalRateTitle));

        boolean actualGlobalRateDisplayed = registerNewHotelPage.isGlobalRateDisplayed();
        Assert.assertTrue(actualGlobalRateDisplayed);

        //Verify that Global Rating field allows to rating of the hotel (0-5 stars)
        int expectedStarsValue = 1;
        registerNewHotelPage.selectGlobalRateStars(expectedStarsValue);
        int actualStartsValue = Integer.parseInt(registerNewHotelPage.getGlobalRateValue());
        Assert.assertEquals(actualStartsValue, expectedStarsValue);

        expectedStarsValue = 0;
        registerNewHotelPage.selectGlobalRateStars(expectedStarsValue);
        actualStartsValue = Integer.parseInt(registerNewHotelPage.getGlobalRateValue());
        Assert.assertEquals(actualStartsValue, expectedStarsValue);

        expectedStarsValue = 5;
        registerNewHotelPage.selectGlobalRateStars(expectedStarsValue);
        actualStartsValue = Integer.parseInt(registerNewHotelPage.getGlobalRateValue());
        Assert.assertEquals(actualStartsValue, expectedStarsValue);

        //Verify that it is possible to save the Global Rating field
        expectedStarsValue = 0;
        registerNewHotelPage.selectGlobalRateStars(expectedStarsValue);
        registerNewHotelPage.clickOnSaveButton();
        actualStartsValue = Integer.parseInt(registerNewHotelPage.getGlobalRateValue());
        Assert.assertEquals(actualStartsValue, expectedStarsValue);

        //Verify that it is possible to save valid Global Rating field
        expectedStarsValue = 5;
        registerNewHotelPage.selectGlobalRateStars(expectedStarsValue);
        registerNewHotelPage.clickOnSaveButton();
        actualStartsValue = Integer.parseInt(registerNewHotelPage.getGlobalRateValue());
        Assert.assertEquals(actualStartsValue, expectedStarsValue);
    }

    @Test(description = "Verify that user can add Date of Construction of new hotel")
    public void dateOfConstruction() {
        //Verify that Date of Construction field is displayed in Data section of Register new Hotel page
        String expectedDateOfConstructionTitle = "Date of Construction";
        String actualDateOfConstructionTitle = registerNewHotelPage.getDateOfConstructionTitle();
        Assert.assertTrue(actualDateOfConstructionTitle.contains(expectedDateOfConstructionTitle));

        boolean actualDateOfConstructionInputDisplayed = registerNewHotelPage.isDateOfConstructionInputDisplayed();
        Assert.assertTrue(actualDateOfConstructionInputDisplayed);

        boolean actualDateOfConstructionCalendarIconDisplayed = registerNewHotelPage.isDateOfConstructionCalendarIconDisplayed();
        Assert.assertTrue(actualDateOfConstructionCalendarIconDisplayed);

        //Verify that Date of Construction field is marked with asterisk
        String expectedDateOfConstructionAsterisk = "*";
        actualDateOfConstructionTitle = registerNewHotelPage.getDateOfConstructionTitle();
        Assert.assertTrue(actualDateOfConstructionTitle.contains(expectedDateOfConstructionAsterisk));

        //Verify that Date of Construction is editable
        registerNewHotelPage.typeDateOfConstruction("10.10");
        System.out.println(registerNewHotelPage.getDateOfConstructionValue());
        registerNewHotelPage.typeDateOfConstruction(".2018");
        System.out.println(registerNewHotelPage.getDateOfConstructionValue());
        registerNewHotelPage.clearAndTypeDateOfConstruction("01.01.1900");
        System.out.println(registerNewHotelPage.getDateOfConstructionValue());

        //Verify Date of Construction field allows to input in date format
        registerNewHotelPage.clearAndTypeDateOfConstruction("12.12.17");
        System.out.println(registerNewHotelPage.getDateOfConstructionValue());
        registerNewHotelPage.clearAndTypeDateOfConstruction("12.12.2017");
        System.out.println(registerNewHotelPage.getDateOfConstructionValue());

        //Verify that it is not possible to save incorrect date format value Date of Construction field
        // and a meaningful error message is displayed
        registerNewHotelPage.clearAndTypeDateOfConstruction("1900.10.10");
        registerNewHotelPage.clickOnSaveButton();
        System.out.println(registerNewHotelPage.isDateOfConstructionErrorDisplayed());// true
        System.out.println(registerNewHotelPage.getDateOfConstructionErrorText());
        // Date of Construction: '1900.10.10' could not be understood as a date. Example: 18.10.18

        //Verify that it is not possible to save the empty Date of Construction field and a meaningful error message is displayed
        registerNewHotelPage.clearDateOfConstruction();
        registerNewHotelPage.clickOnSaveButton();
        System.out.println(registerNewHotelPage.isDateOfConstructionErrorDisplayed());// true
        System.out.println(registerNewHotelPage.getDateOfConstructionErrorText());
        // Date of Construction: Validation Error: Value is required.

        //Verify that it is possible to save valid Date of Construction field
        registerNewHotelPage.clearAndTypeDateOfConstruction("10.10.2000");
        registerNewHotelPage.clickOnSaveButton();
        System.out.println(registerNewHotelPage.isDateOfConstructionErrorDisplayed());// false
        System.out.println(registerNewHotelPage.getDateOfConstructionValue());// 10.10.00
    }

    @Test(description = "Verify that user can add Country of new hotel")
    public void countryOfHotel() throws InterruptedException {
        //Verify that Country field is displayed in Data section of Register new Hotel page
        System.out.println(registerNewHotelPage.isCountrySelectDisplayed());// true
        System.out.println(registerNewHotelPage.getCountryTitle());// Country:*

        //Verify that Country fields is marked with asterisk
        System.out.println(registerNewHotelPage.getCountryTitle().contains("*"));// true

        //Verify that Country field is editable
        registerNewHotelPage.clickOnCountrySelect();
        registerNewHotelPage.setCountrySelect("USA");
        System.out.println(registerNewHotelPage.getCountrySelectValue());
        registerNewHotelPage.clickOnCountrySelect();
        Thread.sleep(500);
        registerNewHotelPage.setCountrySelect("Ukraine");
        System.out.println(registerNewHotelPage.getCountrySelectValue());

        //Verify that it is not possible to save the empty (with default value “Select me’) Country field
        //And a meaningful error is displayed
        registerNewHotelPage.clickOnCountrySelect();
        registerNewHotelPage.setCountrySelect("Select one");
        registerNewHotelPage.clickOnSaveButton();
        System.out.println(registerNewHotelPage.isCountryErrorDisplayed());// true
        System.out.println(registerNewHotelPage.getCountryErrorText());// Country: Validation Error: Value is required.

        //Verify that it is possible to save the valid Country field
        registerNewHotelPage.clickOnCountrySelect();
        registerNewHotelPage.setCountrySelect("Ukraine");
        registerNewHotelPage.clickOnSaveButton();
        System.out.println(registerNewHotelPage.isCountryErrorDisplayed());// false
        System.out.println(registerNewHotelPage.getCountrySelectValue());
    }
}
