package uitests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FilterTest extends BaseUITest {

    private String filterName = "My Filter" + System.currentTimeMillis();

    @BeforeMethod(alwaysRun = true)
    public void openFiltersPage() {
        homePage.openFiltersPage();
    }

    @Test
    public void createFilterTest() {
        filterPage
                .clickOnFilterButton()
                .fillInFilterName(filterName)
                .clickOnConditionDropDown()
                .clickOnEqualsOption()
                .fillInFilterValue("OpenAI")
                .clickOnSaveFilterButton();
        softAssert.assertTrue(filterPage.verifyFilterIsDisplayed(filterName), "Verify filter is created");
    }

    @Test
    public void deleteFilterTest() {
        filterPage
                .clickOnFilterButton()
                .clickOnFilterElement(filterName)
                .deleteFilter();
        softAssert.assertFalse(filterPage.verifyFilterIsDisplayed(filterName), "Verify filter is deleted");
    }
}