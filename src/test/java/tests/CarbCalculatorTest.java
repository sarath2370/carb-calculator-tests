package tests;

import base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import pages.CarbCalcPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarbCalculatorTest extends BaseTest {

    @Test
    @DisplayName("A1: Happy Path Male computes recommendation")
    void happyPathTestMaleModerate() {
        CarbCalcPage carbCalcPage = new CarbCalcPage(driver, wait);
        carbCalcPage.load()
                .setAge(30)
                .selectSex("male")
                .setHeightCm(175.0)
                .setWeightKg(70.0)
                .selectActivityLevel("Moderate: exercise 4-5 times/week")
                .calculate();

        assertTrue(carbCalcPage.getResult(), "Expected result table with class 'cinfoT' is visible after calculation");

    }
    @Test
    @DisplayName("A2: Happy Path Female computes recommendation")
    void happyPathTestFemaleSedentary(){
        CarbCalcPage  carbCalcPage = new CarbCalcPage(driver, wait);
        carbCalcPage.load()
                .setAge(28)
                .selectSex("female")
                .setHeightCm(160.0)
                .setWeightKg(55.0)
                .selectActivityLevel("Sedentary: little or no exercise")
                .calculate();

        assertTrue(carbCalcPage.getResult(), "Expected result table with class 'cinfoT' is visible after calculation");
    }

    @Test
    @DisplayName("A3: Happy path – Imperial Male, Moderate activity")
    void happyPathImperialMaleModerate(){
        CarbCalcPage carbCalcPage = new CarbCalcPage(driver, wait);
        carbCalcPage.load()
                .imperialUnits()
                .setAge(30)
                .selectSex("male")
                .ImperialHeightFtin(5, 10)
                .ImperialWeightLbs(154.3)
                .selectActivityLevel("Moderate: exercise 4-5 times/week")
                .calculate();
        assertTrue(carbCalcPage.getResult(), "Expected result table with class 'cinfoT' is visible after calculation");
    }


    @Test
    @DisplayName("A5: Blank submit shows error")
    void blankSubmitShowsError() {
        CarbCalcPage carbCalcPage = new CarbCalcPage(driver, wait);
        carbCalcPage.load()
                .clear()
                .calculate();

        assertFalse(carbCalcPage.getResult(),"Submitting blank form should surface validation or help messages and not result table" );
        assertTrue(carbCalcPage.hasBlankErrorMessage(), "Expected error message is visible after blank submission");
    }



}
