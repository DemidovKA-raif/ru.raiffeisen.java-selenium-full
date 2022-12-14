package cucumber;

import io.cucumber.java8.En;
import org.junit.jupiter.api.BeforeAll;
import tests.TestBase;

import java.io.IOException;

public class MyStepDefs extends TestBase implements En {

    public MyStepDefs() throws IOException {
        Before(app::init);
        After(app::stop);
        When("Add duck in basket in quantity {string}", (String arg0) -> {
            app.productPurchaseHelper().addProduct(Integer.parseInt(arg0));
        });
        Then("Go to the basket, check the contents and remove all the ducks one by one in quantity {string}", (String arg0) -> {
            app.productPurchaseHelper().workToBasket(Integer.parseInt(arg0));
        });

    }
}
