package HotelsTest;

import HotelsTest.pages.NavigationMenu;
import HotelsTest.pages.RegisterNewHotelPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HotelsTest extends BaseTest {
    private RegisterNewHotelPage registerNewHotelPage;
    private final String expectedAsterisk = "*";

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

        //Verify that Name field is marked with expectedAsterisk
        actualNameFiledTitle = registerNewHotelPage.getNameFiledTitle();
        Assert.assertTrue(actualNameFiledTitle.contains(expectedAsterisk));

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
        expectedNameField = "1Name 2Last 3";
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

        //Verify that Date of Construction field is marked with expectedAsterisk
        actualDateOfConstructionTitle = registerNewHotelPage.getDateOfConstructionTitle();
        Assert.assertTrue(actualDateOfConstructionTitle.contains(expectedAsterisk));

        //Verify that Date of Construction is editable
        String expectedDateOfConstruction = "10.10";
        registerNewHotelPage.typeDateOfConstruction(expectedDateOfConstruction);
        String actualDateOfConstruction = registerNewHotelPage.getDateOfConstructionValue();
        Assert.assertEquals(actualDateOfConstruction, expectedDateOfConstruction);

        String dateOfConstruction = ".2018";
        expectedDateOfConstruction = "10.10.2018";
        registerNewHotelPage.typeDateOfConstruction(dateOfConstruction);
        actualDateOfConstruction = registerNewHotelPage.getDateOfConstructionValue();
        Assert.assertEquals(actualDateOfConstruction, expectedDateOfConstruction);

        expectedDateOfConstruction = "01.01.1900";
        registerNewHotelPage.clearAndTypeDateOfConstruction(expectedDateOfConstruction);
        actualDateOfConstruction = registerNewHotelPage.getDateOfConstructionValue();
        Assert.assertEquals(actualDateOfConstruction, expectedDateOfConstruction);

        //Verify Date of Construction field allows to input in date format
        expectedDateOfConstruction = "12.12.17";
        registerNewHotelPage.clearAndTypeDateOfConstruction(expectedDateOfConstruction);
        actualDateOfConstruction = registerNewHotelPage.getDateOfConstructionValue();
        Assert.assertEquals(actualDateOfConstruction, expectedDateOfConstruction);

        expectedDateOfConstruction = "12.12.2017";
        registerNewHotelPage.clearAndTypeDateOfConstruction(expectedDateOfConstruction);
        actualDateOfConstruction = registerNewHotelPage.getDateOfConstructionValue();
        Assert.assertEquals(actualDateOfConstruction, expectedDateOfConstruction);

        //Verify that it is not possible to save incorrect date format value Date of Construction field
        // and a meaningful error message is displayed
        dateOfConstruction = "1900.10.10";
        registerNewHotelPage.clearAndTypeDateOfConstruction(dateOfConstruction);
        registerNewHotelPage.clickOnSaveButton();
        boolean actualDateOfConstructionErrorDisplayed = registerNewHotelPage.isDateOfConstructionErrorDisplayed();
        Assert.assertTrue(actualDateOfConstructionErrorDisplayed);

        String expectedDateOfConstructionErrorText =
                "Date of Construction: '1900.10.10' could not be understood as a date. Example";
        String actualDateOfConstructionErrorText = registerNewHotelPage.getDateOfConstructionErrorText();
        Assert.assertTrue(actualDateOfConstructionErrorText.contains(expectedDateOfConstructionErrorText));

        //Verify that it is not possible to save the empty Date of Construction field and a meaningful error message is displayed
        registerNewHotelPage.clearDateOfConstruction();
        registerNewHotelPage.clickOnSaveButton();
        actualDateOfConstructionErrorDisplayed = registerNewHotelPage.isDateOfConstructionErrorDisplayed();
        Assert.assertTrue(actualDateOfConstructionErrorDisplayed);
        expectedDateOfConstructionErrorText =
                "Date of Construction: Validation Error: Value is required.";
        actualDateOfConstructionErrorText = registerNewHotelPage.getDateOfConstructionErrorText();
        Assert.assertEquals(actualDateOfConstructionErrorText, expectedDateOfConstructionErrorText);

        //Verify that it is possible to save valid Date of Construction field
        dateOfConstruction = "10.10.2000";
        expectedDateOfConstruction = "10.10.00";
        registerNewHotelPage.clearAndTypeDateOfConstruction(dateOfConstruction);
        registerNewHotelPage.clickOnSaveButton();

        actualDateOfConstructionErrorDisplayed = registerNewHotelPage.isDateOfConstructionErrorDisplayed();
        Assert.assertFalse(actualDateOfConstructionErrorDisplayed);

        actualDateOfConstruction = registerNewHotelPage.getDateOfConstructionValue();
        Assert.assertEquals(actualDateOfConstruction, expectedDateOfConstruction);
    }

    @Test(description = "Verify that user can add Country of new hotel")
    public void countryOfHotel() {
        //Verify that Country field is displayed in Data section of Register new Hotel page
        boolean actualCountrySelectDisplayed = registerNewHotelPage.isCountrySelectDisplayed();
        Assert.assertTrue(actualCountrySelectDisplayed);

        String expectedCountryTitle = "Country";
        String actualCountryTitle = registerNewHotelPage.getCountryTitle();
        Assert.assertTrue(actualCountryTitle.contains(expectedCountryTitle));

        //Verify that Country fields is marked with expectedAsterisk
        actualCountryTitle = registerNewHotelPage.getCountryTitle();
        Assert.assertTrue(actualCountryTitle.contains(expectedAsterisk));

        //Verify that Country field is editable
        String expectedCountrySelect = "USA";
        registerNewHotelPage.clickOnCountrySelect();
        registerNewHotelPage.setCountrySelect(expectedCountrySelect);
        String actualCountrySelect = registerNewHotelPage.getCountrySelectValue();
        Assert.assertEquals(actualCountrySelect, expectedCountrySelect);

        expectedCountrySelect = "Ukraine";
        registerNewHotelPage.clickOnCountrySelect();
        registerNewHotelPage.setCountrySelect(expectedCountrySelect);
        actualCountrySelect = registerNewHotelPage.getCountrySelectValue();
        Assert.assertEquals(actualCountrySelect, expectedCountrySelect);

        //Verify that it is not possible to save the empty (with default value “Select me’) Country field and a meaningful error is displayed
        expectedCountrySelect = "Select one";
        registerNewHotelPage.clickOnCountrySelect();
        registerNewHotelPage.setCountrySelect(expectedCountrySelect);
        registerNewHotelPage.clickOnSaveButton();

        boolean actualCountryErrorDisplayed = registerNewHotelPage.isCountryErrorDisplayed();
        Assert.assertTrue(actualCountryErrorDisplayed);

        String expectedCountryErrorText = "Country: Validation Error: Value is required.";
        String actualCountryErrorText = registerNewHotelPage.getCountryErrorText();
        Assert.assertEquals(actualCountryErrorText, expectedCountryErrorText);

        //Verify that it is possible to save the valid Country field
        expectedCountrySelect = "Ukraine";
        registerNewHotelPage.clickOnCountrySelect();
        registerNewHotelPage.setCountrySelect(expectedCountrySelect);
        registerNewHotelPage.clickOnSaveButton();

        actualCountryErrorDisplayed = registerNewHotelPage.isCountryErrorDisplayed();
        Assert.assertFalse(actualCountryErrorDisplayed);

        actualCountrySelect = registerNewHotelPage.getCountrySelectValue();
        Assert.assertEquals(actualCountrySelect, expectedCountrySelect);
    }

    @Test(description = "Verify that user can add City of new hotel")
    public void cityOfHotel() {
        //Verify that City field is displayed in Data section of Register new Hotel page
        boolean actualCitySelectDisplayed = registerNewHotelPage.isCitySelectDisplayed();
        Assert.assertTrue(actualCitySelectDisplayed);

        String expectedCityTitle = "City";
        String actualCityTitle = registerNewHotelPage.getCityTitle();
        Assert.assertTrue(actualCityTitle.contains(expectedCityTitle));

        //Verify that City field is marked with expectedAsterisk
        actualCityTitle = registerNewHotelPage.getCountryTitle();
        Assert.assertTrue(actualCityTitle.contains(expectedAsterisk));

        //Verify that City field is editable
        String expectedCountrySelect = "Ukraine";
        String expectedCitySelect = "Kyiv";
        registerNewHotelPage.clickOnCountrySelect();
        registerNewHotelPage.setCountrySelect(expectedCountrySelect);
        registerNewHotelPage.clickOnCitySelect();
        registerNewHotelPage.setCitySelect(expectedCitySelect);
        String actualCitySelect = registerNewHotelPage.getCitySelectValue();
        Assert.assertEquals(actualCitySelect, expectedCitySelect);

        expectedCountrySelect = "Ukraine";
        expectedCitySelect = "Lviv";
        registerNewHotelPage.clickOnCountrySelect();
        registerNewHotelPage.setCountrySelect(expectedCountrySelect);
        registerNewHotelPage.clickOnCitySelect();
        registerNewHotelPage.setCitySelect(expectedCitySelect);
        actualCitySelect = registerNewHotelPage.getCitySelectValue();
        Assert.assertEquals(actualCitySelect, expectedCitySelect);

        //Verify that it is not possible to save the empty (with default value “Select me”) City field and a meaningful error message is displayed
        expectedCountrySelect = "Ukraine";
        expectedCitySelect = "Select one";
        registerNewHotelPage.clickOnCountrySelect();

        registerNewHotelPage.setCountrySelect(expectedCountrySelect);
        registerNewHotelPage.clickOnCitySelect();
        registerNewHotelPage.setCitySelect(expectedCitySelect);
        registerNewHotelPage.clickOnSaveButton();

        boolean actualCityErrorDisplayed = registerNewHotelPage.isCityErrorDisplayed();
        Assert.assertTrue(actualCityErrorDisplayed);

        String expectedCityErrorText = "City: Validation Error: Value is required.";
        String actualCityErrorText = registerNewHotelPage.getCityErrorText();
        Assert.assertEquals(actualCityErrorText, expectedCityErrorText);

        //Verify that it is possible to save the valid City field
        expectedCountrySelect = "Ukraine";
        expectedCitySelect = "Lviv";
        registerNewHotelPage.clickOnCountrySelect();
        registerNewHotelPage.setCountrySelect(expectedCountrySelect);
        registerNewHotelPage.clickOnCitySelect();
        registerNewHotelPage.setCitySelect(expectedCitySelect);
        registerNewHotelPage.clickOnSaveButton();

        actualCityErrorDisplayed = registerNewHotelPage.isCityErrorDisplayed();
        Assert.assertFalse(actualCityErrorDisplayed);

        actualCitySelect = registerNewHotelPage.getCitySelectValue();
        Assert.assertEquals(actualCitySelect, expectedCitySelect);
    }

    @Test(description = "Verify that user can add Short Description of new hotel ")
    public void shortDescription() {
        //Verify that Short Description field is displayed in Data section  of Register new Hotel
        boolean actualShortDescriptionDisplayed = registerNewHotelPage.isShortDescriptionDisplayed();
        Assert.assertTrue(actualShortDescriptionDisplayed);

        String expectedShortDescriptionTitle = "Short Description";
        String actualShortDescriptionTitle = registerNewHotelPage.getShortDescriptionTitle();
        Assert.assertTrue(actualShortDescriptionTitle.contains(expectedShortDescriptionTitle));

        //Verify that Short Description field is marked with expectedAsterisk
        actualShortDescriptionTitle = registerNewHotelPage.getShortDescriptionTitle();
        Assert.assertTrue(actualShortDescriptionTitle.contains(expectedAsterisk));

        //Verify that Short Description field is editable
        String expectedShortDescription = "Short";
        registerNewHotelPage.clearAndTypeShortDescription(expectedShortDescription);
        String actualShortDescription = registerNewHotelPage.getShortDescriptionValue();
        Assert.assertEquals(actualShortDescription, expectedShortDescription);

        String secondPartOfDescription = " Description";
        expectedShortDescription = expectedShortDescription + secondPartOfDescription;
        registerNewHotelPage.typeShortDescription(secondPartOfDescription);
        actualShortDescription = registerNewHotelPage.getShortDescriptionValue();
        Assert.assertEquals(actualShortDescription, expectedShortDescription);

        expectedShortDescription = "Short Description";
        registerNewHotelPage.clearAndTypeShortDescription(expectedShortDescription);
        actualShortDescription = registerNewHotelPage.getShortDescriptionValue();
        Assert.assertEquals(actualShortDescription, expectedShortDescription);

        //Verify that Short Description field allows to input alphanumeric characters
        expectedShortDescription = "1Short 2Description 3";
        registerNewHotelPage.clearAndTypeShortDescription(expectedShortDescription);
        actualShortDescription = registerNewHotelPage.getShortDescriptionValue();
        Assert.assertEquals(actualShortDescription, expectedShortDescription);

        expectedShortDescription = "1Short2Description3";
        registerNewHotelPage.clearAndTypeShortDescription(expectedShortDescription);
        actualShortDescription = registerNewHotelPage.getShortDescriptionValue();
        Assert.assertEquals(actualShortDescription, expectedShortDescription);

        //Verify that it is not possible to save the empty Short Description field and a meaningful error message is displayed
        registerNewHotelPage.clearShortDescription();
        registerNewHotelPage.clickOnSaveButton();
        boolean actualShortDescriptionErrorDisplayed = registerNewHotelPage.isShortDescriptionErrorDisplayed();
        Assert.assertTrue(actualShortDescriptionErrorDisplayed);

        String expectedShortDescriptionErrorText = "Short Description: Validation Error: Value is required.";
        String actualShortDescriptionErrorText = registerNewHotelPage.getShortDescriptionErrorText();
        Assert.assertEquals(actualShortDescriptionErrorText, expectedShortDescriptionErrorText);

        //Verify that it is possible to save the valid Short Description field
        expectedShortDescription = "Short Description";
        registerNewHotelPage.clearAndTypeShortDescription(expectedShortDescription);
        registerNewHotelPage.clickOnSaveButton();
        actualShortDescriptionErrorDisplayed = registerNewHotelPage.isShortDescriptionErrorDisplayed();
        Assert.assertFalse(actualShortDescriptionErrorDisplayed);

        actualShortDescription = registerNewHotelPage.getShortDescriptionValue();
        Assert.assertEquals(actualShortDescription, expectedShortDescription);
    }

    @Test(description = "Verify that user can add Description of new hotel")
    public void description(){
        //Verify that  Description field is displayed in Data section  of Register new Hotel
        //Verify that Description field is marked with asterisk
        //Verify that Description field is editable
        //Verify that Description field allows to input alphanumeric characters
        //Verify that it is not possible to save the empty Description field and a meaningful error message is displayed
        //Verify that it is possible to save the valid Description field
    }

    @Test(description = "Verify that user can add Notes of new hotel")
    public void notes(){
        //Verify that  Notes field is displayed in Data section  of Register new Hotel
        //Verify that Notes field is editable
        //Verify that Notes field allows to input alphanumeric characters
        //Verify that it is possible to save the empty Notes field
        //Verify that it is possible to save the valid Notes field
    }
}
