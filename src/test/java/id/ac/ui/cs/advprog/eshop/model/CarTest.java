package id.ac.ui.cs.advprog.eshop.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CarTest {
    private Car car;

    @BeforeEach
    void setUp() {
        car = new Car();
    }

    @Test
    void testGetAndSetCarId() {
        String carId = "car-123";
        car.setCarId(carId);
        assertEquals(carId, car.getCarId());
    }

    @Test
    void testGetAndSetCarName() {
        String carName = "Toyota Camry";
        car.setCarName(carName);
        assertEquals(carName, car.getCarName());
    }

    @Test
    void testGetAndSetCarColor() {
        String carColor = "Red";
        car.setCarColor(carColor);
        assertEquals(carColor, car.getCarColor());
    }

    @Test
    void testGetAndSetCarQuantity() {
        int quantity = 5;
        car.setCarQuantity(quantity);
        assertEquals(quantity, car.getCarQuantity());
    }
}