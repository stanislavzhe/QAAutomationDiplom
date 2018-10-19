package HotelsTest.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Objects;


public class RegisterNewHotelPage {
    private WebDriver driver;

    @FindBy(id = "title")
    private WebElement pageTitle;

    @FindBy(id = "add_hotel")
    private WebElement dataForm;

    @FindBy(id = "add_hotel:j_idt40_header")
    private WebElement dataFormTitle;

    @FindBy(id = "add_hotel:j_idt62")
    private WebElement saveButton;

    @FindBy(id = "add_hotel:j_idt42")
    private WebElement nameFieldTitle;

    @FindBy(id = "add_hotel:name")
    private WebElement nameFiled;

    @FindBy(id = "add_hotel:j_idt43")
    private WebElement nameError;

    @FindBy(id = "add_hotel:j_idt44")
    private WebElement globalRateTitle;

    @FindBy(id = "add_hotel:rate")
    private WebElement globalRate;

    @FindBy(className = "ui-rating-star")
    private List<WebElement> globalRateStarsList;

    @FindBy(className = "ui-rating-cancel")
    private WebElement globalRateCancel;

    @FindBy(id = "add_hotel:rate_input")
    private WebElement globalRateValue;

    @FindBy(id = "add_hotel:j_idt46")
    private WebElement dateOfConstructionTitle;

    @FindBy(id = "add_hotel:dateOfConstruction_input")
    private WebElement dateOfConstructionInput;

    @FindBy(xpath = "//*[@id='add_hotel:dateOfConstruction']/button")
    private WebElement dateOfConstructionCalendarIcon;

    @FindBy(id = "add_hotel:j_idt47")
    private WebElement dateOfConstructionError;

    @FindBy(id = "add_hotel:country_label")
    private WebElement countryInput;

    @FindBy(xpath = "//*[@id='add_hotel:country_panel']//li")
    private List <WebElement> countrySelectList;

    @FindBy(id = "add_hotel:j_idt48")
    private WebElement countryTitle;

    @FindBy(id = "add_hotel:j_idt51")
    private WebElement countryError;



    @FindBy(id = "add_hotel:city_label")
    private WebElement cityInput;

    @FindBy(xpath = "//*[@id='add_hotel:city_panel']//li")
    private List <WebElement> citySelectList;

    @FindBy(id = "add_hotel:j_idt52")
    private WebElement cityTitle;

    @FindBy(id = "add_hotel:j_idt55")
    private WebElement cityError;


    public RegisterNewHotelPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {
        return pageTitle.getText();
    }

    public boolean isDataFormDisplayed() {
        return dataForm.isDisplayed();
    }

    public String getDataFormTitle() {
        return dataFormTitle.getText();
    }

    public boolean isSaveButtonDisplayed() {
        return saveButton.isDisplayed();
    }

    public void clickOnSaveButton() {
        saveButton.click();
    }

    public String getNameFiledTitle() {
        return nameFieldTitle.getText();
    }

    public boolean isNameFieldDisplayed() {
        return nameFiled.isDisplayed();
    }

    public void typeName(String name) {
        nameFiled.sendKeys(name);
    }

    public void clearAndTypeName(String name) {
        nameFiled.clear();
        nameFiled.sendKeys(name);
    }

    public void clearNameField() {
        nameFiled.clear();
    }

    public String getNameFieldValue() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        return (String) executor.executeScript(" return arguments[0].value;", nameFiled);
    }

    public boolean isNameErrorDisplayed() {
        return nameError.isDisplayed();
    }

    public String getNameErrorText() {
        return nameError.getText();
    }

    public String getGlobalRateTitle() {
        return globalRateTitle.getText();
    }

    public boolean isGlobalRateDisplayed() {
        return globalRate.isDisplayed();
    }


    public void selectGlobalRateStars(int starsValue) {
        int starValueToIndex = starsValue - 1;
        if (starsValue <= 5 && starsValue >= 1) {
            globalRateStarsList.get(starValueToIndex).click();
        } else if (starsValue == 0) {
            cancelGlobalRateStar();
        }
        //TODO edit to Exception
        else {
            System.out.println("Wrong stars value");
        }
    }

    public void cancelGlobalRateStar(){
        globalRateCancel.click();
    }

    public String getGlobalRateValue(){
        String globalRateValueString = globalRateValue.getAttribute("value");
        //for 0 stars parse to int
        if (globalRateValueString.equals("")) {
            globalRateValueString = "0";
        }
        return globalRateValueString;
    }

    public String getDateOfConstructionTitle(){
        return dateOfConstructionTitle.getText();
    }

    public boolean isDateOfConstructionInputDisplayed(){
        return dateOfConstructionInput.isDisplayed();
    }

    public void typeDateOfConstruction(String dateValue){
        dateOfConstructionInput.sendKeys(dateValue);
    }

    public void clearAndTypeDateOfConstruction(String dateValue){
        dateOfConstructionInput.clear();
        dateOfConstructionInput.sendKeys(dateValue);
    }

    public void clearDateOfConstruction(){
        dateOfConstructionInput.clear();
    }

    public String getDateOfConstructionValue(){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        return (String) executor.executeScript(" return arguments[0].value;", dateOfConstructionInput);
    }

    public boolean isDateOfConstructionCalendarIconDisplayed(){
        return dateOfConstructionCalendarIcon.isDisplayed();
    }

    public boolean isDateOfConstructionErrorDisplayed(){
        return dateOfConstructionError.isDisplayed();
    }

    public String getDateOfConstructionErrorText(){
        return dateOfConstructionError.getText();
    }

    public boolean isCountrySelectDisplayed(){
        return countryInput.isDisplayed();
    }

    public void clickOnCountrySelect(){
        countryInput.click();
    }

    public String getCountrySelectValue(){
        return countryInput.getText();
    }

    public String getCountryTitle(){
        return countryTitle.getText();
    }

    public void setCountrySelect(String selectValue){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(countrySelectList.get(0)));

        boolean isClick = false;
        for (WebElement element: countrySelectList) {
            if (Objects.equals(element.getText(), selectValue)){
                countrySelectList.get(countrySelectList.indexOf(element)).click();
                isClick = true;
                break;
            }
        }
        //TODO edit to Exception
        if (!isClick){
            System.out.println("No such elements");
        }
    }

    public boolean isCountryErrorDisplayed(){
        return countryError.isDisplayed();
    }

    public String getCountryErrorText(){
        return countryError.getText();
    }

    public boolean isCitySelectDisplayed(){
        return cityInput.isDisplayed();
    }

    public String getCitySelectValue(){
        return cityInput.getText();
    }

    public String getCityTitle(){
        return cityTitle.getText();
    }

    public void clickOnCitySelect(){
        cityInput.click();
    }

    public void setCitySelect(String selectValue){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(citySelectList.get(0)));

        boolean isClick = false;
        for (WebElement element: citySelectList) {
            if (Objects.equals(element.getText(), selectValue)){
                citySelectList.get(citySelectList.indexOf(element)).click();
                isClick = true;
                break;
            }
        }
        //TODO edit to Exception
        if (!isClick){
            System.out.println("No such elements");
        }
    }

    public boolean isCityErrorDisplayed(){
        return cityError.isDisplayed();
    }

    public String getCityErrorText(){
        return cityError.getText();
    }
}
