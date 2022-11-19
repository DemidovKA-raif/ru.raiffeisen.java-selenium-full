package testsAdmin;


import org.testng.annotations.Test;


import java.io.FileNotFoundException;

public class AddNewProductTest extends BaseTest {

    @Test
    public void newProductFieldForm() throws InterruptedException, FileNotFoundException {
        app.helpers().fieldFormProduct();
    }


}

