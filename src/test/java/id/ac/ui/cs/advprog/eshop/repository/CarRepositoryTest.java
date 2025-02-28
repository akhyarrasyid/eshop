package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarRepositoryTest {

    @InjectMocks
    CarRepository carRepository;

    @BeforeEach
    void setUp() {
        carRepository = new CarRepository();
    }

    @Test
    void testCreateCarWithoutId() {
        Car car = new Car();
        car.setCarName("Toyota");
        car.setCarColor("Red");
        car.setCarQuantity(10);

        Car result = carRepository.create(car);

        assertNotNull(result.getCarId());
        assertEquals("Toyota", result.getCarName());
        assertEquals("Red", result.getCarColor());
        assertEquals(10, result.getCarQuantity());
    }

    @Test
    void testCreateCarWithId() {
        Car car = new Car();
        car.setCarId("existing-id");
        car.setCarName("Honda");
        car.setCarColor("Blue");
        car.setCarQuantity(5);

        Car result = carRepository.create(car);

        assertEquals("existing-id", result.getCarId());
        assertEquals("Honda", result.getCarName());
    }

    @Test
    void testFindAll() {
        Car car1 = new Car();
        car1.setCarName("Toyota");
        car1.setCarColor("Red");
        car1.setCarQuantity(10);
        carRepository.create(car1);

        Car car2 = new Car();
        car2.setCarName("Honda");
        car2.setCarColor("Blue");
        car2.setCarQuantity(5);
        carRepository.create(car2);

        Iterator<Car> iterator = carRepository.findAll();
        assertTrue(iterator.hasNext());
        
        Car firstCar = iterator.next();
        assertEquals(car1.getCarId(), firstCar.getCarId());
        
        assertTrue(iterator.hasNext());
        Car secondCar = iterator.next();
        assertEquals(car2.getCarId(), secondCar.getCarId());
        
        assertFalse(iterator.hasNext());
    }

    @Test
    void testFindById_Found() {
        Car car = new Car();
        car.setCarName("Toyota");
        car.setCarColor("Red");
        car.setCarQuantity(10);
        Car createdCar = carRepository.create(car);
        String carId = createdCar.getCarId();

        Car foundCar = carRepository.findById(carId);
        
        assertNotNull(foundCar);
        assertEquals(carId, foundCar.getCarId());
        assertEquals("Toyota", foundCar.getCarName());
        assertEquals("Red", foundCar.getCarColor());
        assertEquals(10, foundCar.getCarQuantity());
    }

    @Test
    void testFindById_NotFound() {
        Car car1 = new Car();
        car1.setCarName("Toyota");
        car1.setCarColor("Red");
        car1.setCarQuantity(10);
        carRepository.create(car1);
        
        Car car2 = new Car();
        car2.setCarName("Honda");
        car2.setCarColor("Blue");
        car2.setCarQuantity(5);
        carRepository.create(car2);
        
        Car foundCar = carRepository.findById("non-existent-id");
        
        assertNull(foundCar);
    }

    @Test
    void testFindById_EmptyRepository() {
        Car foundCar = carRepository.findById("any-id");
        
        assertNull(foundCar);
    }

    @Test
    void testFindById_MultipleIteration() {
        Car car1 = new Car();
        car1.setCarId("id-1");
        car1.setCarName("Toyota");
        carRepository.create(car1);
        
        Car car2 = new Car();
        car2.setCarId("id-2");
        car2.setCarName("Honda");
        carRepository.create(car2);
        
        Car foundCar = carRepository.findById("id-2");
        
        assertNotNull(foundCar);
        assertEquals("id-2", foundCar.getCarId());
        assertEquals("Honda", foundCar.getCarName());
    }

    @Test
    void testUpdate_Success() {
        Car car = new Car();
        car.setCarName("Toyota");
        car.setCarColor("Red");
        car.setCarQuantity(10);
        Car createdCar = carRepository.create(car);
        String carId = createdCar.getCarId();

        Car updatedCar = new Car();
        updatedCar.setCarName("Toyota Updated");
        updatedCar.setCarColor("Blue");
        updatedCar.setCarQuantity(15);

        Car result = carRepository.update(carId, updatedCar);
        
        assertNotNull(result);
        assertEquals(carId, result.getCarId());
        assertEquals("Toyota Updated", result.getCarName());
        assertEquals("Blue", result.getCarColor());
        assertEquals(15, result.getCarQuantity());
        
        Car foundCar = carRepository.findById(carId);
        assertEquals("Toyota Updated", foundCar.getCarName());
        assertEquals("Blue", foundCar.getCarColor());
        assertEquals(15, foundCar.getCarQuantity());
    }

    @Test
    void testUpdate_CarNotFound() {
        Car updatedCar = new Car();
        updatedCar.setCarName("Toyota Updated");
        updatedCar.setCarColor("Blue");
        updatedCar.setCarQuantity(15);

        Car result = carRepository.update("non-existent-id", updatedCar);
        
        assertNull(result);
    }

    @Test
    void testDelete_Success() {
        Car car = new Car();
        car.setCarName("Toyota");
        car.setCarColor("Red");
        car.setCarQuantity(10);
        Car createdCar = carRepository.create(car);
        String carId = createdCar.getCarId();

        assertNotNull(carRepository.findById(carId));
        
        carRepository.delete(carId);
        
        assertNull(carRepository.findById(carId));
    }

    @Test
    void testDelete_CarNotFound() {
        Car car = new Car();
        car.setCarName("Toyota");
        car.setCarColor("Red");
        car.setCarQuantity(10);
        carRepository.create(car);
        
        carRepository.delete("non-existent-id");
        
        assertEquals(1, getCarDataSize());
    }
    
    private int getCarDataSize() {
        Iterator<Car> iterator = carRepository.findAll();
        int count = 0;
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }
        return count;
    }
}