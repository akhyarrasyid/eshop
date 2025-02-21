package id.ac.ui.cs.advprog.eshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EshopApplication {

    protected EshopApplication() {
        // Protected constructor to be used by Spring Boot
    }

    public static void main(String[] args) {
        SpringApplication.run(EshopApplication.class, args);
    }
}
