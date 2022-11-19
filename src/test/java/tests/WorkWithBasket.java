package tests;

import org.testng.annotations.Test;

public class WorkWithBasket extends TestBase {

    @Test
    public void byDuck() throws InterruptedException {
        int quantity = 4; //количество товаров для покупки
        app.productPurchaseHelper().addProduct(quantity);
        app.productPurchaseHelper().workToBasket(quantity);
    }
}

