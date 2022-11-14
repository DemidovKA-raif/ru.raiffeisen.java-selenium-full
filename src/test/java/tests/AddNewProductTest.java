package tests;

import org.testng.annotations.Test;


import java.io.FileNotFoundException;

public class AddNewProductTest extends BaseTestAdmin {

    @Test
    public void newProductFieldForm() throws InterruptedException, FileNotFoundException {
        fieldFormProduct();
    }


}

