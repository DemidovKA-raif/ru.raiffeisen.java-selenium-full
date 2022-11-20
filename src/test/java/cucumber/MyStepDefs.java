package cucumber;

import io.cucumber.java8.En;
import tests.TestBase;

public class MyStepDefs extends TestBase implements En {

    public MyStepDefs() {

        When("^Add duck in basket in quantity '(\\d+)'$", (Integer arg0) -> {
            app.productPurchaseHelper().addProduct(arg0);
        });
        Then("^Go to the basket, check the contents and remove all the ducks one by one in quantity '(\\d+)'$", (Integer arg0) -> {
            app.productPurchaseHelper().workToBasket(arg0);
        });

    }
}
