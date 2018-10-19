package HotelsTest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavigationMenu {
    private WebDriver driver;

    @FindBy(xpath = "//*[@class='ui-menu-list ui-helper-reset']/li[1]")
    private WebElement articleButton;

    @FindBy(xpath = "//*[@class='ui-menu-list ui-helper-reset']/li[1]/ul/li[1]")
    private WebElement newButton;

    @FindBy(xpath = "//*[@class='ui-menu-list ui-helper-reset']/li[1]/ul/li[1]//li[1]")
    private WebElement hotelButton;

    public NavigationMenu(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public NavigationMenu moveToArticleButton(){
        moveToElement(articleButton);
        return this;
    }

    public NavigationMenu moveToNewButton(){
        moveToElement(newButton);
        return this;
    }

    public RegisterNewHotelPage clickOnHotelButton(){
        hotelButton.click();
        return new RegisterNewHotelPage(driver);
    }

    public NavigationMenu moveToElement(WebElement element){
        Actions builder = new Actions(driver);
        builder.moveToElement(element).build().perform();
        return this;
    }
}
