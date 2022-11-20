package cucumber;

import appmanager.HelperBase;
import io.cucumber.java8.En;
import org.openqa.selenium.WebDriver;
import tests.TestBase;

public class MyStepdefs extends TestBase implements En {

    public MyStepdefs() {

        int quantity = 4; //количество товаров для покупки

        When("^Add duck in basket$", () -> {
            app.productPurchaseHelper().addProduct(quantity);
        });
        Then("^Go to the basket, check the contents and remove all the ducks one by one$", () -> {
            app.productPurchaseHelper().workToBasket(quantity);
        });
    }
}
