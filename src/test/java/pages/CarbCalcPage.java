package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CarbCalcPage {

    private final WebDriver driver;
    private final  WebDriverWait wait;
    private final String url = "https://www.calculator.net/carbohydrate-calculator.html";


    public CarbCalcPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public CarbCalcPage load() {
        driver.get(url);
        wait.until( ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//h1[contains(.,'Carbohydrate Calculator')]")
                )
        );

        return this;
    }

    // Helper to find radio button by its label text

    private WebElement radioByLabel(String labelContains){

        By locator = By.xpath(
                "//label[contains(normalize-space(.),'" + labelContains + "')]"
        );

        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }


    // Page Actions

    public CarbCalcPage setAge(Integer age){
        WebElement ageBox = wait.until(ExpectedConditions.elementToBeClickable(By.id("cage")));
        ageBox.click();
        ageBox.clear();
        ageBox.sendKeys(String.valueOf(age));
        return this;
    }

    public CarbCalcPage selectSex(String sexLabel){
        WebElement sexRadio = radioByLabel(sexLabel);
        sexRadio.click();
        return this;
    }

    public CarbCalcPage setHeightCm (Double cm){
        WebElement heightInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("cheightmeter")));
        heightInput.clear();
        heightInput.sendKeys(String.valueOf(cm));
        return this;
    }


    public CarbCalcPage setWeightKg (Double kg){
        WebElement weightInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("ckg")));
        weightInput.clear();
        weightInput.sendKeys(String.valueOf(kg));
        return this;
    }


    // Levels are:
    // Sedentary: little or no exercise
    // Light: exercise 1-3 times/week
    // Moderate: exercise 4-5 times/week
    // Active: daily exercise or intense excercise 3-4 times/week
    // Very Active: intense exercise 6-7 times/week
    // Extra Active: very intense exercise daily, or physical job
    public CarbCalcPage selectActivityLevel(String activityLabel){
        WebElement activitySelect = wait.until(ExpectedConditions.elementToBeClickable(By.id("cactivity")));
        activitySelect.click();
        Select select = new Select(activitySelect);
        select.selectByVisibleText(activityLabel);
        return this;
    }

    public CarbCalcPage calculate(){
        WebElement calcButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[ @value='Calculate' and @type='submit']")
        ));
        calcButton.click();
        return this;
    }

    public CarbCalcPage clear() {
        WebElement clearButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[ @value='Clear' and @type='button']")
        ));
        clearButton.click();
        return this;
    }

    public boolean getResult(){
        try {
            WebElement table = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table.cinfoT"))
            );
            return table.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean hasErrorMessage(){
        By locator = By.xpath(
                "//*[contains(@class,'inputErrMsg') or contains(.,'Please') or contains(.,'invalid')or contains(.,'required')]"
        );
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }



}
