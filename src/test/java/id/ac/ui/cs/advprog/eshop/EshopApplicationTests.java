package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class EshopApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        org.junit.jupiter.api.Assertions.assertNotNull(applicationContext);
    }

    @Test
    void mainMethodLoads() {
        EshopApplication.main(new String[]{});
    }
}
