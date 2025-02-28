package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;
    
    private Car car;

    @BeforeEach
    void setUp() {
        car = new Car();
        car.setCarName("Toyota Avanza");
        car.setCarColor("Silver");
        car.setCarQuantity(5);
    }

    @Test
    void testCreateWithoutId() {
        when(carRepository.create(any(Car.class))).thenReturn(car);
        
        Car createdCar = carService.create(car);
        
        assertNotNull(createdCar.getCarId());
        assertEquals("Toyota Avanza", createdCar.getCarName());
        verify(carRepository).create(car);
    }
    
    @Test
    void testCreateWithId() {
        car.setCarId("test-id");
        when(carRepository.create(any(Car.class))).thenReturn(car);
        
        Car createdCar = carService.create(car);
        
        assertEquals("test-id", createdCar.getCarId());
        verify(carRepository).create(car);
    }
    
    @Test
    void testCreateWithEmptyId() {
        car.setCarId("");
        when(carRepository.create(any(Car.class))).thenReturn(car);
        
        Car createdCar = carService.create(car);
        
        assertNotEquals("", createdCar.getCarId());
        verify(carRepository).create(car);
    }
    
    @Test
    void testCreateWithEmptyNameThrowsException() {
        car.setCarName("");
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            carService.create(car);
        });
        
        assertEquals("Car name and color cannot be empty", exception.getMessage());
        verify(carRepository, never()).create(any(Car.class));
    }
    
    @Test
    void testCreateWithNullNameThrowsException() {
        car.setCarName(null);
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            carService.create(car);
        });
        
        assertEquals("Car name and color cannot be empty", exception.getMessage());
        verify(carRepository, never()).create(any(Car.class));
    }
    
    @Test
    void testCreateWithEmptyColorThrowsException() {
        car.setCarColor("");
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            carService.create(car);
        });
        
        assertEquals("Car name and color cannot be empty", exception.getMessage());
        verify(carRepository, never()).create(any(Car.class));
    }
    
    @Test
    void testCreateWithNullColorThrowsException() {
        car.setCarColor(null);
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            carService.create(car);
        });
        
        assertEquals("Car name and color cannot be empty", exception.getMessage());
        verify(carRepository, never()).create(any(Car.class));
    }
    
    @Test
    void testCreateWithNegativeQuantityThrowsException() {
        car.setCarQuantity(-1);
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            carService.create(car);
        });
        
        assertEquals("Car quantity cannot be negative", exception.getMessage());
        verify(carRepository, never()).create(any(Car.class));
    }

    @Test
    void testFindAll() {
        List<Car> cars = new ArrayList<>();
        cars.add(car);
        
        Car car2 = new Car();
        car2.setCarName("Honda Jazz");
        car2.setCarColor("Red");
        car2.setCarQuantity(3);
        cars.add(car2);
        
        when(carRepository.findAll()).thenReturn(cars.iterator());
        
        List<Car> result = carService.findAll();
        
        assertEquals(2, result.size());
        assertEquals("Toyota Avanza", result.get(0).getCarName());
        assertEquals("Honda Jazz", result.get(1).getCarName());
    }
    
    @Test
    void testFindAllEmptyList() {
        when(carRepository.findAll()).thenReturn(new ArrayList<Car>().iterator());
        
        List<Car> result = carService.findAll();
        
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindById() {
        String carId = "car-123";
        car.setCarId(carId);
        
        when(carRepository.findById(carId)).thenReturn(car);
        
        Car result = carService.findById(carId);
        
        assertNotNull(result);
        assertEquals(carId, result.getCarId());
        assertEquals("Toyota Avanza", result.getCarName());
    }
    
    @Test
    void testFindByIdNotFound() {
        when(carRepository.findById(anyString())).thenReturn(null);
        
        Car result = carService.findById("non-existent-id");
        
        assertNull(result);
    }

    @Test
    void testUpdate() {
        String carId = "car-123";
        
        carService.update(carId, car);
        
        verify(carRepository).update(carId, car);
    }

    @Test
    void testDeleteCarById() {
        String carId = "car-123";
        
        carService.deleteCarById(carId);
        
        verify(carRepository).delete(carId);
    }
}